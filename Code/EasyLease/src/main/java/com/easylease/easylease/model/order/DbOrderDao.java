package com.easylease.easylease.model.order;


import com.easylease.easylease.model.DBPool.DbConnection;
import com.easylease.easylease.model.advisor.Advisor;
import com.easylease.easylease.model.client.Client;
import com.easylease.easylease.model.estimate.DbEstimateDao;
import com.easylease.easylease.model.estimate.EstimateDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class DbOrderDao implements OrderDao {

  private static final Logger logger = Logger.getLogger(
      DbOrderDao.class.getName());
  private static DbOrderDao dao;
  private final Connection connection;

  /**
   * Returns a DBOrderDAO Singleton Object.
   *
   * @return the {@link DbOrderDao} Object that accesses the {@link Order} object in the Database.
   */
  public static OrderDao getInstance() {
    if (dao == null) {
      dao = new DbOrderDao(DbConnection.getInstance().getConnection());
    }
    return dao;
  }


  DbOrderDao(Connection connection) {

    this.connection = connection;
  }

  @Override
  public Order retrieveById(String id) {
    final String query = "SELECT * FROM orders WHERE id_order = ?";
    PreparedStatement stm;
    ResultSet rs;

    if (id == null || id.equals("") || !id.startsWith("OR")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", id));
    }
    try {
      stm = connection.prepareStatement(query);
      stm.setString(1, id);
      stm.execute();

      rs = stm.getResultSet();

      if (!rs.next()) {
        return null;
      }

      return getOrderFromRs(rs);

    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
    return null;
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
  public List<Order> retrieveByState(String state) {
    final String query = "SELECT * FROM orders WHERE state = ?";
    PreparedStatement stm;
    ResultSet rs;

    if (state == null || state.equals("")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", state));
    }
    List<Order> orders = new ArrayList<>();
    try {
      stm = connection.prepareStatement(query);
      stm.setString(1, state);
      stm.execute();

      rs = stm.getResultSet();

      while (rs.next()) {
        orders.add(getOrderFromRs(rs));
      }
      return orders;
    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
    return null;
  }

  @Override
  public List<Order> retrieveAll() {
    final String query = "SELECT * FROM orders";
    PreparedStatement stm;
    ResultSet rs;

    List<Order> orders = new ArrayList<>();
    try {
      stm = connection.prepareStatement(query);
      stm.execute();

      rs = stm.getResultSet();

      while (rs.next()) {
        orders.add(getOrderFromRs(rs));
      }
      return orders;
    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
    return null;
  }

  @Override
  public void update(Order order) throws IllegalArgumentException {
    final String query = "UPDATE orders SET id_estimate = ?, "
        +
        "start_date = ?, end_date = ?, confirm_date = ?, visibility = ?, state = ?,"
        + " creation_date = ? WHERE id_order = ?";
    PreparedStatement stm;
    if (order == null) {
      throw new IllegalArgumentException("Order is null!");
    }
    if (DbOrderDao.getInstance().retrieveById(order.getIdOrder()) == null) {
      throw new IllegalArgumentException("Order does not exist in database!");
    }

    try {
      stm = connection.prepareStatement(query);
      stm.setString(1, order.getEstimate().getIdEstimate());
      stm.setDate(2, order.getStartDate() != null
          ? new java.sql.Date(order.getStartDate().getTime()) :
          null);
      stm.setDate(3, order.getEndDate() != null
          ? new java.sql.Date(order.getEndDate().getTime()) :
          null);
      stm.setDate(4, order.getConfirmDate() != null
          ? new java.sql.Date(order.getConfirmDate().getTime()) :
          null);
      stm.setBoolean(5, order.isVisibility());
      stm.setString(6, order.getState());
      stm.setDate(7, order.getCreationDate() != null
          ? new java.sql.Date(order.getCreationDate().getTime()) :
          null);
      stm.setString(8, order.getIdOrder());
      stm.executeUpdate();
    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
  }

  @Override
  public void insert(Order order) throws IllegalArgumentException {
    final String query =
        "INSERT INTO orders (id_order, id_estimate, start_date, end_date,"
            +
            "confirm_date, visibility, state, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement stm;
    if (order == null) {
      throw new IllegalArgumentException("Order is null!");
    }
    if (DbOrderDao.getInstance().retrieveById(order.getIdOrder()) != null) {
      throw new IllegalArgumentException("Order already exist!");
    }

    try {
      stm = connection.prepareStatement(query);
      stm.setString(1, order.getIdOrder());
      stm.setString(2, order.getEstimate().getIdEstimate());
      stm.setDate(3, order.getStartDate() != null
          ? new java.sql.Date(order.getStartDate().getTime()) :
          null);
      stm.setDate(4, order.getEndDate() != null
          ? new java.sql.Date(order.getEndDate().getTime()) :
          null);
      stm.setDate(5, order.getConfirmDate() != null
          ? new java.sql.Date(order.getConfirmDate().getTime()) :
          null);
      stm.setBoolean(6, order.isVisibility());
      stm.setString(7, order.getState());
      stm.setDate(8, order.getCreationDate() != null
          ? new java.sql.Date(order.getCreationDate().getTime()) :
          null);
      stm.executeUpdate();
    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
  }

  @Override
  public void delete(Order order) throws IllegalArgumentException {
    final String query = "DELETE FROM orders WHERE id_order = ?";
    PreparedStatement stm;
    if (order == null) {
      throw new IllegalArgumentException("Order is null!");
    }
    if (DbOrderDao.getInstance().retrieveById(order.getIdOrder()) == null) {
      throw new IllegalArgumentException("Order does not exist!");
    }

    try {
      stm = connection.prepareStatement(query);
      stm.setString(1, order.getIdOrder());
      stm.executeUpdate();
    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
  }

  /**
   * Returns a list of {@link Client} or {@link Advisor} {@link Order}s.
   *
   * @param id of the {@link Advisor} or {@link Client}.
   * @param query of the {@link Advisor} or {@link Client}.
   * @return a {@link List} or {@link Order}.
   * @version 0.2
   * @since 0.1
   */
  private List<Order> getOrders(String id, String query) {
    List<Order> orders = new ArrayList<>();
    PreparedStatement stm;
    ResultSet rs;

    if (id == null || id.equals("")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", id));
    }
    try {
      stm = connection.prepareStatement(query);
      stm.setString(1, id);
      stm.execute();

      rs = stm.getResultSet();

      while (rs.next()) {
        orders.add(getOrderFromRs(rs));
      }
      return orders;
    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
    return null;
  }

  /**
   * Returns the {@link Order} object created by the ResultSet.
   *
   * @param rs the {@link ResultSet}.
   * @return the {@link Order} returned from the ResultSet.
   * @throws SQLException if the ResultSet is null.
   * @version 0.2
   * @since 0.1
   */
  private Order getOrderFromRs(ResultSet rs) throws SQLException {
    EstimateDao estimateDao = DbEstimateDao.getInstance();
    Order o = new Order();

    o.setIdOrder(rs.getString("id_order"));
    o.setEstimate(estimateDao.retrieveById(rs.getString("id_estimate")));
    o.setStartDate(rs.getDate("start_date") != null
        ? new Date(rs.getDate("start_date").getTime()) :
        null);
    o.setEndDate(rs.getDate("end_date") != null
        ? new Date(rs.getDate("end_date").getTime()) :
        null);
    o.setConfirmDate(rs.getDate("confirm_date") != null
        ? new Date(rs.getDate("confirm_date").getTime()) :
        null);
    o.setVisibility(rs.getBoolean("visibility"));
    o.setState(rs.getString("state"));
    o.setCreationDate(new Date(rs.getDate("creation_date").getTime()));
    return o;
  }
}
