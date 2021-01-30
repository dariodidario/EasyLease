package com.easylease.EasyLease.model.DBPool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test of the DBConnection model.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
public class DbConnectionTest {
  private static DataSource dataSource;
  private DbConnection dbConnection;

  @BeforeAll
  static void init() throws Exception {
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dataSource = mysqlDataSource;
  }

  @BeforeEach
  void setUp() {
    dbConnection = DbConnection.getInstance();
    dbConnection.setDataSource(dataSource);
  }

  @Test
  void testConnection() {
    assertNotNull(dbConnection.getConnection());
  }

  @Test
  void testGetDataSource() {
    assertNotNull(dbConnection.getDataSource());
  }

  @Test
  void testGetConnection_DataSourceNull() {
    dbConnection.setDataSource(null);
    assertNull(dbConnection.getConnection());
  }

  @Test
  void testGetConnection_NullConnection() {
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("prova123");
    dbConnection.setDataSource(mysqlDataSource);
    assertNotNull(dbConnection.getConnection());
  }

  @Test
  void testStatement() throws Exception {
    Connection conn = dbConnection.getConnection();
    Statement stm = conn.createStatement();
    assertNotNull(stm);
  }

  @Test
  void testQuery() throws Exception {
    Connection conn = dbConnection.getConnection();
    Statement stm = conn.createStatement();
    stm.execute("SELECT 15 + 5");
    ResultSet rs = stm.getResultSet();
    assertTrue(rs.next());
    assertEquals(20, rs.getInt(1));
  }
}