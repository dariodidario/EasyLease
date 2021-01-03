package com.easylease.EasyLease.model.DBPool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBConnectionTest {

  private static DataSource dataSource;
  private DBConnection dbConnection;

  @BeforeAll
  static void init() throws Exception {
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/EasyLease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("master");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dataSource = mysqlDataSource;
  }

  @BeforeEach
  void setUp() {
    dbConnection = DBConnection.getInstance();
    dbConnection.setDataSource(dataSource);
  }

  @Test
  void testConnection() {
    assertNotNull(dbConnection.getConnection());
  }

  @Test
  void testStatement() throws SQLException {
    Connection connection = dbConnection.getConnection();
    Statement stm = connection.createStatement();
    assertNotNull(stm);
  }

  @Test
  void testQuery() throws SQLException {
    Connection connection = dbConnection.getConnection();
    Statement stm = connection.createStatement();
    stm.execute("SELECT 20 + 15");

    ResultSet rs = stm.getResultSet();
    assertTrue(rs.next());
    assertEquals(35, rs.getInt(1));
  }
}