package com.easylease.easylease.systemtesting.client.registration;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class RegistrationPasswordTooShortTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;

  @BeforeEach()
  public void setUp() throws Exception {
    System.setProperty("webdriver.edge.driver",
        "src/test/java/com/easylease/EasyLease/systemtesting/msedgedriver.exe");
    driver = new EdgeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("ST_NRUSER_1_2")
  public void testRegistrationPasswordTooShort() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Registrati")).click();
    driver.findElement(By.id("nome")).clear();
    driver.findElement(By.id("nome")).sendKeys("Paolo");
    driver.findElement(By.id("cognome")).clear();
    driver.findElement(By.id("cognome")).sendKeys("Rossi");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("rossiPaolo@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("Paolo");
    driver.findElement(By.id("conferma")).clear();
    driver.findElement(By.id("conferma")).sendKeys("PaoloRossi97");
    driver.findElement(By.id("bp")).clear();
    driver.findElement(By.id("bp")).sendKeys("Caserta");
    driver.findElement(By.id("bd")).clear();
    driver.findElement(By.id("bd")).sendKeys("04-05-1997");
    driver.findElement(By.id("city")).clear();
    driver.findElement(By.id("city")).sendKeys("Caserta");
    driver.findElement(By.id("cap")).clear();
    driver.findElement(By.id("cap")).sendKeys("81050");
    driver.findElement(By.id("street")).clear();
    driver.findElement(By.id("street")).sendKeys("Corso Umberto 3");
    driver.findElement(By.xpath("//div[@id='divCont']/form/div[11]/label"))
        .click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
  }
}
