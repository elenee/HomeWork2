package Form;

import base.TestBase;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormPage;
import utils.DriverFactory;

@Epic("Form Submission")
@Feature("Automation Practice Form")
public class FormTests extends TestBase {
    @Test
    @Story("Submit student form successfully")
    @Description("Verify that filling the form with valid data submits successfully and data is correct")
    @Severity(SeverityLevel.CRITICAL)
    public void testFormSubmissionAndValidation() {
        Allure.step("Open the form page", () -> {
            DriverFactory.getDriver().get("https://demoqa.com/automation-practice-form");
        });

        FormPage formPage = new FormPage(DriverFactory.getDriver());

        Allure.step("Fill in student personal details", () -> {
            formPage.enterFirstName("Elene")
                    .enterLastName("Rainauli")
                    .enterEmail("elene@gmail.com")
                    .selectGenderFemale()
                    .enterMobile("5667789455");
        });

        Allure.step("Fill in date of birth and subjects", () -> {
            formPage.selectDateOfBirth("February", "2004")
                    .enterSubjects("English")
                    .selectHobbiesReading();
        });

        Allure.step("Fill in address and location", () -> {
            formPage.enterAddress("Tbilisi")
                    .selectState("NCR")
                    .selectCity("Delhi");
        });

        Allure.step("Submit the form", () -> {
            formPage.clickSubmit();
        });

        Assert.assertEquals(formPage.getStudentData("Student Name"), "Elene Rainauli");
        Assert.assertEquals(formPage.getStudentData("Student Email"), "elene@gmail.com");
        Assert.assertEquals(formPage.getStudentData("Gender"), "Female");
        Assert.assertEquals(formPage.getStudentData("Mobile"), "5667789455");
        Assert.assertEquals(formPage.getStudentData("Date of Birth"), "05 February,2004");
        Assert.assertEquals(formPage.getStudentData("Subjects"), "English");
        Assert.assertEquals(formPage.getStudentData("Hobbies"), "Reading");
        Assert.assertEquals(formPage.getStudentData("Address"), "Tbilisi");
        Assert.assertEquals(formPage.getStudentData("State and City"), "NCR Delhi");
    }
}
