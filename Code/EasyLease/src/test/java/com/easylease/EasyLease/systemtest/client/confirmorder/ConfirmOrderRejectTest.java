package com.easylease.EasyLease.systemtest.client.confirmorder;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

public class ConfirmOrderRejectTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private DBConnection dbConnection;
  private OrderDAO orderDao;
  @BeforeEach()
  public void setUp() throws Exception {
    System.setProperty("webdriver.edge.driver","src/driver/msedgedriver.exe");
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
    orderDao = DBOrderDAO.getInstance();
    driver.manage().window().maximize();
  }

  @Test
  public void testConfirmOrderReject() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("mattia.caprio@unisa.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//li[3]/a/img")).click();
    driver.findElement(By.linkText("Ordini e Preventivi")).click();
    driver.findElement(By.linkText("Visualizza")).click();
    driver.findElement(By.id("btnDeny")).click();
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    Order updatedOrder = orderDao.retrieveById("ORhfga2");
    updatedOrder.setState("Attesa");
    updatedOrder.setVisibility(true);
    orderDao.update(updatedOrder);
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
