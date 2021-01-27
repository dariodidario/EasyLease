package com.easylease.EasyLease.systemtest.client.confirmestimate;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDAO;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDAO;
import com.mysql.cj.jdbc.MysqlDataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.fail;

public class ConfirmEstimateAcceptTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private DBConnection dbConnection;
  private EstimateDAO estimateDao;
  private OrderDAO orderDao;
  private List<Order> orderList;
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
    driver.manage().window().maximize();
    estimateDao = DBEstimateDAO.getInstance();
    orderDao = DBOrderDAO.getInstance();
    orderList = orderDao.retrieveAll();
  }

  @Test
  public void testConfirmEstimateAccept() throws Exception {
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
    driver.findElement(By.xpath("(//a[contains(text(),'Visualizza')])[5]")).click();
    driver.findElement(By.id("btnAccept")).click();
    driver.findElement(By.xpath("//li[3]/a/img")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    Estimate estimate = estimateDao.retrieveById("ESdnA9G");
    estimate.setState("Stipulato");
    estimate.setVisibility(true);
    estimateDao.update(estimate);
    List<Order> updatedOrders = orderDao.retrieveAll();
    for (Order item : updatedOrders) {
      boolean found = false;
      for(Order item2 : orderList){
        if (found == false && item.getId().equals(item2.getId())) {
          found = true;
        }
      }
      if(found == false){
        orderDao.delete(item);
      }
    }
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
