package com.hms.stepdefinitions;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import com.hms.pageobjects.FindPatientPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class FindPatientStepDefinitions {

    FindPatientPage findPatientPage = new FindPatientPage();

    @And("the user clicks on Find Patient Record")
    public void click_find_patient_record() {
        findPatientPage.clickFindPatientRecord();
    }

    @And("the user searches for patient with name {string}")
    public void search_patient(String fullName) {
        findPatientPage.searchPatient(fullName);
    }

    @Then("the patient with name {string} should be displayed")
    public void verify_patient_displayed(String fullName) {
        assertTrue(findPatientPage.isPatientDisplayed(fullName), "Patient should be displayed in search results");
    }

    @Then("the patient with name {string} should not be displayed")
    public void verify_patient_not_displayed(String fullName) {
        assertFalse(findPatientPage.isPatientDisplayed(fullName), "Patient should NOT be displayed in search results");
    }
}
