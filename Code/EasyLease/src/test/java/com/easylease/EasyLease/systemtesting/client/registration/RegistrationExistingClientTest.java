package com.easylease.EasyLease.systemtesting.client.registration;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.ClientDAO;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

public class RegistrationExistingClientTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static DBConnection dbConnection;
  private static ClientDAO clientDao;
  private static Client client = new Client();

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
    dbConnection.getConnection().setAutoCommit(false);
    clientDao = DBClientDAO.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateInString = "1997-04-05";
    Date date = sdf.parse(dateInString);
    String password = "PaoloRossi97";
    client.setId("CLAB123");
    client.setName("Paolo");
    client.setSurname("Rossi");
    client.setEmail("rossiPaolo@gmail.com");
    client.setPc("81050");
    client.setStreet("Corso Umberto 3");
    client.setCity("Caserta");
    client.setKind("Uomo");
    client.setBirthPlace("Caserta");
    client.setBirthDate(date);
    clientDao.insert(client, password);
  }

  @BeforeEach()
  public void setUp() throws Exception {
    System.setProperty("webdriver.edge.driver", "src/test/java/com/easylease/EasyLease/systemtesting/msedgedriver.exe");
    driver = new EdgeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("ST_NRUSER_1_03")
  public void testRegistrationExsistingClient() throws InterruptedException {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Registrati")).click();
    driver.findElement(By.id("nome")).clear();
    driver.findElement(By.id("nome")).sendKeys("Paolo");
    driver.findElement(By.id("cognome")).clear();
    driver.findElement(By.id("cognome")).sendKeys("Rossi");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("rossiPaolo@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("PaoloRossi97");
    driver.findElement(By.id("conferma")).clear();
    driver.findElement(By.id("conferma")).sendKeys("PaoloRossi97");
    driver.findElement(By.id("bp")).clear();
    driver.findElement(By.id("bp")).sendKeys("Caserta");
    driver.findElement(By.id("bd")).clear();
    driver.findElement(By.id("bd")).sendKeys("04-05-1997");
    driver.findElement(By.id("city")).clear();
    driver.findElement(By.id("city")).sendKeys("Caserta");
    driver.findElement(By.id("cap")).clear();
    driver.findElement(By.id("cap")).sendKeys("81050");
    driver.findElement(By.id("street")).clear();
    driver.findElement(By.id("street")).sendKeys("Corso Umberto 3");
    driver.findElement(By.xpath("//div[@id='divCont']/form/div[11]/label"))
        .click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    clientDao.delete(client);
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
 }
}
