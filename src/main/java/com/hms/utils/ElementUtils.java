package com.hms.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {

    private WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void doClick(By locator) {
        driver.findElement(locator).click();
    }

    public void doSendKeys(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public String doGetText(By locator) {
        return driver.findElement(locator).getText();
    }

    public boolean doIsDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }
}
