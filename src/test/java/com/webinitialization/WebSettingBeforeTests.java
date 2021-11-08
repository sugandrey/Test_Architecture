package com.webinitialization;

import com.propertiesreceiver.GetAllPropertiesClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class WebSettingBeforeTests {

    private static WebDriver driver;
    private FirefoxInitialization firefoxInitialization = new FirefoxInitialization();
    private ChromeInitialization chromeInitialization = new ChromeInitialization();
    private EdgeInitialization edgeInitialization = new EdgeInitialization();
    private IEInitialization ieInitialization = new IEInitialization();
    private OperaInitialization operaInitialization = new OperaInitialization();
    private static Logger logger = LogManager.getLogger(WebSettingBeforeTests.class.getName());
    private static String fileName = "webSetting.properties";

    public WebDriver setBrowserDriverForTest() {
        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("chrome")) {
            driver = chromeInitialization.getChromeBrowser();
            logger.info("The chrome browser is initialized");
            return driver;
        }
        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("firefox")) {
            driver = firefoxInitialization.getForFirefoxBrowser();
            logger.info("The firefox browser is initialized");
            return driver;
        }
        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("edge")) {
            driver = edgeInitialization.getEdgeBrowser();
            logger.info("The edge browser is initialized");
            return driver;
        }
        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("ie")) {

            driver = ieInitialization.getIEBrowser();
            logger.info("The Internet Explorer browser is initialized");
            return driver;
        }
        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("opera")) {
            driver = operaInitialization.getOperaBrowser();
            logger.info("The opera browser is initialized");
            return driver;

        } else {
            logger.fatal("The browser's driver is null");
            return null;
        }
    }

    public void setTeardownFromBrowserAfterTest() {

        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("chrome")) {

            chromeInitialization.teardownChrome();
            logger.info("The chrome browser is teardown");
        }
        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("firefox")) {

            firefoxInitialization.teardownFirefox();
            logger.info("The firefox browser is teardown");
        }
        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("edge")) {

            edgeInitialization.teardownEdge();
            logger.info("The edge browser is teardown");
        }
        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("ie")) {

            ieInitialization.teardownIE();
            logger.info("The Internet Explorer browser is teardown");
        }
        if (GetAllPropertiesClass.getPropertyFileValue("BrowserForTest", fileName).contains("opera")) {

            operaInitialization.teardownOpera();
            logger.info("The opera browser is teardown");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
