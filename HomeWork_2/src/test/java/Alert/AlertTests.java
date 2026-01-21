package Alert;

import base.TestBase;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertPage;
import utils.DriverFactory;


@Epic("Alerts Handling")
@Feature("Alert Boxes")
public class AlertTests extends TestBase {
    @Test
    public void testAlertWithTextbox() {
        Allure.step("Open Alerts page", () -> {
            DriverFactory.getDriver().get("https://demo.automationtesting.in/Alerts.html");
        });

        AlertPage alertPage = new AlertPage(DriverFactory.getDriver());

        String fullName = "Elene Rainauli";

        Allure.step("Open Prompt Alert tab", () -> {
            alertPage.openAlertTab();
        });
        Allure.step("Click the prompt alert button", () -> {
            alertPage.clickAlertButton();
        });
        Allure.step("Enter name in alert and accept", () -> {
            alertPage.enterTextInAlert(fullName);
        });

        Allure.step("Verify result text contains entered name", () -> {
            Assert.assertTrue(alertPage.getResultText().contains(fullName),
                    "Alert result should contain the name entered");
        });

    }
}
