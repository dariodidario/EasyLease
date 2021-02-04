package com.easylease.easylease.systemtesting.admin.addadvisor;

import static org.junit.jupiter.api.Assertions.fail;

import com.easylease.easylease.model.DBPool.DbConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * System Test that tests the functionality of Add Advisor having all the values
 * entered except the password confirmation.
 *
 * @version 0.1
 * @author Sarro Antonio
 */
public class AddAdvisorEmptyCoPasswordTest {
  private WebDriver driver;
  private static DbConnection dbConnection;
  private String baseUrl;

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

  /**
   * Instantiation of the connection to the DB and of the webdriver for selenium.
   *
   * @throws Exception of db
   */
  @BeforeEach
  public void setUp() throws Exception {
    dbConnection.getConnection().setAutoCommit(false);
    System.setProperty("webdriver.edge.driver",
        "src/test/java/com/easylease/EasyLease/systemtesting/msedgedriver.exe");
    DesiredCapabilities capabilities = DesiredCapabilities.edge();
    capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
        UnexpectedAlertBehaviour.ACCEPT);
    driver = new EdgeDriver(capabilities);
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("ST_ADMIN_3_09")
  public void testAddAdvisorEmptyCoPassword() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("lucaVerdi@easylease.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//li[3]/a/img")).click();
    driver.findElement(By.linkText("Aggiungi consulente")).click();
    driver.findElement(By.id("advisor_name")).click();
    driver.findElement(By.id("advisor_name")).clear();
    driver.findElement(By.id("advisor_name")).sendKeys("Piero");
    driver.findElement(By.id("advisor_surname")).click();
    driver.findElement(By.id("advisor_surname")).clear();
    driver.findElement(By.id("advisor_surname")).sendKeys("Angela");
    driver.findElement(By.id("advisor_email")).click();
    driver.findElement(By.id("advisor_email")).clear();
    driver.findElement(By.id("advisor_email"))
        .sendKeys("p.angela@rai.it");
    driver.findElement(By.id("advisor_date")).click();
    driver.findElement(By.id("advisor_date")).clear();
    driver.findElement(By.id("advisor_date")).sendKeys("2021-01-20");
    driver.findElement(By.id("advisor_password")).click();
    driver.findElement(By.id("advisor_password")).clear();
    driver.findElement(By.id("advisor_password")).sendKeys("PieroAngela98");
    driver.findElement(By.id("buttonAddAdvisor")).click();
    driver.findElement(By.xpath("//li[3]/a/img")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  /**
   * Rollback of the DB.
   *
   * @throws Exception of db
   */
  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }
}
