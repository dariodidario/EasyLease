package com.easylease.EasyLease.model.order;

import com.easylease.EasyLease.control.utility.exception.EntityTamperingException;
import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    assertNull(orderDAO.retrieveById("OR12345"));
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
  }

  @Test
  void update_Failure() {
    assertThrows(EntityTamperingException.class, () -> orderDAO.update(null));
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
  void insert_Failure() {
    Order order = orderDAO.retrieveById("OR1ER4T");
    assertThrows(EntityTamperingException.class, () -> orderDAO.insert(order));
  }

  @Test
  void delete_Success() {
    Order order = orderDAO.retrieveById("OR1ER4T");
    order.setId("ORTEST1");
    orderDAO.insert(order);
    orderDAO.delete(order);
  }

  @Test
  void delete_Failure() {
    Order order = orderDAO.retrieveById("OR1ER4T");
    order.setId("ORTEST2");
    assertThrows(EntityTamperingException.class, () -> orderDAO.delete(order));
  }
}