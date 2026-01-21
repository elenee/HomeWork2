package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FormPage extends BasePage {

    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By genderFemale = By.cssSelector("label[for='gender-radio-2']");
    private By mobile = By.id("userNumber");
    private By dateOfBirthInput = By.id("dateOfBirthInput");
    private By monthSelect = By.cssSelector(".react-datepicker__month-select");
    private By yearSelect = By.cssSelector(".react-datepicker__year-select");
    private By daySelect = By.cssSelector(".react-datepicker__day--005");
    private By subjectsInput = By.id("subjectsInput");
    private By hobbiesReading = By.cssSelector("label[for='hobbies-checkbox-2']");
    private By address = By.id("currentAddress");
    private By stateDropdown = By.id("state");
    private By cityDropdown = By.id("city");
    private By stateInput = By.id("react-select-3-input");
    private By cityInput = By.id("react-select-4-input");
    private By submitButton = By.cssSelector(".btn.btn-primary");

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public FormPage fillField(By locator, String value) {
        type(locator, value);
        return this;
    }

    public FormPage clickElement(By locator) {
        scrollIntoView(locator);
        click(locator);
        return this;
    }

    public FormPage selectFromDropdown(By dropdownLocator, By inputLocator, String value) {
        scrollIntoView(dropdownLocator);
        click(dropdownLocator);
        type(inputLocator, value);
        driver.findElement(inputLocator).sendKeys("\n");
        return this;
    }

    public FormPage selectDate(By dateInput, By monthSelect, By yearSelect, By daySelect, String month, String year) {
        click(dateInput);
        type(monthSelect, month);
        type(yearSelect, year);
        click(daySelect);
        return this;
    }


    public FormPage enterFirstName(String text) { return fillField(firstName, text); }
    public FormPage enterLastName(String text) { return fillField(lastName, text); }
    public FormPage enterEmail(String text) { return fillField(email, text); }
    public FormPage selectGenderFemale() { return clickElement(genderFemale); }
    public FormPage enterMobile(String number) { return fillField(mobile, number); }
    public FormPage selectDateOfBirth(String month, String year) {
        return selectDate(dateOfBirthInput, monthSelect, yearSelect, daySelect, month, year);
    }
    public FormPage enterSubjects(String subject) {
        fillField(subjectsInput, subject);
        driver.findElement(subjectsInput).sendKeys("\n");
        return this;
    }
    public FormPage selectHobbiesReading() { return clickElement(hobbiesReading); }
    public FormPage enterAddress(String text) { return fillField(address, text); }
    public FormPage selectState(String stateName) { return selectFromDropdown(stateDropdown, stateInput, stateName); }
    public FormPage selectCity(String cityName) { return selectFromDropdown(cityDropdown, cityInput, cityName); }
    public FormPage clickSubmit() { return clickElement(submitButton); }

    private void scrollIntoView(By locator) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    public String getStudentData(String fieldName) {
        By locator = By.xpath("//td[text()='" + fieldName + "']/following-sibling::td");
        return getText(locator);
    }
}
