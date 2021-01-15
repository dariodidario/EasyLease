package com.easylease.EasyLease.control.utility;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.ClientDAO;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDAO;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import javax.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test of the EmailManager utility class.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
class EmailManagerTest {
  private static DBConnection dbConnection;
  private OrderDAO orderDAO;
  private ClientDAO clientDAO;
  private EstimateDAO estimateDAO;

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
    clientDAO = DBClientDAO.getInstance();
    estimateDAO = DBEstimateDAO.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
  }

  @AfterEach
  void tearDown() throws SQLException {
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }

  @Test
  void sendOrderNotificationTest_Success() throws MessagingException {
    Client client = clientDAO.retrieveById("CLEE8BD");
    Order order = orderDAO.retrieveById("ORnj86K");
    assertTrue(EmailManager.sendOrderNotification(client, order));
  }

  @Test
  void sendOrderNotificationTest_OrderNull() {
    Client client = clientDAO.retrieveById("CLEE8BD");
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendOrderNotification(client, null));
  }

  @Test
  void sendOrderNotificationTest_ClientNull() {
    Order order = orderDAO.retrieveById("ORnj86K");
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendOrderNotification(null, order));
  }

  @Test
  void sendOrderNotificationTest_BothNull() {
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendOrderNotification(null, null));
  }

  @Test
  void sendEstimateNotificationTest_Success() throws MessagingException {
    Client client = clientDAO.retrieveById("CLEE8BD");
    Estimate estimate = estimateDAO.retrieveById("EShg76T");
    assertTrue(EmailManager.sendEstimateNotification(client, estimate));
  }

  @Test
  void sendEstimateNotificationTest_EstimateNull() {
    Client client = clientDAO.retrieveById("CLEE8BD");
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendEstimateNotification(client, null));
  }

  @Test
  void sendEstimateNotificationTest_ClientNull() {
    Estimate estimate = estimateDAO.retrieveById("EShg76T");
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendEstimateNotification(null, estimate));
  }

  @Test
  void sendEstimateNotificationTest_BothNull() {
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendEstimateNotification(null, null));
  }

}