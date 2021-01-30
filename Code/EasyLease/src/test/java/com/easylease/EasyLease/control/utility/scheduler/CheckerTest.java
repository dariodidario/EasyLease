package com.easylease.EasyLease.control.utility.scheduler;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.EstimateDAO;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.OrderDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.Calendar;

class CheckerTest {

  private static DBConnection dbConnection;
  private OrderDAO orderDAO;
  private EstimateDAO estimateDAO;
  private Calendar c;

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
    estimateDAO = DBEstimateDAO.getInstance();
    c = Calendar.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
  }

  @AfterEach
  void tearDown() throws SQLException {
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }

  @Test
  void dailyCheckerTest_Success() {
    DailyChecker thread = new DailyChecker();
    thread.run();
  }

  @Test
  void monthCheckerTest_Success() {
    MonthChecker thread = new MonthChecker();
    thread.run();
  }
}