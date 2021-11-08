package com.webapptestcases;

import com.propertiesreceiver.GetAllPropertiesClass;
import com.utilities.*;
import com.webinitialization.WebSettingBeforeTests;
import com.webinitialization.WebSettingsSetup;
import com.webpageresources.HomeAccountPage;
import com.webpageresources.MainPage;
import com.webpageresources.RegistrationAndLoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class LoginAsCustomerTestCase extends WebSettingsSetup {

    MainPage mainPage;
    WebDriver driver;
    WebSettingBeforeTests settingBeforeTests;
    RegistrationAndLoginPage registrationAndLoginPage;
    HomeAccountPage homeAccountPage;
    ExcelReader excelReader = new ExcelReader(Constants.SUITE_XL_PATH);
    private static int rowIterate = 2;
    private static Logger logger = LogManager.getLogger(LoginAsCustomerTestCase.class.getName());
    private static String fileName = "webSetting.properties";


    @BeforeMethod
    public void getMainPage() {
        settingBeforeTests = new WebSettingBeforeTests();
        driver = settingBeforeTests.setBrowserDriverForTest();
        getCookiesCleaning(driver);
        getMaxWebWindowSize(driver);
        getImplicitWait(driver);
        logger.info("The cookies are cleaned and web window is maximized");

        mainPage = new MainPage(getInitialWebSiteWindow(driver,"urlName"));
        registrationAndLoginPage = mainPage.clickUserAccountButton();
        logger.info("The main window is opened");
    }

    @Test(priority = 1, dataProviderClass = DataProviders.class, dataProvider = "registrationData")
    public void loginWithCredentials(Hashtable<String, String> data) {

        String testCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println(testCaseName);
        DataUtil.checkExecution("loginWithCredentials",data.get("RunMode"),excelReader);
        registrationAndLoginPage.inputEmailFieldForLogin(data.get("Email"));
        registrationAndLoginPage.inputPasswordFieldForLogin(data.get("Password"));
        homeAccountPage = registrationAndLoginPage.clickSubmitLoginButton();
        logger.info("The form for login is filled in");
        WebDriverWait wait = new WebDriverWait(driver,5);
        String expectedPageTitle = GetAllPropertiesClass.getPropertyFileValue("HomeAccountPage", fileName);
        try {
            wait.until(ExpectedConditions.titleContains(expectedPageTitle));
        }catch (TimeoutException e) {
            String actualPageTitle = setEntryToExcelFile(testCaseName);
            Assert.assertEquals(actualPageTitle, expectedPageTitle);
        }
        String actualPageTitle = setEntryToExcelFile(testCaseName);
        Assert.assertEquals(actualPageTitle, expectedPageTitle);
        homeAccountPage.logOutClickButton();

    }

    @AfterMethod
    public void quitDriver() {
        settingBeforeTests.setTeardownFromBrowserAfterTest();
    }

    private String setEntryToExcelFile(String testCaseName) {

        String actualPageTitle = homeAccountPage.getTitleHomeAccountPage();
        int rowNumber = AddDataToExcel.getNumberStartTitleRow(testCaseName, Constants.DATA_SHEET) +rowIterate;
        AddDataToExcel.getRowNumberForNewEntry(rowNumber, homeAccountPage, testCaseName);
        rowIterate++;
        return actualPageTitle;

    }
}
