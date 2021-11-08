package com.webinitialization;

import com.propertiesreceiver.GetAllPropertiesClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class OperaInitialization {


    private WebDriver driver;
    private static String fileName = "webSetting.properties";

    public WebDriver getOperaBrowser() {

        WebDriverManager.operadriver().setup();
        OperaOptions options = new OperaOptions();
        if(GetAllPropertiesClass.getPropertyFileValue("invisibleMode", fileName).equalsIgnoreCase("true")) {
            options.addArguments("headless");
        }
        driver = new OperaDriver(options);
        return driver;
    }

    public void teardownOpera() {
        if (driver != null) {
            driver.quit();
        }
    }

}
