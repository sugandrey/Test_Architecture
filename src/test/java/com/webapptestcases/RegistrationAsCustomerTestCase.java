package com.webapptestcases;

import com.utilities.DataUtil;
import com.webinitialization.WebSettingBeforeTests;
import com.webpageresources.HomeAccountPage;
import com.webpageresources.MainPage;
import com.webpageresources.RegistrationAndLoginPage;
import com.propertiesreceiver.GetAllPropertiesClass;
import com.utilities.*;
import com.webinitialization.WebSettingsSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Hashtable;


public class RegistrationAsCustomerTestCase extends WebSettingsSetup {

    private static MainPage mainPage;
    private static WebDriver driver;
    private WebSettingBeforeTests settingBeforeTests;
    private RegistrationAndLoginPage registrationAndLoginPage;
    private HomeAccountPage homeAccountPage;
    private ExcelReader excelReader = new ExcelReader(Constants.SUITE_XL_PATH);
    private static int rowIterate = 2;
    private static Logger logger = LogManager.getLogger(RegistrationAsCustomerTestCase.class.getName());
    private static String fileName = "webSetting.properties";

    @BeforeMethod
    public void getHomePage() {
        settingBeforeTests = new WebSettingBeforeTests();
        driver = settingBeforeTests.setBrowserDriverForTest();
        getCookiesCleaning(driver);
        getMaxWebWindowSize(driver);
        getImplicitWait(driver);

        mainPage = new MainPage(getInitialWebSiteWindow(driver,"urlName"));
    }

//    @Test(priority = 1, dataProviderClass = DataProviders.class, dataProvider = "registrationData")
//    public void getRegistrationPage(Hashtable<String, String> data) {
//        DataUtil.checkExecution("getRegistrationPage",data.get("RunMode"),excelReader);
//            registrationAndLoginPage = homePage.clickUserAccountButton();
//    }

    @Test(priority = 1, dataProviderClass = DataProviders.class, dataProvider = "registrationData")//, retryAnalyzer = Retry.class)
    public void fillRegistrationForm(Hashtable<String, String> data) {

        String testCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println(testCaseName);
        DataUtil.checkExecution("getRegistrationPage",data.get("RunMode"),excelReader);
        registrationAndLoginPage = mainPage.clickUserAccountButton();
        DataUtil.checkExecution("fillRegistrationForm",data.get("RunMode"),excelReader);

        registrationAndLoginPage.inputFirstName(data.get("FirstName"));
        registrationAndLoginPage.inputLastName(data.get("LastName"));
        registrationAndLoginPage.inputRegEmail(data.get("Email"));
        registrationAndLoginPage.inputRegPassword(data.get("Password"));

        homeAccountPage = registrationAndLoginPage.clickCreateAccountButton();
        logger.info("The form for registration is filled in");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String actualResult = homeAccountPage.getTitleHomeAccountPage();
        String expectedResult = GetAllPropertiesClass.getPropertyFileValue("HomeAccountPage", fileName);
        int rowNumber = AddDataToExcel.getNumberStartTitleRow("fillRegistrationForm", Constants.DATA_SHEET) +rowIterate;
        AddDataToExcel.getRowNumberForNewEntry(rowNumber, homeAccountPage, testCaseName);
        rowIterate++;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @AfterMethod
    public void quitDriver() {
        settingBeforeTests.setTeardownFromBrowserAfterTest();
    }
}
