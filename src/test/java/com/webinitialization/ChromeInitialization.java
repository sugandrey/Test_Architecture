package com.webinitialization;

import com.propertiesreceiver.GetAllPropertiesClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeInitialization {


    private WebDriver driver;
    private static String fileName = "webSetting.properties";

    public WebDriver getChromeBrowser() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if(GetAllPropertiesClass.getPropertyFileValue("invisibleMode", fileName).equalsIgnoreCase("true")) {
            options.addArguments("headless");
        }
        driver = new ChromeDriver(options);
        return driver;
    }

    public void teardownChrome() {
        if (driver != null) {
            driver.quit();
        }
    }

}
