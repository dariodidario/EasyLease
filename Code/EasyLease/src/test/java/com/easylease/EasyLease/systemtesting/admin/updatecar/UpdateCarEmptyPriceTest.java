package com.easylease.EasyLease.systemtesting.admin.updatecar;

import static org.junit.jupiter.api.Assertions.fail;

import com.easylease.EasyLease.model.DBPool.DBConnection;
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
 * System Test that tests the functionality of Update Car with an empty value
 * entered for "Price".
 *
 * @version 0.1
 * @author Sarro Antonio
 */
public class UpdateCarEmptyPriceTest {
  private WebDriver driver;
  private static DBConnection dbConnection;
  private String baseUrl;

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
  @DisplayName("ST_ADMIN_2_10")
  public void testUpdateCarWrongPrice() {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("giu.digiamp@giudigiamp.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[3]/div/a/img")).click();
    driver.findElement(By.name("Modifica Auto")).click();
    driver.findElement(By.id("matita_price")).click();
    driver.findElement(By.xpath("//input[@type='number']")).click();
    driver.findElement(By.xpath("//input[@type='number']")).clear();
    driver.findElement(By.xpath("//input[@type='number']")).sendKeys("");
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.id("buttonUpdateCar")).click();
    driver.findElement(By.xpath("//li[3]/a/img")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.close();
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
