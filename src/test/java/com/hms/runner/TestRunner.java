package com.hms.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.hms.stepdefinitions", "com.hms.hooks"},
        plugin = {
            "pretty",
            "html:target/cucumber-reports.html",
            "json:target/cucumber.json"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
