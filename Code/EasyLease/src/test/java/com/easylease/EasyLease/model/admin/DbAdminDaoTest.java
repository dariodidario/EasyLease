package com.easylease.EasyLease.model.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test of the DBAdminDAO model.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
class DbAdminDaoTest {
  private static DbConnection dbConnection;
  private AdminDao adminDao;

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
    adminDao = DbAdminDao.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
  }

  @AfterEach
  void tearDown() throws SQLException {
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }

  @Test
  void retrieveById_Success() {
    String adminId = "00CfR8I";
    Admin admin = adminDao.retrieveById(adminId);
    assertEquals(adminId, admin.getIdUser());
  }

  @Test
  void retrieveById_NotPresent() {
    String adminId = "00CfRSI";
    Admin admin = adminDao.retrieveById(adminId);
    assertNull(admin);
  }

  @Test
  void retrieveById_NullId() {
    assertThrows(IllegalArgumentException.class, () -> adminDao.retrieveById(null));
  }

  @Test
  void retrieveById_EmptyId() {
    assertThrows(IllegalArgumentException.class, () -> adminDao.retrieveById(""));
  }

  @Test
  void retrieveByEmail_Success() {
    String adminEmail = "lucaVerdi@easylease.com";
    Admin admin = adminDao.retrieveByEmail(adminEmail);
    assertEquals(adminEmail, admin.getEmail());
  }

  @Test
  void retrieveByEmail_NullEmail() {
    assertThrows(IllegalArgumentException.class, () -> adminDao.retrieveByEmail(null));
  }

  @Test
  void retrieveByEmail_EmptyEmail() {
    assertThrows(IllegalArgumentException.class, () -> adminDao.retrieveByEmail(""));
  }

  @Test
  void retrievePasswordByEmail_Success() {
    String adminEmail = "lucaVerdi@easylease.com";
    assertEquals("9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684",
        adminDao.retrievePasswordByEmail(adminEmail));
  }

  @Test
  void retrievePasswordByEmail_NullEmail() {
    assertThrows(IllegalArgumentException.class, () -> adminDao.retrievePasswordByEmail(null));
  }

  @Test
  void retrievePasswordByEmail_NotPresentEmail() {
    assertNull(adminDao.retrievePasswordByEmail("ADXXXXX"));
  }

  @Test
  void retrieveAll_Success() {
    assertNotNull(adminDao.retrieveAll());
  }
}