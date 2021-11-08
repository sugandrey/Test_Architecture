package com.webinitialization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class IEInitialization {


    private WebDriver driver;

    public WebDriver getIEBrowser() {

        WebDriverManager.iedriver().setup();
        InternetExplorerOptions options = new InternetExplorerOptions();
        driver = new InternetExplorerDriver(options);
        return driver;
    }

    public void teardownIE() {
        if (driver != null) {
            driver.quit();
        }
    }

}
