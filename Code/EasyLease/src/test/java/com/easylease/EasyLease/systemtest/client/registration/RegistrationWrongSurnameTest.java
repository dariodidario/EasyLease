package com.easylease.EasyLease.systemtest.client.registration;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.ClientDAO;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

public class RegistrationWrongSurnameTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private DBConnection dbConnection;
  private ClientDAO clientDAO;
  private Client cliente;
  @BeforeEach()
  public void setUp() throws Exception {
    System.setProperty("webdriver.edge.driver", "src/driver/msedgedriver.exe");
    driver = new EdgeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
    clientDAO = DBClientDAO.getInstance();
    cliente = clientDAO.retrieveById("CL0MbMy");
    clientDAO.delete(cliente);
  }

  @Test
  public void testRegistrationWrongSurname() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Registrati")).click();
    driver.findElement(By.id("nome")).clear();
    driver.findElement(By.id("nome")).sendKeys("Paolo");
    driver.findElement(By.id("cognome")).clear();
    driver.findElement(By.id("cognome")).sendKeys("123Rossi");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("rossiPaolo@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("PaoloRossi97");
    driver.findElement(By.id("conferma")).clear();
    driver.findElement(By.id("conferma")).sendKeys("PaoloRossi97");
    driver.findElement(By.id("bp")).clear();
    driver.findElement(By.id("bp")).sendKeys("Caserta");
    driver.findElement(By.id("bd")).clear();
    driver.findElement(By.id("bd")).sendKeys("1997-05-04");
    driver.findElement(By.id("city")).clear();
    driver.findElement(By.id("city")).sendKeys("Caserta");
    driver.findElement(By.id("cap")).clear();
    driver.findElement(By.id("cap")).sendKeys("81050");
    driver.findElement(By.id("street")).clear();
    driver.findElement(By.id("street")).sendKeys("Corso Umberto 3");
    driver.findElement(By.xpath("//div[@id='divCont']/form/div[11]/label")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    clientDAO.insert(cliente, "PaoloRossi97");
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }
}
