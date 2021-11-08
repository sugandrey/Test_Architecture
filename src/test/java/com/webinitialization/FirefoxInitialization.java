package com.webinitialization;

import com.propertiesreceiver.GetAllPropertiesClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxInitialization {


    private WebDriver driver;
    private static String fileName = "webSetting.properties";

    public WebDriver getForFirefoxBrowser() {

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if(GetAllPropertiesClass.getPropertyFileValue("invisibleMode", fileName).equalsIgnoreCase("true")) {
            options.addArguments("headless");
        }
        driver = new FirefoxDriver(options);
        return driver;
    }

    public void teardownFirefox() {
        if (driver != null) {
            driver.quit();
        }
    }

}
