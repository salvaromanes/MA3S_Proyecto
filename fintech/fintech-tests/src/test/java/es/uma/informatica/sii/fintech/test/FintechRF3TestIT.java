// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class FintechRF3TestIT {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void untitled() {
    driver.get("http://localhost:8080/fintech-war/mainv2.xhtml");
    driver.manage().window().setSize(new Dimension(961, 818));
    driver.findElement(By.linkText("Inicio Sesión Administrador")).click();
    driver.findElement(By.id("index:user")).click();
    driver.findElement(By.id("index:user")).sendKeys("ponciano");
    driver.findElement(By.id("index:user")).click();
    driver.findElement(By.id("index:user")).click();
    {
      WebElement element = driver.findElement(By.id("index:user"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.id("index:pass")).click();
    driver.findElement(By.id("index:pass")).sendKeys("ponciano");
    driver.findElement(By.cssSelector("#index\\3A botonIniciarSesion > .ui-button-text")).click();
    driver.findElement(By.linkText("Test")).click();
    driver.findElement(By.cssSelector("#j_idt34\\3Aj_idt35\\3A 0\\3Aj_idt57 > .ui-button-text")).click();
    driver.findElement(By.id("form:j_idt41")).click();
    driver.findElement(By.id("form:j_idt41")).click();
    {
      WebElement element = driver.findElement(By.id("form:j_idt41"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.id("form:j_idt41")).sendKeys("Barcelona");
    driver.findElement(By.cssSelector(".ui-button-text:nth-child(1)")).click();
    driver.findElement(By.cssSelector("#j_idt34\\3Aj_idt35_data td:nth-child(7)")).click();
    driver.findElement(By.cssSelector("#j_idt34\\3Aj_idt35_data td:nth-child(7)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("#j_idt34\\3Aj_idt35_data td:nth-child(7)"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    assertThat(driver.findElement(By.cssSelector("#j_idt34\\3Aj_idt35_data td:nth-child(7)")).getText(), is("Barcelona"));
  }
}
