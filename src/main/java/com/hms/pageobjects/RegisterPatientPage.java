package com.hms.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.hms.utils.DriverFactory;
import com.hms.utils.ElementUtils;
import com.hms.utils.WaitUtils;

public class RegisterPatientPage {

    private WebDriver driver;
    private ElementUtils elementUtils;
    private WaitUtils waitUtils;

    // Locators
    private By registerPatientButton = By.id("referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension");
    private By givenNameField = By.name("givenName");
    private By familyNameField = By.name("familyName");
    private By genderDropdown = By.id("gender-field");
    private By birthDayField = By.id("birthdateDay-field");
    private By birthMonthDropdown = By.id("birthdateMonth-field");
    private By birthYearField = By.id("birthdateYear-field");
    private By addressField = By.id("address1");
    private By phoneField = By.name("phoneNumber");
    private By relationshipTypeDropdown = By.id("relationship_type");
    private By relativeNameField = By.cssSelector(".person-typeahead");
    private By nextButton = By.id("next-button");
    private By confirmButton = By.id("submit");
    private By registeredGivenName = By.xpath("//span[@class='PersonName-givenName']");
    private By registeredFamilyName = By.xpath("//span[@class='PersonName-familyName']");
    private By errorMessageField = By.xpath("//span[@class='field-error' and contains(., 'provide a value')]");

    public RegisterPatientPage() {
        this.driver = DriverFactory.getDriver();
        elementUtils = new ElementUtils(driver);
        waitUtils = new WaitUtils(driver, 20);
    }

    public void clickRegisterPatient() {
        waitUtils.waitForElementClickable(registerPatientButton);
        elementUtils.doClick(registerPatientButton);
        pause(2000);
    }

    public void enterGivenName(String name) {
        waitUtils.waitForElementVisible(givenNameField);
        elementUtils.doSendKeys(givenNameField, name);
        pause(1000);
    }

    public void enterFamilyName(String name) {
        waitUtils.waitForElementVisible(familyNameField);
        elementUtils.doSendKeys(familyNameField, name);
        pause(1000);
    }

    public void clickNext() {
        waitUtils.waitForElementClickable(nextButton);
        elementUtils.doClick(nextButton);
        pause(1000);
    }

    public void selectGender(String gender) {
        waitUtils.waitForElementVisible(genderDropdown);
        Select select = new Select(driver.findElement(genderDropdown));
        select.selectByVisibleText(gender);
        pause(1000);
    }

    public void enterBirthDay(String day) {
        waitUtils.waitForElementVisible(birthDayField);
        elementUtils.doSendKeys(birthDayField, day);
        pause(1000);
    }

    public void selectBirthMonth(String month) {
        waitUtils.waitForElementVisible(birthMonthDropdown);
        Select select = new Select(driver.findElement(birthMonthDropdown));
        select.selectByValue(month);
        pause(1000);
    }

    public void enterBirthYear(String year) {
        waitUtils.waitForElementVisible(birthYearField);
        elementUtils.doSendKeys(birthYearField, year);
        pause(1000);
    }

    public void enterAddress(String address) {
        waitUtils.waitForElementVisible(addressField);
        elementUtils.doSendKeys(addressField, address);
        pause(1000);
    }

    public void enterPhone(String phone) {
        waitUtils.waitForElementVisible(phoneField);
        elementUtils.doSendKeys(phoneField, phone);
        pause(1000);
    }

    public void selectRelationshipType(String type) {
        waitUtils.waitForElementVisible(relationshipTypeDropdown);
        Select select = new Select(driver.findElement(relationshipTypeDropdown));
        select.selectByVisibleText(type);
        pause(1000);
    }

    public void enterRelativeName(String name) {
        waitUtils.waitForElementVisible(relativeNameField);
        elementUtils.doSendKeys(relativeNameField, name);
        pause(1000);
    }

    public void clickConfirmAndVerify(String fullName) {
        // Wait for Confirm button and click
        waitUtils.waitForElementVisible(confirmButton);
        waitUtils.waitForElementClickable(confirmButton);
        elementUtils.doClick(confirmButton);

        // Wait for the final page with registered patient info
        waitUtils.waitForElementVisible(registeredGivenName);
        waitUtils.waitForElementVisible(registeredFamilyName);

        String actualName = elementUtils.doGetText(registeredGivenName).trim() + " " +
                            elementUtils.doGetText(registeredFamilyName).trim();

        if (!actualName.equalsIgnoreCase(fullName)) {
            throw new AssertionError("Patient registration failed! Expected: " + fullName + ", but found: " + actualName);
        }
    }

    public String getErrorMessage() {
        waitUtils.waitForElementVisible(errorMessageField);
        return elementUtils.doGetText(errorMessageField).trim();
    }

    private void pause(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
