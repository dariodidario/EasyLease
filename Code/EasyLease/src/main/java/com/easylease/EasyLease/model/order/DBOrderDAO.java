package com.easylease.EasyLease.model.order;


import com.easylease.EasyLease.control.utility.exception.EntityTamperingException;
import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.EstimateDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the OrderDAO interface, using the singleton DBConnection
 * as the database.
 *
 * @author Antonio Sarro
 * @version 0.2
 * @since 0.1
 */
public class DBOrderDAO implements OrderDAO {

  private static Logger logger = Logger.getLogger(DBOrderDAO.class.getName());
  private static DBOrderDAO dao;
  private Connection connection;

  /**
   * Returns a DBOrderDAO Singleton Object.
   *
   * @return the {@link DBOrderDAO} Object that accesses the {@link Order} object in the Database.
   */
  public static OrderDAO getInstance() {
    if (dao == null) {
      dao = new DBOrderDAO(DBConnection.getInstance().getConnection());
    }
    return dao;
  }


  DBOrderDAO(Connection connection) {

    this.connection = connection;
  }

  @Override
  public Order retrieveById(String id) {
    final String query = "SELECT * FROM orders WHERE id_order = ?";

    if (id == null || id.equals("") || !id.startsWith("OR")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", id));
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, id);
      stm.execute();

      ResultSet rs = stm.getResultSet();
      if (rs == null) {
        return null;
      }
      if (!rs.next()) {
        return null;
      }
      return getOrderFromRs(rs);
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }

  @Override
  public List<Order> retrieveByAdvisor(String id) {

    final String query = "SELECT * FROM orders WHERE id_estimate IN "
        + "(SELECT id_estimate FROM estimate WHERE id_advisor = ?)";

    return getOrders(id, query);
  }

  @Override
  public List<Order> retrieveByClient(String id) {
    final String query = "SELECT * FROM orders WHERE id_estimate IN "
        + "(SELECT id_estimate FROM estimate WHERE id_client = ?)";
    return getOrders(id, query);
  }

  @Override
  public List<Order> retrieveAll() {
    final String query = "SELECT * FROM orders";

    List<Order> orders = new ArrayList<>();
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.execute();
      ResultSet rs = stm.getResultSet();

      if (rs == null) {
        return null;
      }
      while (rs.next()) {
        orders.add(getOrderFromRs(rs));
      }
      return orders;
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }

  @Override
  public void update(Order order) throws EntityTamperingException {
    final String query = "UPDATE orders SET id_estimate = ?, "
        +
        "start_date = ?, end_date = ?, pickup_date = ?, visibility = ?, state = ?,"
        + " creation_date = ? WHERE id_order = ?";
    if (order == null) {
      throw new EntityTamperingException("Order does not exist!");
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, order.getEstimate().getId());
      stm.setDate(2, order.getStartDate() != null ?
          new java.sql.Date(order.getStartDate().getTime()) :
          null);
      stm.setDate(3, order.getEndDate() != null ?
          new java.sql.Date(order.getEndDate().getTime()) :
          null);
      stm.setDate(4, order.getPickupDate() != null ?
          new java.sql.Date(order.getPickupDate().getTime()) :
          null);
      stm.setBoolean(5, order.isVisibility());
      stm.setString(6, order.getState());
      stm.setDate(7, order.getCreationDate() != null ?
          new java.sql.Date(order.getCreationDate().getTime()) :
          null);
      stm.setString(8, order.getId());
      stm.executeUpdate();
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
  }

  @Override
  public void insert(Order order) throws EntityTamperingException {
    final String query =
        "INSERT INTO orders (id_order, id_estimate, start_date, end_date,"
            +
            "pickup_date, visibility, state, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    if(DBOrderDAO.getInstance().retrieveById(order.getId()) != null) {
      throw new EntityTamperingException("Order already exist!");
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, order.getId());
      stm.setString(2, order.getEstimate().getId());
      stm.setDate(3, order.getStartDate() != null ?
          new java.sql.Date(order.getStartDate().getTime()) :
          null);
      stm.setDate(4, order.getEndDate() != null ?
          new java.sql.Date(order.getEndDate().getTime()) :
          null);
      stm.setDate(5, order.getPickupDate() != null ?
          new java.sql.Date(order.getPickupDate().getTime()) :
          null);
      stm.setBoolean(6, order.isVisibility());
      stm.setString(7, order.getState());
      stm.setDate(8, order.getCreationDate() != null ?
          new java.sql.Date(order.getCreationDate().getTime()) :
          null);
      stm.executeUpdate();
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
  }

  @Override
  public void delete(Order order) throws EntityTamperingException {
    final String query = "DELETE FROM orders WHERE id_order = ?";
    if(DBOrderDAO.getInstance().retrieveById(order.getId()) == null) {
      throw new EntityTamperingException("Order does not exist!");
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, order.getId());
      stm.executeUpdate();
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
  }

  /**
   * Returns a list of {@link Client} or {@link Advisor} {@link Order}s.
   *
   * @param id    of the {@link Advisor} or {@link Client}.
   * @param query of the {@link Advisor} or {@link Client}.
   * @return a {@link List} or {@link Order}.
   */
  private List<Order> getOrders(String id, String query) {
    List<Order> orders = new ArrayList<>();
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", id));
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, id);
      stm.execute();

      ResultSet rs = stm.getResultSet();
      while (rs.next()) {
        orders.add(getOrderFromRs(rs));
      }
      return orders;
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }

  /**
   * Returns the {@link Order} object created by the ResultSet.
   *
   * @param rs the {@link ResultSet}.
   * @return the {@link Order} returned from the ResultSet.
   * @throws SQLException if the ResultSet is null.
   */
  private Order getOrderFromRs(ResultSet rs) throws SQLException {
    EstimateDAO estimateDao = DBEstimateDAO.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Order o = new Order();
    o.setId(rs.getString("id_order"));
    o.setEstimate(estimateDao.retrieveById(rs.getString("id_estimate")));
    o.setStartDate(rs.getDate("start_date") != null ?
        new Date(rs.getDate("start_date").getTime()) :
        null);
    o.setEndDate(rs.getDate("end_date") != null ?
        new Date(rs.getDate("end_date").getTime()) :
        null);
    o.setPickupDate(rs.getDate("pickup_date") != null ?
        new Date(rs.getDate("pickup_date").getTime()) :
        null);
    o.setVisibility(rs.getBoolean("visibility"));
    o.setState(rs.getString("state"));
    o.setCreationDate(rs.getDate("creation_date") != null ?
        new Date(rs.getDate("creation_date").getTime()) :
        null);
    return o;
  }
}
