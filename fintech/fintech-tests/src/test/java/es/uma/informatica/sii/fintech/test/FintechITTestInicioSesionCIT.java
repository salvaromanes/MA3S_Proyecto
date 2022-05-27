// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class FintechITTestInicioSesionCIT {
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
  public void inicioSesionCliente() {
    driver.get("http://localhost:8080/fintech-war/");
    driver.manage().window().setSize(new Dimension(974, 824));
    driver.findElement(By.cssSelector("li:nth-child(4) b")).click();
    driver.findElement(By.id("index:user")).click();
    driver.findElement(By.id("index:user")).sendKeys("juan");
    driver.findElement(By.id("index:pass")).sendKeys("juan");
    driver.findElement(By.cssSelector("#index\\3A botonIniciarSesion > .ui-button-text")).click();
  }
}
