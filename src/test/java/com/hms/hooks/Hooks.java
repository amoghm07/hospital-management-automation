package com.hms.hooks;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.hms.utils.DriverFactory;
import com.hms.utils.ExtentReportManager;
import com.hms.utils.ScreenshotUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    public WebDriver driver;

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.initReports();
        ExtentReportManager.startTest(scenario.getName());
        driver = DriverFactory.initDriver();
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        try {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, scenario.getName().replaceAll(" ", "_"));
            if (screenshotPath != null) {
                if (scenario.isFailed()) {
                    ExtentReportManager.getTest().fail("Scenario Failed",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else {
                    ExtentReportManager.getTest().pass("Scenario Passed",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }

                // Attach screenshot to Cucumber scenario (optional if viewing in Cucumber plugin)
                scenario.attach(
                    Files.readAllBytes(Paths.get("target/screenshots/" + scenario.getName().replaceAll(" ", "_") + ".png")),
                    "image/png",
                    scenario.getName()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.quitDriver();
            ExtentReportManager.flushReports();
        }
    }
}
