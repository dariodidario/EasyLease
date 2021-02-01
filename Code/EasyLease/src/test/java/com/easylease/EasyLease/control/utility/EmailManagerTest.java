package com.easylease.EasyLease.control.utility;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.ClientDao;
import com.easylease.EasyLease.model.client.DbClientDao;
import com.easylease.EasyLease.model.estimate.DbEstimateDao;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDao;
import com.easylease.EasyLease.model.order.DbOrderDao;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDao;
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
  private static DbConnection dbConnection;
  private OrderDao orderDao;
  private ClientDao clientDao;
  private EstimateDao estimateDao;

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
    clientDao = DbClientDao.getInstance();
    estimateDao = DbEstimateDao.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
  }

  @AfterEach
  void tearDown() throws SQLException {
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }

  @Test
  void sendOrderNotificationTest_Success() throws MessagingException {
    Client client = clientDao.retrieveById("CLEE8BD");
    Order order = orderDao.retrieveById("ORnj86K");
    assertTrue(EmailManager.sendOrderNotification(client, order));
  }

  @Test
  void sendOrderNotificationTest_OrderNull() {
    Client client = clientDao.retrieveById("CLEE8BD");
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendOrderNotification(client, null));
  }

  @Test
  void sendOrderNotificationTest_ClientNull() {
    Order order = orderDao.retrieveById("ORnj86K");
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
    Client client = clientDao.retrieveById("CLEE8BD");
    Estimate estimate = estimateDao.retrieveById("EShg76T");
    assertTrue(EmailManager.sendEstimateNotification(client, estimate));
  }

  @Test
  void sendEstimateNotificationTest_EstimateNull() {
    Client client = clientDao.retrieveById("CLEE8BD");
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendEstimateNotification(client, null));
  }

  @Test
  void sendEstimateNotificationTest_ClientNull() {
    Estimate estimate = estimateDao.retrieveById("EShg76T");
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendEstimateNotification(null, estimate));
  }

  @Test
  void sendEstimateNotificationTest_BothNull() {
    assertThrows(IllegalArgumentException.class, () ->
        EmailManager.sendEstimateNotification(null, null));
  }

}