package com.easylease.EasyLease.model.order;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.EstimateDAO;
import com.easylease.EasyLease.model.exception.EntityTamperingException;

import java.sql.*;
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
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class DBOrderDAO implements OrderDAO {

  private static Logger logger = Logger.getLogger(DBOrderDAO.class.getName());
  private static DBOrderDAO dao;
  private Connection connection;

  /**
   * Returns a DBOrderDAO Singleton Object.
   *
   * @return the {@link DBOrderDAO} Object that accesses the {@link Order} object
   *      in the Database.
   */
  public static OrderDAO getInstance() {
    if (dao == null) {
      dao = new DBOrderDAO(DBConnection.getInstance().getConnection());
    }
    return dao;
  }

  private DBOrderDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Order retrieveById(String id) {
    final String query = "SELECT * FROM order WHERE id = ?";
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", id));
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, id);
      stm.execute();

      ResultSet rs = stm.getResultSet();
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

    final String query = "SELECT * FROM order WHERE estimate_id = "
        + "(SELECT estimate_id FROM estimate WHERE advisor_id = ?)";
    return getOrders(id, query);
  }

  @Override
  public List<Order> retrieveByClient(String id) {
    final String query = "SELECT * FROM order WHERE estimate_id = "
        + "(SELECT estimate_id FROM estimate WHERE client_id = ?)";
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
    final String query = "UPDATE order SET id = ?, estimate_id = ?, "
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
    final String query = "INSERT INTO order (id, estimate_id, start_date, end_date,"
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
    final String query = "DELETE FROM order WHERE id_order = ?";

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
   *
   * @param order to be modified or inserted in the Database.
   * @param query to insert or update the order in the Database.
   * @throws SQLException if the query has any problems.
   */
  private void executeInternalQuery(
      Order order, String query) throws SQLException {
    PreparedStatement stm = connection.prepareStatement(query);
    stm.setString(1, order.getId());
    //stm.setString(2, order.getEstimate().getId());
    //stm.setDate(3, (Date) order.getStartDate());
    //stm.setDate(3, (Date) order.getEndDate());
    //stm.setDate(3, (Date) order.getPickupDate());
    stm.setBoolean(4, order.isVisibility());
    stm.executeUpdate();
  }

  /**
   * Returns a list of {@link Client} or {@link Advisor} {@link Order}s.
   *
   * @param id of the {@link Advisor} or {@link Client}.
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
    //EstimateDAO estimateDAO = DBEstimateDAO.getIstance();
    Order o = new Order();
    o.setId(rs.getString("id"));
    //o.setEstimate(estimateDAO.retrieveById(rs.getString("estimate")));
    try {
      o.setStartDate(new SimpleDateFormat().parse(rs.getString("startDate")));
      o.setEndDate(new SimpleDateFormat().parse(rs.getString("endDate")));
      o.setPickupDate(new SimpleDateFormat().parse(rs.getString("pickupDate")));
    } catch (ParseException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
    o.setVisibility(rs.getBoolean("visibility"));
    return o;
  }
}
