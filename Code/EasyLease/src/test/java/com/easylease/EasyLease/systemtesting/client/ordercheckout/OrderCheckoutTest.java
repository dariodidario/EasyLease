package com.easylease.EasyLease.systemtesting.client.ordercheckout;

import static org.junit.jupiter.api.Assertions.fail;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.order.DbOrderDao;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDao;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * System Test that tests the functionality of Order Checkout.
 *
 * @version 0.1
 * @author Sarro Antonio
 */
public class OrderCheckoutTest {
  private OrderDao orderDao;
  private Order order = null;
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
    orderDao = DbOrderDao.getInstance();
    dbConnection.getConnection().setAutoCommit(false);
    System.setProperty("webdriver.edge.driver",
        "src/test/java/com/easylease/EasyLease/systemtesting/msedgedriver.exe");
    DesiredCapabilities capabilities = DesiredCapabilities.edge();
    capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
        UnexpectedAlertBehaviour.ACCEPT);
    driver = new EdgeDriver(capabilities);
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    order = orderDao.retrieveById("ORbG567");
  }

  @Test
  public void testOrderCheckout() {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("iodice.michele@gmail.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
    driver.findElement(By.linkText("Ordini e Preventivi")).click();
    driver.findElement(By.id("ORbG567")).click();
    driver.findElement(By.id("#btnPay")).click();
    driver.findElement(By.id("cardNumber")).click();
    driver.findElement(By.id("cardNumber")).clear();
    driver.findElement(By.id("cardNumber")).sendKeys("5333-3333-3333-3333");
    driver.findElement(By.id("cardExpiry")).click();
    driver.findElement(By.id("cardExpiry")).clear();
    driver.findElement(By.id("cardExpiry")).sendKeys("12/45");
    driver.findElement(By.id("cardCcv")).click();
    driver.findElement(By.id("cardCcv")).clear();
    driver.findElement(By.id("cardCcv")).sendKeys("453");
    driver.findElement(By.id("payment-submit")).click();
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
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
    orderDao.update(order);
  }
}
