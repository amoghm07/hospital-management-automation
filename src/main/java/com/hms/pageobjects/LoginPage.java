package com.hms.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hms.utils.ElementUtils;
import com.hms.utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private ElementUtils elementUtils;
    private WaitUtils waitUtils;

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By registrationDesk = By.id("Registration Desk");
    private By loginButton = By.id("loginButton");
    private By errorMessage = By.id("error-message");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        waitUtils = new WaitUtils(driver, 10); // 10-second explicit wait
    }

    // Actions
    public void enterUsername(String username) {
        elementUtils.doSendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        elementUtils.doSendKeys(passwordField, password);
    }

    public void selectRegistrationDesk() {
        elementUtils.doClick(registrationDesk);
    }

    public void clickLogin() {
        elementUtils.doClick(loginButton);
    }

    public String getErrorMessage() {
        // Wait for error message to be visible
        waitUtils.waitForElementVisible(errorMessage);
        return elementUtils.doGetText(errorMessage).trim();
    }

    public boolean isLoginSuccessful() {
        // Wait for the title to be "Home"
        return waitUtils.waitForTitleContains("Home");
    }
}
