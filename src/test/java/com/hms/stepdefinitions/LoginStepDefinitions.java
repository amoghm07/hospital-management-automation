package com.hms.stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.hms.pageobjects.LoginPage;
import com.hms.utils.ConfigReader;
import com.hms.utils.DriverFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {

    private WebDriver driver = DriverFactory.getDriver();
    private LoginPage loginPage = new LoginPage(driver);

    @Given("the user is on the login page")
    public void user_on_login_page() {
        driver.get(ConfigReader.getProperty("url"));
    }

    @When("the user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.selectRegistrationDesk(); // click "Registration Desk"
    }

    @And("clicks the Login button")
    public void clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("the user should see {string}")
    public void the_user_should_see(String expected) {
        if (expected.equalsIgnoreCase("Login successful")) {
            // Wait and check for page title "Home"
            assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        } else {
            // Wait and check error message
            String actualError = loginPage.getErrorMessage();
            assertEquals(actualError, expected, "Error message should match");
        }
    }
}
