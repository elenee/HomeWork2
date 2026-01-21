package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertPage extends BasePage{
    private By alertTab = By.cssSelector("a[href='#Textbox']");
    private By alertButton = By.cssSelector(".btn.btn-info");
    private By resultText = By.id("demo1");

    public AlertPage(WebDriver driver) {
        super(driver);
    }

    public AlertPage openAlertTab() {
        click(alertTab);
        return this;
    }

    public AlertPage clickAlertButton() {
        click(alertButton);
        return this;
    }

    public AlertPage enterTextInAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
        return this;
    }

    public String getResultText() {
        return getText(resultText);
    }
}
