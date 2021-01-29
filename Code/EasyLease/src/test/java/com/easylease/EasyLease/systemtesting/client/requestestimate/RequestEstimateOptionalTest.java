package com.easylease.EasyLease.systemtesting.client.requestestimate;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

public class RequestEstimateOptionalTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static DBConnection dbConnection;
  private static EstimateDAO estimateDao;
  private static List<Estimate> estimateList;
  private static List<Estimate> updatedEstimate;

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
    estimateDao = DBEstimateDAO.getInstance();
    estimateList = estimateDao.retrieveAll();
  }

  @BeforeEach()
  public void setUp() throws Exception {
    System.setProperty("webdriver.edge.driver", "src/test/java/com/easylease/EasyLease/systemtesting/msedgedriver.exe");
    driver = new EdgeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("ST_CLIENT_5_01")
  public void RequestEstimate_Optional() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).sendKeys("mattia.caprio@unisa.com");
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[3]/div[2]/a/img")).click();
    driver.findElement(By.name("Richiedi preventivo")).click();
    driver.findElement(By.xpath("//label")).click();
    driver.findElement(By.xpath("//label")).click();
    driver.findElement(By.xpath("(//input[@name='optionals'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='optionals'])[4]")).click();
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    updatedEstimate = estimateDao.retrieveAll();
    for (Estimate item : updatedEstimate) {
      boolean found = false;
      for (Estimate item2 : estimateList) {
        if (!found && item.getId().equals(item2.getId())) {
          found = true;
        }
      }
      if (!found) {
        estimateDao.deleteForever(item);
      }
    }
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
