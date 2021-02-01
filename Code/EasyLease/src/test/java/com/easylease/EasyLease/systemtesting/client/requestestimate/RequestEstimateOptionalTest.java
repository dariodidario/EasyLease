package com.easylease.EasyLease.systemtesting.client.requestestimate;


import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.estimate.DbEstimateDao;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDao;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class RequestEstimateOptionalTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private static DbConnection dbConnection;
  private static EstimateDao estimateDao;
  private static List<Estimate> estimateList;
  private static List<Estimate> updatedEstimate;

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
    estimateDao = DbEstimateDao.getInstance();
    estimateList = estimateDao.retrieveAll();
  }

  @BeforeEach()
  public void setUp() throws Exception {
    System.setProperty("webdriver.edge.driver",
        "src/test/java/com/easylease/EasyLease/systemtesting/msedgedriver.exe");
    driver = new EdgeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("ST_CLIENT_1_01")
  public void requestEstimateOptional() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("mattia.caprio@unisa.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.id("loginButton")).click();
    driver.findElement(By.xpath("//img[@alt='...']")).click();
    driver.findElement(By.name("Richiedi preventivo")).click();
    driver.findElement(By.xpath("(//input[@name='optionals'])[5]")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
    Thread.sleep(500);
    driver.findElement(By.xpath("(//input[@name='optionals'])[16]")).click();
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.xpath("//li[3]/a/img")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    updatedEstimate = estimateDao.retrieveAll();
    for (Estimate item : updatedEstimate) {
      boolean found = false;
      for (Estimate item2 : estimateList) {
        if (!found && item.getIdEstimate().equals(item2.getIdEstimate())) {
          found = true;
        }
      }
      if (!found) {
        estimateDao.delete(item);
      }
    }
    driver.quit();
  }
}
