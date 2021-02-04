package com.easylease.easylease.model.advisor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.easylease.easylease.model.DBPool.DbConnection;
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
  DbAdvisorDao dbAdvisorDao;
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
    dbAdvisorDao = (DbAdvisorDao) DbAdvisorDao.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
  }

  @AfterEach
  void tearDown() throws SQLException {
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }

  @Test
  public void retrieveById_CorrectIdGiven() {
    Advisor advisor = dbAdvisorDao.retrieveById("ADJdybc");
    assertEquals("ADJdybc", advisor.getIdUser());
  }

  @Test
  public void retrieveById_UnexistngIdGiven_ExceptedNull() {
    assertNull(dbAdvisorDao.retrieveById("AD00000"));
  }

  @Test
  public void retrieveById_WrongIdGiven_ExceptedNull() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDao.retrieveById("CLcapNk"));
  }

  @Test
  public void retrieveById_NullIdGiven_ExceptedNull() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDao.retrieveById(null));
  }

  @Test
  public void retrieveById_EmptyIdGiven_ExceptedNull() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDao.retrieveById(""));
  }

  @Test
  public void retrieveByEmail_CorrectEmailGiven_ExpectedTrue() {
    Advisor advisor = dbAdvisorDao.retrieveByEmail(
        "marcoGreco@easylease.com");
    assertEquals("marcoGreco@easylease.com", advisor.getEmail());
  }

  @Test
  public void retrieveByEmail_UnexistingEmailGiven_ExpectedTrue() {
    assertNull(dbAdvisorDao.retrieveByEmail("marco@gmail.com"));
  }

  @Test
  public void retrieveByEmail_EmptyEmailGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDao.retrieveByEmail(""));
  }

  @Test
  public void retrieveByEmail_NullEmailGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class,
        () -> dbAdvisorDao.retrieveByEmail(null));
  }

  @Test
  void retrievePasswordByMail_ExistingMailGiven_ExpectedTrue() throws SQLException {
    assertEquals("9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684",
        dbAdvisorDao.retrievePasswordByEmail("marcoGreco@easylease.com"));
  }

  @Test
  void retrievePasswordByMail_NullEmailGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDao.retrievePasswordByEmail(null));
  }

  @Test
  void retrievePasswordByMail_UnexistingEmailGiven_ExpectedNull() {
    assertNull(dbAdvisorDao.retrieveByEmail("marco@gmail.com"));
  }

  @Test
  void retrieveAll_Success() {
    assertNotNull(dbAdvisorDao.retrieveAll());
  }

  @Test
  public void insert_CorrectAdvisorGiven_ExpectedTrue() {
    Advisor advisor = dbAdvisorDao.retrieveById("ADfake0");
    advisor.setEmail("fakeMail0@gmail.com");
    advisor.setIdUser("ADn21xz");
    dbAdvisorDao.insert(advisor, "pass");
    Advisor advisorToCheck = dbAdvisorDao.retrieveById("ADn21xz");
    assertNotNull(advisorToCheck);
    assertEquals(advisor.getIdUser(), advisorToCheck.getIdUser());
    assertEquals(advisor.getFirstName(), advisorToCheck.getFirstName());
    assertEquals(advisor.getSurname(), advisorToCheck.getSurname());
    assertEquals(advisor.getEmail(), advisorToCheck.getEmail());
    assertEquals(advisor.getHireDate(), advisorToCheck.getHireDate());
    dbAdvisorDao.delete(dbAdvisorDao.retrieveById("ADn21xz"));
  }

  @Test
  void insert_ExistingAdvisorGivern_ExceptionThrown() {
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDao.insert(dbAdvisorDao.retrieveById("ADJdybc"), "pass"));
  }

  @Test
  public void insert_NullAdvisorGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDao.insert(null, "pass"));
  }

  @Test
  public void insertNullPasswordGiven_ExpectedExceptiion() throws ParseException {
    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    Advisor advisor = new Advisor();
    advisor.setIdUser("AD12o3h");
    advisor.setFirstName("Elon");
    advisor.setSurname("Musk");
    advisor.setEmail("MuskElon@gmail.com");
    advisor.setHireDate(dataFormat.parse(dataFormat.format(new Date())));
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDao.insert(advisor, null));
  }

  @Test
  public void update_CorrectAdvisorGiven_ExpectedTrue() {
    Advisor advisor = dbAdvisorDao.retrieveById("ADfake0");
    advisor.setFirstName("Elon");
    advisor.setEmail("fakeMail1@gmail.com");
    dbAdvisorDao.update(advisor, "pass");
    Advisor advisorToCheck = dbAdvisorDao.retrieveById("ADfake0");
    assertEquals(advisor.getIdUser(), advisorToCheck.getIdUser());
    assertEquals(advisor.getFirstName(), advisorToCheck.getFirstName());
    assertEquals(advisor.getSurname(), advisorToCheck.getSurname());
    assertEquals(advisor.getEmail(), advisorToCheck.getEmail());
    assertEquals(advisor.getHireDate(), advisorToCheck.getHireDate());
    String realName = advisor.getFirstName();
    advisor.setFirstName(realName);
    String realEmail = advisor.getEmail();
    advisor.setEmail(realEmail);
    dbAdvisorDao.update(advisor, "pass");
  }

  @Test
  public void update_NullAdvisorGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDao.update(null, "pass"));
  }

  @Test
  public void update_UnexistingAdvisorGiven_ExpectedException() throws ParseException {
    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    Advisor advisor = new Advisor();
    advisor.setIdUser("AD12o3h");
    advisor.setFirstName("Elon");
    advisor.setSurname("Musk");
    advisor.setEmail("MuskElon@gmail.com");
    advisor.setHireDate(dataFormat.parse(dataFormat.format(new Date())));
    assertThrows(IllegalArgumentException.class, () ->
        dbAdvisorDao.update(advisor, "pass"));
  }

  @Test
  public void delete_CorrectAdvisorGiven_ExpectedTrue() throws ParseException {
    Advisor advisor = dbAdvisorDao.retrieveById("ADFake0");
    advisor.setIdUser("ADfake1");
    advisor.setEmail("fakeMail2@gmail.com");
    dbAdvisorDao.insert(advisor, "pass");
    dbAdvisorDao.delete(advisor);
  }

  @Test
  public void delelete_NullAdvisorGiven_ExpectedException() {
    assertThrows(IllegalArgumentException.class, ()
        -> dbAdvisorDao.delete(null));
  }

}