package com.checkexecution;


import com.androidScreenResources.HomePage;
import com.androidScreenResources.HotelsSearchPage;
import com.propertiesreceiver.GetAllPropertiesClass;
import com.utilities.*;
import com.webapptestcases.LoginAsCustomerTestCase;
import com.webinitialization.WebSettingBeforeTests;
import com.webinitialization.WebSettingsSetup;
import com.webpageresources.HomeAccountPage;
import com.webpageresources.MainPage;
import com.webpageresources.RegistrationAndLoginPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Execution {

    private static WebSettingBeforeTests settingBeforeTests;
    private static WebDriver driver;
    private static WebSettingsSetup webSettingsSetup;
    private static MainPage mainPage;
    private static Logger logger = LogManager.getLogger(LoginAsCustomerTestCase.class.getName());
    private static RegistrationAndLoginPage registrationAndLoginPage;
    private static String fileName = "webSetting.properties";
    private static HomeAccountPage homeAccountPage;

    public static void main(String[] args) {
        webSettingsSetup = new WebSettingsSetup();
        settingBeforeTests = new WebSettingBeforeTests();
        driver = settingBeforeTests.setBrowserDriverForTest();
        webSettingsSetup.getCookiesCleaning(driver);
        webSettingsSetup.getMaxWebWindowSize(driver);
        webSettingsSetup.getImplicitWait(driver);
        logger.info("The cookies are cleaned and web window is maximized");
        mainPage = new MainPage(webSettingsSetup.getInitialWebSiteWindow(driver, "urlName"));
        logger.info("The main window is opened");
        registrationAndLoginPage = mainPage.clickUserAccountButton();
        logger.info("The main window is opened");
        registrationAndLoginPage.inputEmailFieldForLogin(GetAllPropertiesClass.getPropertyFileValue("logEmail", fileName));
        registrationAndLoginPage.inputPasswordFieldForLogin(GetAllPropertiesClass.getPropertyFileValue("logPassword", fileName));
        homeAccountPage = registrationAndLoginPage.clickSubmitLoginButton();
        logger.info("The form for login is filled in");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        String expectedPageTitle = GetAllPropertiesClass.getPropertyFileValue("HomeAccountPage", fileName);
        wait.until(ExpectedConditions.titleContains(expectedPageTitle));
        homeAccountPage.clickCollectionsButton();
        WebElement element = driver.findElement(By.cssSelector("a[href='/en/collections/occasion']"));
        System.out.println(element.getAttribute("title"));
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        javascript. executeScript("arguments[0]. click();", element);
    }
}
