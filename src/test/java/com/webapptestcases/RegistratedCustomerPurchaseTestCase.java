package com.webapptestcases;

import com.propertiesreceiver.GetAllPropertiesClass;
import com.webinitialization.WebSettingBeforeTests;
import com.webinitialization.WebSettingsSetup;
import com.webpageresources.HomeAccountPage;
import com.webpageresources.MainPage;
import com.webpageresources.OccasionPage;
import com.webpageresources.RegistrationAndLoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistratedCustomerPurchaseTestCase extends WebSettingsSetup {

    private WebSettingBeforeTests settingBeforeTests;
    private static WebDriver driver;
    private MainPage mainPage;
    private static Logger logger = LogManager.getLogger(LoginAsCustomerTestCase.class.getName());
    private static RegistrationAndLoginPage registrationAndLoginPage;
    private static String fileName = "webSetting.properties";
    private static HomeAccountPage homeAccountPage;
    private static OccasionPage occasionPage;


    @BeforeTest
    public void getMainPage() {

        settingBeforeTests = new WebSettingBeforeTests();
        driver = settingBeforeTests.setBrowserDriverForTest();
        getCookiesCleaning(driver);
        getMaxWebWindowSize(driver);
        getImplicitWait(driver);
        logger.info("The cookies are cleaned and web window is maximized");
        mainPage = new MainPage(getInitialWebSiteWindow(driver, "urlName"));
        logger.info("The main window is opened");
        registrationAndLoginPage = mainPage.clickUserAccountButton();
        logger.info("The main window is opened");
    }

    @Test(priority = 1)
    public void doLoginIntoAccount() {
        registrationAndLoginPage.inputEmailFieldForLogin(GetAllPropertiesClass.getPropertyFileValue("logEmail", fileName));
        registrationAndLoginPage.inputPasswordFieldForLogin(GetAllPropertiesClass.getPropertyFileValue("logPassword", fileName));
        homeAccountPage = registrationAndLoginPage.clickSubmitLoginButton();
        logger.info("The form for login is filled in");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        String expectedPageTitle = GetAllPropertiesClass.getPropertyFileValue("HomeAccountPage", fileName);
        wait.until(ExpectedConditions.titleContains(expectedPageTitle));

    }

    @Test(priority = 2)
    public void ChooseCollectionsButton() {

        homeAccountPage.clickCollectionsButton();
        WebElement element = driver.findElement(By.cssSelector("a[href='/en/collections/occasion']"));
        System.out.println(element.getAttribute("title"));
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        javascript. executeScript("arguments[0]. click();", element);
        occasionPage = new OccasionPage(driver);
    }

    @AfterTest
    public void quitDriver() {
        settingBeforeTests.setTeardownFromBrowserAfterTest();
    }

}
