package com.easylease.EasyLease.systemtest.client.requestestimate;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.fail;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.ClientDAO;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class RequestEstimateOptionalTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private DBConnection dbConnection;
  private EstimateDAO estimateDao;
  private List<Estimate> estimateList;

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
    estimateDao = DBEstimateDAO.getInstance();
    estimateList = estimateDao.retrieveAll();
  }

  @Test
  @DisplayName("ST_CL_02")
  public void RequestEstimate_Optional() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).sendKeys("mattia.caprio@unisa.com");
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[3]/div[2]/a/img")).click();
    driver.findElement(By.name("Richiedi preventivo")).click();
    driver.findElement(By.name("Mesi")).click();
    new Select(driver.findElement(By.name("Mesi"))).selectByVisibleText("36");
    driver.findElement(By.xpath("//label")).click();
    driver.findElement(By.xpath("//label")).click();
    driver.findElement(By.name("optionals")).click();
    driver.findElement(By.xpath("(//input[@name='optionals'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='optionals'])[3]")).click();
    driver.findElement(By.xpath("(//input[@name='optionals'])[4]")).click();
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    List<Estimate> updatedEstimate = estimateDao.retrieveAll();
    for (Estimate item : updatedEstimate) {
      boolean found = false;
      for(Estimate item2 : estimateList){
        if (found == false && item.getId().equals(item2.getId())) {
          found = true;
        }
      }
      if(found == false){
        estimateDao.delete(item);
      }
    }
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }
}
