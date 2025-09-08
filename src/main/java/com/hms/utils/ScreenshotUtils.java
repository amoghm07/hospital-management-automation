package com.hms.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String screenshotDir = ConfigReader.getProperty("screenshotPath");
            File dir = new File(screenshotDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String screenshotPath = screenshotDir + screenshotName + ".png";
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(srcFile, destFile);

            // Return relative path for ExtentReport
            return "../" + screenshotPath.replace("target/", "");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
