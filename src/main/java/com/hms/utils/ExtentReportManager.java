package com.hms.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static void initReports() {
        if (extentReports == null) {
            extentReports = new ExtentReports();

            ExtentSparkReporter spark = new ExtentSparkReporter(ConfigReader.getProperty("extentReportPath"));
            spark.config().setDocumentTitle("OpenMRS Report");
            spark.config().setReportName("OpenMRS Test Automation Report");
            spark.config().setTheme(Theme.STANDARD);

            extentReports.attachReporter(spark);

            extentReports.setSystemInfo("Author", "Amogh M");
            extentReports.setSystemInfo("Environment", "QA");
            extentReports.setSystemInfo("OS", "Windows");
            extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
    }

    public static void startTest(String testName) {
        extentTest = extentReports.createTest(testName);
    }

    public static ExtentTest getTest() {
        return extentTest;
    }

    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
