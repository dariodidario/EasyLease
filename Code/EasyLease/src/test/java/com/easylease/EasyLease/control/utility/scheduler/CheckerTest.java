package com.easylease.EasyLease.control.utility.scheduler;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.estimate.DbEstimateDao;
import com.easylease.EasyLease.model.estimate.EstimateDao;
import com.easylease.EasyLease.model.order.DbOrderDao;
import com.easylease.EasyLease.model.order.OrderDao;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.Calendar;

class CheckerTest {

  private static DbConnection dbConnection;
  private OrderDao orderDAO;
  private EstimateDao estimateDAO;
  private Calendar c;

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
    orderDAO = DbOrderDao.getInstance();
    estimateDAO = DbEstimateDao.getInstance();
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