import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demo.automationtesting.in/Alerts.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds((15)));
    }

    @Test
    public void testAlertWithTextbox() {
        driver.findElement(By.cssSelector("a[href='#Textbox']")).click();

        driver.findElement(By.cssSelector(".btn.btn-info")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String fullName = "Elene Rainauli";
        alert.sendKeys(fullName);

        alert.accept();
        String resultText = driver.findElement(By.id("demo1")).getText();

        Assert.assertTrue(resultText.contains(fullName), "Alert result should contain the name entered");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
