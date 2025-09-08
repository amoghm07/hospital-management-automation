package com.hms.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hms.utils.DriverFactory;
import com.hms.utils.ElementUtils;
import com.hms.utils.WaitUtils;

public class FindPatientPage {

    private WebDriver driver;
    private ElementUtils elementUtils;
    private WaitUtils waitUtils;

    // Locators
    private By findPatientLink = By.id("coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension");
    private By searchInput = By.id("patient-search");
    private By patientRows = By.cssSelector("tbody[role='alert'] tr");
    private By noRecords = By.cssSelector("td.dataTables_empty");

    public FindPatientPage() {
        this.driver = DriverFactory.getDriver();
        elementUtils = new ElementUtils(driver);
        waitUtils = new WaitUtils(driver, 20);
    }

    public void clickFindPatientRecord() {
        waitUtils.waitForElementClickable(findPatientLink);
        elementUtils.doClick(findPatientLink);
        waitUtils.waitForElementVisible(searchInput); // wait until page loads
    }

    public void searchPatient(String fullName) {
        waitUtils.waitForElementVisible(searchInput);
        elementUtils.doSendKeys(searchInput, fullName);
        pause(1000); // allow search results to populate
    }

    public boolean isPatientDisplayed(String fullName) {
        // Check if "No matching records" is visible
        List<WebElement> noRecordsList = driver.findElements(noRecords);
        if (!noRecordsList.isEmpty() && noRecordsList.get(0).isDisplayed()) {
            return false;
        }

        // Iterate through patient rows
        List<WebElement> rows = driver.findElements(patientRows);
        for (WebElement row : rows) {
            String name = row.findElements(By.tagName("td")).get(1).getText().trim();
            if (name.equalsIgnoreCase(fullName)) {
                return true;
            }
        }
        return false;
    }

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
