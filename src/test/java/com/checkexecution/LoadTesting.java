package com.checkexecution;

import com.webinitialization.WebSettingBeforeTests;
import com.webpageresources.HomeAccountPage;
import com.webpageresources.MainPage;
import com.webpageresources.RegistrationAndLoginPage;
import com.propertiesreceiver.GetAllPropertiesClass;
import com.webinitialization.WebSettingsSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Date;

public class LoadTesting extends WebSettingsSetup {

    private MainPage homePage;
    private WebDriver driver;
    private WebSettingBeforeTests settingBeforeTests;
    private RegistrationAndLoginPage registrationAndLoginPage;
    private HomeAccountPage homeAccountPage;
    private static Logger logger = LogManager.getLogger(LoadTesting.class.getName());
    private static String fileName = "webSetting.properties";
    int count;

    public LoadTesting(int count) {
        this.count = count;
    }

    public synchronized void getHomePage() {
        settingBeforeTests = new WebSettingBeforeTests();
        driver = settingBeforeTests.setBrowserDriverForTest();
        getCookiesCleaning(driver);
        getMaxWebWindowSize(driver);
        getImplicitWait(driver);
        homePage = new MainPage(getInitialWebSiteWindow(driver,"urlName"));
    }
    public synchronized void fillRegistrationForm() {

        registrationAndLoginPage = homePage.clickUserAccountButton();
        registrationAndLoginPage.inputFirstName("FirstName");
        registrationAndLoginPage.inputLastName("LastName");
        registrationAndLoginPage.inputRegEmail(getUniqueEmail());
        registrationAndLoginPage.inputRegPassword("Password");

        homeAccountPage = registrationAndLoginPage.clickCreateAccountButton();
        logger.info("The form for registration is filled in");

    }
    public String getUniqueEmail() {
        String email = GetAllPropertiesClass.getPropertyFileValue("emailforloadtest", fileName);
        return email+count+"@gmail.com";
    }

    public void quitDriver() {
        settingBeforeTests.setTeardownFromBrowserAfterTest();
    }

    public void testMethod() {
        System.out.println("This thread is " + Thread.currentThread().getName() + " : " + new Date());
    }
}
