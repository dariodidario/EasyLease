package com.easylease.EasyLease.systemtesting.admin.addadvisor;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.advisor.AdvisorDAO;
import com.easylease.EasyLease.model.advisor.DBAdvisorDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
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

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

public class AddAdvisorWrongCoPasswordTest {
  private AdvisorDAO advisorDAO;
  private WebDriver driver;
  private static DBConnection dbConnection;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeAll
  static void init() throws Exception {
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dbConnection.setDataSource(mysqlDataSource);
  }

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
  public void testUntitledTestCase() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("giu.digiamp@giudigiamp.com");
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
    driver.findElement(By.id("advisor_confirm_password")).click();
    driver.findElement(By.id("advisor_confirm_password")).clear();
    driver.findElement(By.id("advisor_confirm_password"))
        .sendKeys("ciao99");
    driver.findElement(By.id("buttonAddAdvisor")).click();
    driver.findElement(By.xpath("//li[3]/a/img")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    dbConnection.getConnection().rollback();
    dbConnection.getConnection().setAutoCommit(true);
  }
}
