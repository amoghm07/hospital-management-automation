package com.hms.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hms.utils.DriverFactory;
import com.hms.utils.ElementUtils;
import com.hms.utils.WaitUtils;

public class DashboardPage {

    private WebDriver driver;
    private ElementUtils elementUtils;
    private WaitUtils waitUtils;

    private By logoutDropdown = By.xpath("//span[@data-bind='text:fname']");
    private By logoutButton = By.xpath("//a[contains(text(),'Logout')]");

    public DashboardPage() {
        this.driver = DriverFactory.getDriver();
        elementUtils = new ElementUtils(driver);
        waitUtils = new WaitUtils(driver, 20);
    }

    public boolean isDashboardDisplayed() {
        return waitUtils.waitForTitleContains("OpenEMR");
    }

    public void logout() {
        elementUtils.doClick(logoutDropdown);
        elementUtils.doClick(logoutButton);
    }
}
