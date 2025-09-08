package com.hms.stepdefinitions;

import static org.testng.Assert.assertEquals;

import com.hms.pageobjects.RegisterPatientPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class RegisterPatientStepDefinitions {

    RegisterPatientPage registerPage = new RegisterPatientPage();

    @And("the user clicks on {string}")
    public void user_clicks_on(String buttonName) {
        switch (buttonName) {
            case "Register a Patient":
                registerPage.clickRegisterPatient();
                break;
            case "Next":
                registerPage.clickNext();
                break;
            case "Confirm":
                // In step definitions, Confirm is handled in clickConfirmAndVerify
                break;
            default:
                throw new IllegalArgumentException("Unknown button: " + buttonName);
        }
    }

    @And("the user enters given name {string}")
    public void enter_given_name(String name) {
        registerPage.enterGivenName(name);
        registerPage.clickNext();
    }

    @And("the user enters family name {string}")
    public void enter_family_name(String name) {
        registerPage.enterFamilyName(name);
        registerPage.clickNext();
    }

    @And("the user selects gender {string}")
    public void select_gender(String gender) {
        registerPage.selectGender(gender);
        registerPage.clickNext();
    }

    @And("the user enters birth day {string}")
    public void enter_birth_day(String day) {
        registerPage.enterBirthDay(day);
    }

    @And("the user selects birth month {string}")
    public void select_birth_month(String month) {
        registerPage.selectBirthMonth(month);
    }

    @And("the user enters birth year {string}")
    public void enter_birth_year(String year) {
        registerPage.enterBirthYear(year);
        registerPage.clickNext();
    }

    @And("the user enters address {string}")
    public void enter_address(String address) {
        registerPage.enterAddress(address);
        registerPage.clickNext();
    }

    @And("the user leaves the address field blank")
    public void leave_address_blank() {
        registerPage.enterAddress("");
        registerPage.clickNext();
    }

    @And("the user enters phone number {string}")
    public void enter_phone(String phone) {
        registerPage.enterPhone(phone);
        registerPage.clickNext();
    }

    @And("the user selects relationship type {string}")
    public void select_relationship_type(String type) {
        registerPage.selectRelationshipType(type);
    }

    @And("the user enters relative name {string}")
    public void enter_relative_name(String name) {
        registerPage.enterRelativeName(name);
        registerPage.clickNext();
    }

    @Then("the patient with name {string} should be registered successfully")
    public void verify_patient_registered(String fullName) {
        registerPage.clickConfirmAndVerify(fullName);
    }

    @Then("the error message {string} should be displayed")
    public void verify_error_message(String expectedError) {
        String actualError = registerPage.getErrorMessage();
        assertEquals(actualError, expectedError, "Error message should match");
    }
}
