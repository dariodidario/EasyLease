package com.easylease.EasyLease.systemtesting.admin.addcar;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.car.CarDAO;
import com.easylease.EasyLease.model.car.DBCarDAO;
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

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

public class AddCarWrongTrasmissionTest {
  private WebDriver driver;
  private static DBConnection dbConnection;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

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
  public void testAddCarSuccess() {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("giu.digiamp@giudigiamp.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
    driver.findElement(By.linkText("Aggiungi auto")).click();
    driver.findElement(By.id("brand")).click();
    driver.findElement(By.id("brand")).clear();
    driver.findElement(By.id("brand")).sendKeys("Alfa Romeo");
    driver.findElement(By.id("model")).click();
    driver.findElement(By.id("model")).clear();
    driver.findElement(By.id("model")).sendKeys("Giulietta");
    driver.findElement(By.id("car_type")).click();
    driver.findElement(By.id("car_type")).clear();
    driver.findElement(By.id("car_type")).sendKeys("Sport");
    driver.findElement(By.id("doors")).click();
    driver.findElement(By.id("doors")).clear();
    driver.findElement(By.id("doors")).sendKeys("5");
    driver.findElement(
        By.xpath("//div[@id='container']/form/table/tbody/tr[3]/td[2]"))
        .click();
    driver.findElement(By.id("transmission")).click();
    driver.findElement(By.id("transmission")).clear();
    driver.findElement(By.id("transmission")).sendKeys("13da");
    driver.findElement(By.id("avg_consumption")).click();
    driver.findElement(By.id("avg_consumption")).clear();
    driver.findElement(By.id("avg_consumption")).sendKeys("5.3");
    driver.findElement(By.id("horses")).click();
    driver.findElement(By.id("horses")).clear();
    driver.findElement(By.id("horses")).sendKeys("130");
    driver.findElement(By.id("emission_class")).click();
    driver.findElement(By.id("emission_class")).clear();
    driver.findElement(By.id("emission_class")).sendKeys("Euro6");
    driver.findElement(By.id("co2_emissions")).click();
    driver.findElement(By.id("co2_emissions")).clear();
    driver.findElement(By.id("co2_emissions")).sendKeys("96");
    driver.findElement(By.id("power_supply")).click();
    driver.findElement(By.id("power_supply")).clear();
    driver.findElement(By.id("power_supply")).sendKeys("Benzina");
    driver.findElement(By.id("capacity")).click();
    driver.findElement(By.id("capacity")).clear();
    driver.findElement(By.id("capacity")).sendKeys("1500");
    driver.findElement(By.id("price")).click();
    driver.findElement(By.id("price")).clear();
    driver.findElement(By.id("price")).sendKeys("260");
    driver.findElement(By.id("image_path"))
        .sendKeys(new File(
            "src/test/java/com/easylease/EasyLease/systemtesting/admin/giulietta.jpg")
            .getAbsolutePath());
    driver.findElement(By.id("buttonAddCar")).click();
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
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
