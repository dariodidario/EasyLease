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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the OrderDAO interface, using the singleton DBConnection
 * as the database.
 *
 * @author Antonio Sarro
 * @version 0.1
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

    if (id == null || id.equals("")) {
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


    final String query = "SELECT * FROM orders WHERE id_estimate = "
        + "(SELECT id_estimate FROM estimate WHERE id_advisor = ?)";

    return getOrders(id, query);
  }

  @Override
  public List<Order> retrieveByClient(String id) {
    final String query = "SELECT * FROM order WHERE estimate_id = "
        + "(SELECT estimate_id FROM estimate WHERE id_client = ?)";
    return getOrders(id, query);
  }

  @Override
  public List<Order> retrieveAll() {
    final String query = "SELECT * FROM order";

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
    final String query = "UPDATE orders SET id_order = ?, id_estimate = ?, "
        + "start_date = ?, end_date = ?, pickup_date = ?, visibility = ?";
    if (order == null) {
      throw new EntityTamperingException("Order does not exist!");
    }
    try {
      executeInternalQuery(order, query);
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
  }

  @Override
  public void insert(Order order) throws EntityTamperingException {
    final String query =
        "INSERT INTO orders (id_order, id_estimate, start_date, end_date,"
            + "pickup_date, visibility) VALUES (?, ?, ?, ?, ?, ?)";
    try {
      executeInternalQuery(order, query);
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      throw new EntityTamperingException("Already existing Order!");
    }
  }
  @Override
  public void delete(Order order) throws EntityTamperingException {
    final String query = "DELETE FROM orders WHERE id_order = ?";
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, order.getId());
      stm.executeUpdate();
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      throw new EntityTamperingException("Order does not exist!");
    }
  }

  /**
   * Support method for insert and update method.
   *
   * @param order to be modified or inserted in the Database.
   * @param query to insert or update the order in the Database.
   * @throws SQLException if the query has any problems.
   */
  private void executeInternalQuery(
      Order order, String query) throws SQLException {
    PreparedStatement stm = connection.prepareStatement(query);
    stm.setString(1, order.getId());
    stm.setString(2, order.getEstimate().getId());
    stm.setDate(3, new java.sql.Date(order.getStartDate().getTime()));
    stm.setDate(3, new java.sql.Date(order.getEndDate().getTime()));
    stm.setDate(3, new java.sql.Date(order.getPickupDate().getTime()));
    stm.setBoolean(4, order.isVisibility());
    stm.executeUpdate();
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
    Order o = new Order();
    o.setId(rs.getString("id_order"));
    o.setEstimate(estimateDao.retrieveById(rs.getString("id_estimate")));
    try {
      o.setStartDate(new SimpleDateFormat().parse(rs.getString("start_date")));
      o.setEndDate(new SimpleDateFormat().parse(rs.getString("end_date")));
      o.setPickupDate(new SimpleDateFormat().parse(rs.getString("pickup_date")));
    } catch (ParseException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
    o.setVisibility(rs.getBoolean("visibility"));
    return o;
  }
}
