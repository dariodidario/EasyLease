package com.easylease.EasyLease.model.advisor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.easylease.EasyLease.model.DBPool.DbConnection;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DbAdvisorDaoTest {
  DbAdvisorDao dbAdvisorDAO;
  private static DbConnection dbConnection;

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
    dbAdvisorDAO = (DbAdvisorDao) DbAdvisorDao.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
  }

  @AfterEach
  void tearDown() throws SQLException {
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }

  @Test
  public void retrieveById_CorrectIdGiven() {
    Advisor advisor = dbAdvisorDAO.retrieveById("ADJdybc");
    assertEquals("ADJdybc", advisor.getId());
  }

  @Test
  public void retrieveById_UnexistngIdGiven_ExceptedNull() {
    assertNull(dbAdvisorDAO.retrieveById("AD00000"));
  }

  @Test
  public void retrieveById_WrongIdGiven_ExceptedNull() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDAO.retrieveById("CLcapNk"));
  }

  @Test
  public void retrieveById_NullIdGiven_ExceptedNull() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDAO.retrieveById(null));
  }

  @Test
  public void retrieveById_EmptyIdGiven_ExceptedNull() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDAO.retrieveById(""));
  }

  @Test
  public void retrieveByEmail_CorrectEmailGiven_ExpectedTrue() {
    Advisor advisor = dbAdvisorDAO.retrieveByEmail(
        "rossa.clementina@frutta.com");
    assertEquals("rossa.clementina@frutta.com", advisor.getEmail());
  }

  @Test
  public void retrieveByEmail_UnexistingEmailGiven_ExpectedTrue() {
    assertNull(dbAdvisorDAO.retrieveByEmail("marco@gmail.com"));
  }

  @Test
  public void retrieveByEmail_EmptyEmailGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDAO.retrieveByEmail(""));
  }

  @Test
  public void retrieveByEmail_NullEmailGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDAO.retrieveByEmail(null));
  }

  @Test
  void retrievePasswordByMail_ExistingMailGiven_ExpectedTrue() throws SQLException {
    assertEquals("9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684",
        dbAdvisorDAO.retrievePasswordByEmail("rossa.clementina@frutta.com"));
  }

  @Test
  void retrievePasswordByMail_NullEmailGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDAO.retrievePasswordByEmail(null));
  }

  @Test
  void retrievePasswordByMail_UnexistingEmailGiven_ExpectedNull() {
    assertNull(dbAdvisorDAO.retrieveByEmail("marco@gmail.com"));
  }

  @Test
  void retrieveAll_Success() {
    assertNotNull(dbAdvisorDAO.retrieveAll());
  }

  @Test
  public void insert_CorrectAdvisorGiven_ExpectedTrue() {
    Advisor advisor = dbAdvisorDAO.retrieveById("ADfake0");
    advisor.setEmail("fakeMail@gmail.com");
    advisor.setId("ADn21xz");
    dbAdvisorDAO.insert(advisor, "pass");
    Advisor advisorToCheck = dbAdvisorDAO.retrieveById("ADn21xz");
    assertNotNull(advisorToCheck);
    assertEquals(advisor.getId(), advisorToCheck.getId());
    assertEquals(advisor.getName(), advisorToCheck.getName());
    assertEquals(advisor.getSurname(), advisorToCheck.getSurname());
    assertEquals(advisor.getEmail(), advisorToCheck.getEmail());
    assertEquals(advisor.getHireDate(), advisorToCheck.getHireDate());
    dbAdvisorDAO.delete(dbAdvisorDAO.retrieveById("ADn21xz"));
  }

  @Test
  void insert_ExistingAdvisorGivern_ExceptionThrown() {
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDAO.insert(dbAdvisorDAO.retrieveById("ADJdybc"), "pass"));
  }

  @Test
  public void insert_NullAdvisorGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDAO.insert(null, "pass"));
  }

  @Test
  public void insertNullPasswordGiven_ExpectedExceptiion() throws ParseException {
    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    Advisor advisor = new Advisor();
    advisor.setId("AD12o3h");
    advisor.setName("Elon");
    advisor.setSurname("Musk");
    advisor.setEmail("MuskElon@gmail.com");
    advisor.setHireDate(dataFormat.parse(dataFormat.format(new Date())));
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDAO.insert(advisor, null));
  }

  @Test
  public void update_CorrectAdvisorGiven_ExpectedTrue() {
    Advisor advisor = dbAdvisorDAO.retrieveById("ADfake0");
    String realEmail = advisor.getEmail();
    String realName = advisor.getName();
    advisor.setName("Elon");
    advisor.setEmail("fakeMail@gmail.com");
    dbAdvisorDAO.update(advisor, "pass");
    Advisor advisorToCheck = dbAdvisorDAO.retrieveById("ADfake0");
    assertEquals(advisor.getId(), advisorToCheck.getId());
    assertEquals(advisor.getName(), advisorToCheck.getName());
    assertEquals(advisor.getSurname(), advisorToCheck.getSurname());
    assertEquals(advisor.getEmail(), advisorToCheck.getEmail());
    assertEquals(advisor.getHireDate(), advisorToCheck.getHireDate());
    advisor.setName(realName);
    advisor.setEmail(realEmail);
    dbAdvisorDAO.update(advisor, "pass");
  }

  @Test
  public void update_NullAdvisorGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDAO.update(null, "pass"));
  }

  @Test
  public void update_UnexistingAdvisorGiven_ExpectedException() throws ParseException {
    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    Advisor advisor = new Advisor();
    advisor.setId("AD12o3h");
    advisor.setName("Elon");
    advisor.setSurname("Musk");
    advisor.setEmail("MuskElon@gmail.com");
    advisor.setHireDate(dataFormat.parse(dataFormat.format(new Date())));
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDAO.update(advisor, "pass"));
  }

  @Test
  public void delete_CorrectAdvisorGiven_ExpectedTrue() throws ParseException {
    Advisor advisor = dbAdvisorDAO.retrieveById("ADFake0");
    advisor.setId("ADfake1");
    advisor.setEmail("fakeMail@gmail.com");
    dbAdvisorDAO.insert(advisor, "pass");
    dbAdvisorDAO.delete(advisor);
  }

  @Test
  public void delelete_NullAdvisorGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class, ()
        -> dbAdvisorDAO.delete(null));
  }

}