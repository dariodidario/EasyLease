package com.easylease.easylease.systemtesting.admin.updatecar;

import static org.junit.jupiter.api.Assertions.fail;

import com.easylease.easylease.model.DBPool.DbConnection;
import com.easylease.easylease.model.car.Car;
import com.easylease.easylease.model.car.CarDao;
import com.easylease.easylease.model.car.DbCarDao;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
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
 * System Test that tests the functionality of Update Car with a valid value
 * entered for "Image".
 *
 * @version 0.1
 * @author Sarro Antonio
 */
public class UpdateCarSuccessImageTest {
  private CarDao carDAO;
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
    carDAO = DbCarDao.getInstance();
    carDAO.insert(new Car("CA00000", "Fiat", "Panda", 300,
        "Berlina", true, 5, "Manuale",
        3, 80, "Euro 6", 96,
        "Diesel", 1300, "fiat_panda.jpg"));
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
  @DisplayName("ST_ADMIN_2_13")
  public void testUpdateCarSuccessImage(){
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
    driver.findElement(By.xpath("//div[3]/div/a/img")).click();
    driver.findElement(By.name("Modifica Auto")).click();
    driver.findElement(By.id("matita_image")).click();
    driver.findElement(By.id("image_path"))
        .sendKeys(new File(
            "src/test/java/com/easylease/EasyLease/systemtesting/admin/serie3.jpg")
            .getAbsolutePath());
    driver.findElement(By.id("buttonUpdateCar")).click();
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
    carDAO.delete(carDAO.retrieveById("CA00000"));
  }
}
