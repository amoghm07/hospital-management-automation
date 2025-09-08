package com.hms.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.hms.utils.ConfigReader;
import com.hms.utils.DriverFactory;
import com.hms.utils.ExtentReportManager;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize ExtentReports once before the entire suite
        ExtentReportManager.initReports();
    }

    @AfterSuite
    public void afterSuite() {
        // Flush ExtentReports once after all tests are done
        ExtentReportManager.flushReports();
    }

}
