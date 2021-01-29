package com.easylease.EasyLease.systemtesting.advisor.estimatestipulation;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.concurrent.TimeUnit;

/**
 * System Test that tests the functionality of validation of an order
 * having a negative value of the first field.
 *
 * @author Caprio Mattia
 * @since 0.1
 * @version 0.2
 */
public class StipulationNegativeFirst {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static DBConnection dbConnection = DBConnection.getInstance();

  @BeforeEach
  public void setUp() throws Exception {
    System.setProperty("webdriver.edge.driver",
        "src/test/java/com/easylease/EasyLease/systemtesting/msedgedriver.exe");
    driver = new EdgeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("ST_ADVISOR_2_02")
  public void testStipulationNegativeFirst() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("rossa.clementina@frutta.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
    driver.findElement(By.linkText("Ordini e Preventivi")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
    Thread.sleep(500);
    driver.findElement(By.id("ESjg9I7")).click();
    driver.findElement(By.linkText("Stipula")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
    Thread.sleep(500);
    driver.findElement(By.id("Vetri brillantinati in madreperla")).click();
    driver.findElement(By.id("Vetri brillantinati in madreperla")).clear();
    driver.findElement(By.id("Vetri brillantinati in madreperla"))
        .sendKeys("-1");
    driver.findElement(By.id("Assicurazione furto")).click();
    driver.findElement(By.id("Assicurazione furto")).clear();
    driver.findElement(By.id("Assicurazione furto")).sendKeys("100");
    driver.findElement(By.id("stipulate")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("body"))
        .sendKeys(Keys.CONTROL, Keys.HOME);
    Thread.sleep(500);
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
  }
}
