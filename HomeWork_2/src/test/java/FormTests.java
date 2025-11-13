import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void testFormSubmission() throws InterruptedException {

        driver.findElement(By.id("firstName")).sendKeys("Elene");
        driver.findElement(By.id("lastName")).sendKeys("Rainauli");
        driver.findElement(By.id("userEmail")).sendKeys("elene@gmail.com");
        driver.findElement(By.cssSelector("label[for='gender-radio-2']")).click(); // Female
        driver.findElement(By.id("userNumber")).sendKeys("5667789455");

        driver.findElement(By.id("dateOfBirthInput")).click();
        driver.findElement(By.cssSelector(".react-datepicker__month-select")).sendKeys("February");
        driver.findElement(By.cssSelector(".react-datepicker__year-select")).sendKeys("2004");
        driver.findElement(By.cssSelector(".react-datepicker__day--005")).click();


        driver.findElement(By.id("subjectsInput")).sendKeys("English");
        driver.findElement(By.id("subjectsInput")).sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("label[for='hobbies-checkbox-2']")).click(); // Reading

        driver.findElement(By.id("currentAddress")).sendKeys("Tbilisi");

        WebElement stateDropdown = driver.findElement(By.id("state"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateDropdown);
        stateDropdown.click();
        driver.findElement(By.id("react-select-3-input")).sendKeys("NCR");
        driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);

        WebElement cityDropdown = driver.findElement(By.id("city"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityDropdown);
        cityDropdown.click();
        driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi");
        driver.findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
    }

    @Test(dependsOnMethods = "testFormSubmission")
    public void testAssertations() {
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Student Name']/following-sibling::td")).getText(), "Elene Rainauli");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Student Email']/following-sibling::td")).getText(), "elene@gmail.com");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), "Female");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile']/following-sibling::td")).getText(), "5667789455");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Date of Birth']/following-sibling::td")).getText(), "05 February,2004");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Subjects']/following-sibling::td")).getText(), "English");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Hobbies']/following-sibling::td")).getText(), "Reading");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), "Tbilisi");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State and City']/following-sibling::td")).getText(), "NCR Delhi");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
