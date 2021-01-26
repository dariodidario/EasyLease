package com.easylease.EasyLease.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test of the DBOrderDAO model.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
public class DBOrderDAOTest {
  private static DBConnection dbConnection;
  private OrderDAO orderDAO;

  @BeforeAll
  static void init() throws Exception {
    dbConnection = DBConnection.getInstance();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dbConnection.setDataSource(mysqlDataSource);
  }

  @BeforeEach
  void setUp() throws SQLException {
    orderDAO = DBOrderDAO.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
  }

  @AfterEach
  void tearDown() throws SQLException {
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }

  @Test
  void retrieveById_Success() {
    String orderID = "OR1ER4T";
    Order order = orderDAO.retrieveById(orderID);
    assertEquals(orderID, order.getId());
  }

  @Test
  void retrieveById_NullId() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.retrieveById(null));
  }

  @Test
  void retrieveById_EmptyId() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.retrieveById(""));
  }

  @Test
  void retrieveById_WrongId() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.retrieveById("CL12345"));
  }

  @Test
  void retrieveById_NullRs() {
    assertNull(orderDAO.retrieveById("ORXXXXX"));
  }

  @Test
  void retrieveByAdvisor_Success() {
    String advisorID = "ADJdybc";
    List<Order> orders = orderDAO.retrieveByAdvisor(advisorID);
    assertEquals(advisorID, orders.get(0).getEstimate().getAdvisor().getId());
  }

  @Test
  void retrieveByAdvisor_NullId() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.retrieveByAdvisor(null));
  }

  @Test
  void retrieveByAdvisor_EmptyId() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.retrieveByAdvisor(""));
  }

  @Test
  void retrieveByClient_Success() {
    String clientID = "CLEE8BD";
    List<Order> orders = orderDAO.retrieveByClient(clientID);
    assertEquals(clientID, orders.get(0).getEstimate().getClient().getId());
  }

  @Test
  void retrieveByClient_NullId() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.retrieveByClient(null));
  }

  @Test
  void retrieveByClient_EmptyId() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.retrieveByClient(""));
  }

  @Test
  void retrieveByState_Success() {
    List<Order> orders = orderDAO.retrieveByState("Confermato");
    assertNotNull(orders);
  }

  @Test
  void retrieveByState_NullState() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.retrieveByState(null));
  }

  @Test
  void retrieveByState_EmptyState() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.retrieveByState(""));
  }

  @Test
  void retrieveAll_Success() {
    assertNotNull(orderDAO.retrieveAll());
  }

  @Test
  void update_Success() {
    Order order = orderDAO.retrieveById("OR1ER4T");
    order.setVisibility(!order.isVisibility());
    orderDAO.update(order);
    Order updatedOrder = orderDAO.retrieveById("OR1ER4T");
    assertEquals(order.getId(), updatedOrder.getId());
    assertEquals(order.isVisibility(), updatedOrder.isVisibility());
    order.setVisibility(!order.isVisibility());
    orderDAO.update(order);
  }

  @Test
  void update_NullOrder() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.update(null));
  }

  @Test
  void update_NotPresentOrder() {
    Order order = orderDAO.retrieveById("OR1ER4T");
    order.setId("ORXXXXX");
    assertThrows(IllegalArgumentException.class, () -> orderDAO.update(order));
  }

  @Test
  void insert_Success() {
    Order order = orderDAO.retrieveById("OR1ER4T");
    order.setId("ORTEST1");
    orderDAO.insert(order);
    Order newOrder = orderDAO.retrieveById("ORTEST1");
    assertNotNull(newOrder);
    assertEquals(order.getId(), newOrder.getId());
    assertEquals(order.isVisibility(), newOrder.isVisibility());
    assertEquals(order.getStartDate(), newOrder.getStartDate());
    orderDAO.delete(order);
  }

  @Test
  void insert_NullOrder() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.insert(null));
  }

  @Test
  void insert_AlreadyPresentOrder() {
    Order order = orderDAO.retrieveById("OR1ER4T");
    assertThrows(IllegalArgumentException.class, () -> orderDAO.insert(order));
  }

  @Test
  void delete_Success() {
    Order order = orderDAO.retrieveById("OR1ER4T");
    order.setId("ORTEST1");
    orderDAO.insert(order);
    assertNotNull(orderDAO.retrieveById("ORTEST1"));
    orderDAO.delete(order);
    assertNull(orderDAO.retrieveById("ORTEST1"));
  }

  @Test
  void delete_NullOrder() {
    assertThrows(IllegalArgumentException.class, () -> orderDAO.delete(null));
  }

  @Test
  void delete_NotPresentOrder() {
    Order order = orderDAO.retrieveById("OR1ER4T");
    order.setId("ORXXXXX");
    assertThrows(IllegalArgumentException.class, () -> orderDAO.delete(order));
  }
}