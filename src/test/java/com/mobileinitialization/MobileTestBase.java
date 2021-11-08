package com.mobileinitialization;

import com.utilities.AppiumServerInit;
import com.utilities.Constants;
import com.utilities.MobileUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobileTestBase {

    private AppiumDriver<MobileElement> driver;
    private static String propertyFile = Constants.PROPERTIES_FILE_NAME;
    private static Logger logger = LogManager.getLogger(MobileTestBase.class.getName());

    public void appiumServerSetup() {

        if(driver == null) {

            if(propertyFile.startsWith("android_")) {
                AppiumServerInit.startAppiumServer();
                logger.info("The Appium server is initialized");
                driver = MobileUtils.getAndroidDriver();
                logger.info("Appium driver is initialized");

            }
            else if (propertyFile.startsWith("iOS_")) {

            }
        }
    }

    public void quit() {

        driver.quit();
        logger.info("Test case execution is completed");
        AppiumServerInit.stopAppiumServer();
        logger.info("Appium server was shut down");
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

}
