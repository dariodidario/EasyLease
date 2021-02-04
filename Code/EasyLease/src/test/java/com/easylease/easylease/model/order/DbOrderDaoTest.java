package com.easylease.easylease.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.easylease.easylease.model.DBPool.DbConnection;
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
public class DbOrderDaoTest {
  private static DbConnection dbConnection;
  private OrderDao orderDao;

  @BeforeAll
  static void init() throws Exception {
    dbConnection = DbConnection.getInstance();
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
    orderDao = DbOrderDao.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
  }

  @AfterEach
  void tearDown() throws SQLException {
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }

  @Test
  void retrieveById_Success() {
    String orderId = "OR1ER4T";
    Order order = orderDao.retrieveById(orderId);
    assertEquals(orderId, order.getIdOrder());
  }

  @Test
  void retrieveById_NullId() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.retrieveById(null));
  }

  @Test
  void retrieveById_EmptyId() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.retrieveById(""));
  }

  @Test
  void retrieveById_WrongId() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.retrieveById("CL12345"));
  }

  @Test
  void retrieveById_NullRs() {
    assertNull(orderDao.retrieveById("ORXXXXX"));
  }

  @Test
  void retrieveByAdvisor_Success() {
    String advisorId = "ADJdybc";
    List<Order> orders = orderDao.retrieveByAdvisor(advisorId);
    assertEquals(advisorId, orders.get(0).getEstimate().getAdvisor().getIdUser());
  }

  @Test
  void retrieveByAdvisor_NullId() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.retrieveByAdvisor(null));
  }

  @Test
  void retrieveByAdvisor_EmptyId() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.retrieveByAdvisor(""));
  }

  @Test
  void retrieveByClient_Success() {
    String clientId = "CLEE8BD";
    List<Order> orders = orderDao.retrieveByClient(clientId);
    assertEquals(clientId, orders.get(0).getEstimate().getClient().getIdUser());
  }

  @Test
  void retrieveByClient_NullId() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.retrieveByClient(null));
  }

  @Test
  void retrieveByClient_EmptyId() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.retrieveByClient(""));
  }

  @Test
  void retrieveByState_Success() {
    List<Order> orders = orderDao.retrieveByState("Confermato");
    assertNotNull(orders);
  }

  @Test
  void retrieveByState_NullState() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.retrieveByState(null));
  }

  @Test
  void retrieveByState_EmptyState() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.retrieveByState(""));
  }

  @Test
  void retrieveAll_Success() {
    assertNotNull(orderDao.retrieveAll());
  }

  @Test
  void update_Success() {
    Order order = orderDao.retrieveById("OR1ER4T");
    order.setVisibility(!order.isVisibility());
    orderDao.update(order);
    Order updatedOrder = orderDao.retrieveById("OR1ER4T");
    assertEquals(order.getIdOrder(), updatedOrder.getIdOrder());
    assertEquals(order.isVisibility(), updatedOrder.isVisibility());
    order.setVisibility(!order.isVisibility());
    orderDao.update(order);
  }

  @Test
  void update_NullOrder() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.update(null));
  }

  @Test
  void update_NotPresentOrder() {
    Order order = orderDao.retrieveById("OR1ER4T");
    order.setIdOrder("ORXXXXX");
    assertThrows(IllegalArgumentException.class, () -> orderDao.update(order));
  }

  @Test
  void insert_Success() {
    Order order = orderDao.retrieveById("OR1ER4T");
    order.setIdOrder("ORTEST1");
    orderDao.insert(order);
    Order newOrder = orderDao.retrieveById("ORTEST1");
    assertNotNull(newOrder);
    assertEquals(order.getIdOrder(), newOrder.getIdOrder());
    assertEquals(order.isVisibility(), newOrder.isVisibility());
    assertEquals(order.getStartDate(), newOrder.getStartDate());
    orderDao.delete(order);
  }

  @Test
  void insert_NullOrder() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.insert(null));
  }

  @Test
  void insert_AlreadyPresentOrder() {
    Order order = orderDao.retrieveById("OR1ER4T");
    assertThrows(IllegalArgumentException.class, () -> orderDao.insert(order));
  }

  @Test
  void delete_Success() {
    Order order = orderDao.retrieveById("OR1ER4T");
    order.setIdOrder("ORTEST1");
    orderDao.insert(order);
    assertNotNull(orderDao.retrieveById("ORTEST1"));
    orderDao.delete(order);
    assertNull(orderDao.retrieveById("ORTEST1"));
  }

  @Test
  void delete_NullOrder() {
    assertThrows(IllegalArgumentException.class, () -> orderDao.delete(null));
  }

  @Test
  void delete_NotPresentOrder() {
    Order order = orderDao.retrieveById("OR1ER4T");
    order.setIdOrder("ORXXXXX");
    assertThrows(IllegalArgumentException.class, () -> orderDao.delete(order));
  }
}