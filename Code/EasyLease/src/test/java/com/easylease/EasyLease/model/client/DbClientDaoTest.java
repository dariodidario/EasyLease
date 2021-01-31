package com.easylease.EasyLease.model.client;

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



public class DbClientDaoTest {
  private ClientDao clientDao;
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
    clientDao = DbClientDao.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
  }

  @AfterEach
  void tearDown() throws SQLException {
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }

  @Test
  void retrieveById_ExistingId_Success() {
    String clientId = "CLEE8BD";
    Client client = clientDao.retrieveById(clientId);
    System.out.println(client);
    assertEquals(clientId, client.getIdUser());
  }

  @Test
  void retrieveById_NullId_ExceptionThrown() {
    String clientId = null;
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.retrieveById(clientId));
  }

  @Test
  void retrieveById_EmptyId_ExceptionThrown() {
    String clientId = "";
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.retrieveById(clientId));
  }

  @Test
  void retrieveById_WrongId_ExceptionThrown() {
    String clientId = "ADJdybc";
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.retrieveById(clientId));
  }

  @Test
  void retrieveById_NonexistentId_ExpectedNull() {
    String clientId = "CL11111";
    assertNull(clientDao.retrieveById(clientId));
  }

  @Test
  void retrieveByEmail_ExistingEmail_Success() {
    String email = "mattia.caprio@unisa.com";
    Client client = clientDao.retrieveByEmail(email);
    assertEquals(email, client.getEmail());
  }

  @Test
  void retrieveByEmail_NullEmail_ExceptionThrown() {
    String email = null;
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.retrieveByEmail(email));
  }

  @Test
  void retrieveByEmail_EmptyEmail_ExceptionThrown() {
    String email = "";
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.retrieveByEmail(email));
  }

  @Test
  void retrieveByEmail_NonexistentEmail_ExpectedNull() {
    String email = "mario.rossi.unisa@gmail.com";
    assertNull(clientDao.retrieveByEmail(email));
  }

  @Test
  void retrieveAll_Success() {
    assertNotNull(clientDao.retrieveAll());
  }

  @Test
  void retrievePasswordByMail_ExistingMail_Success() {
    String email = "mattia.caprio@unisa.com";
    String password = "9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684";
    assertEquals(password, clientDao.retrievePasswordByEmail(email));
  }

  @Test
  void retrievePasswordByMail_NullEmail_ExceptionThrown() {
    String email = null;
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.retrievePasswordByEmail(email));
  }

  @Test
  void retrievePasswordByMail_NonexistingEmail_ExpectedNull() {
    String email = "m.m@m.com";
    assertNull(clientDao.retrievePasswordByEmail(email));
  }

  @Test
  void insert_CorrectInsert_Success() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateInString = "31-08-1982";
    Date date = sdf.parse(dateInString);
    Client client = new Client();
    client.setIdUser("CL12345");
    client.setFirstName("Alberto");
    client.setSurname("Angela");
    client.setEmail("alberto.angela@rai.it");
    client.setPc("83020");
    client.setStreet("Via dei sommi");
    client.setCity("Roma");
    client.setKind("Uomo");
    client.setBirth_place("Roma");
    client.setBirth_date(date);
    String password = "pass";
    clientDao.insert(client, password);
    Client toCheck = clientDao.retrieveById("CL12345");
    assertEquals(client.getIdUser(), toCheck.getIdUser());
    assertEquals(client.getFirstName(), toCheck.getFirstName());
    assertEquals(client.getSurname(), toCheck.getSurname());
    assertEquals(client.getEmail(), toCheck.getEmail());
    assertEquals(client.getPc(), toCheck.getPc());
    assertEquals(client.getStreet(), toCheck.getStreet());
    assertEquals(client.getCity(), toCheck.getCity());
    assertEquals(client.getKind(), toCheck.getKind());
    assertEquals(client.getBirth_place(), toCheck.getBirth_place());
    assertEquals(client.getBirth_date(), toCheck.getBirth_date());
    clientDao.delete(client);
  }

  @Test
  void insert_AlreadyExistingClient_ExceptionThrown() {
    Client client = clientDao.retrieveById("CLEE8BD");
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.insert(client, "pass"));
  }

  @Test
  void insert_NullClient_ExceptionThrown() {
    Client client = null;
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.insert(client, "pass"));
  }

  @Test
  void insert_NullPassword_ExceptionThrown() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateInString = "31-08-1982";
    Date date = sdf.parse(dateInString);
    Client client = new Client();
    client.setIdUser("CL12345");
    client.setFirstName("Alberto");
    client.setSurname("Angela");
    client.setEmail("alberto.angela@rai.it");
    client.setPc("83020");
    client.setStreet("Via dei sommi");
    client.setCity("Roma");
    client.setKind("Uomo");
    client.setBirth_place("Roma");
    client.setBirth_date(date);
    String password = null;
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.insert(client, password));
  }

  @Test
  void update_CorrectInsert_Success() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateInString = "1982-08-31";
    Date date = sdf.parse(dateInString);
    Client client = new Client();
    client.setIdUser("CLEE8BD");
    client.setFirstName("Alberto");
    client.setSurname("Angela");
    client.setEmail("alberto.angela@rai.it");
    client.setPc("83020");
    client.setStreet("Via dei sommi");
    client.setCity("Roma");
    client.setKind("Uomo");
    client.setBirth_place("Roma");
    client.setBirth_date(date);
    clientDao.update(client, "pass");
    Client toCheck = clientDao.retrieveById("CLEE8BD");
    assertEquals(client.getIdUser(), toCheck.getIdUser());
    assertEquals(client.getFirstName(), toCheck.getFirstName());
    assertEquals(client.getSurname(), toCheck.getSurname());
    assertEquals(client.getEmail(), toCheck.getEmail());
    assertEquals(client.getPc(), toCheck.getPc());
    assertEquals(client.getStreet(), toCheck.getStreet());
    assertEquals(client.getCity(), toCheck.getCity());
    assertEquals(client.getKind(), toCheck.getKind());
    assertEquals(client.getBirth_place(), toCheck.getBirth_place());
    assertEquals(client.getBirth_date(), toCheck.getBirth_date());
    Client originalClient = clientDao.retrieveById("CLEE8BD");
    clientDao.update(originalClient, "pass");
  }

  @Test
  void update_nullClient_ExceptionThrown() {
    Client client = null;
    String password = "pass";
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.update(client, password));
  }

  @Test
  void update_nullPassword_ExceptionThrown() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateInString = "1982-08-31";
    Date date = sdf.parse(dateInString);
    Client client = new Client();
    client.setIdUser("CLEE8BD");
    client.setFirstName("Alberto");
    client.setSurname("Angela");
    client.setEmail("alberto.angela@rai.it");
    client.setPc("83020");
    client.setStreet("Via dei sommi");
    client.setCity("Roma");
    client.setKind("Uomo");
    client.setBirth_place("Roma");
    client.setBirth_date(date);
    String password = null;
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.update(client, password));
  }

  @Test
  void update_NonexistingClient_ExceptionThrown() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateInString = "1982-08-31";
    Date date = sdf.parse(dateInString);
    Client client = new Client();
    client.setIdUser("CL12345");
    client.setFirstName("Alberto");
    client.setSurname("Angela");
    client.setEmail("alberto.angela@rai.it");
    client.setPc("83020");
    client.setStreet("Via dei sommi");
    client.setCity("Roma");
    client.setKind("Uomo");
    client.setBirth_place("Roma");
    client.setBirth_date(date);
    String password = "pass";
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.update(client, password));
  }

  @Test
  void delete_ExistingClient_Success() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateInString = "31-08-1982";
    Date date = sdf.parse(dateInString);
    Client client = new Client();
    client.setIdUser("CL12345");
    client.setFirstName("Alberto");
    client.setSurname("Angela");
    client.setEmail("alberto.angela@rai.it");
    client.setPc("83020");
    client.setStreet("Via dei sommi");
    client.setCity("Roma");
    client.setKind("Uomo");
    client.setBirth_place("Roma");
    client.setBirth_date(date);
    String password = "pass";
    clientDao.insert(client, password);
    clientDao.delete(client);
    assertNull(clientDao.retrieveById("CL12345"));
  }

  @Test
  void delete_NullClient_ExceptionThrown() {
    Client client = null;
    assertThrows(IllegalArgumentException.class, () ->
        clientDao.delete(client));
  }
}