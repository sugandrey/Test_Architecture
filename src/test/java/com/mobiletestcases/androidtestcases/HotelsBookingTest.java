package com.mobiletestcases.androidtestcases;

import com.androidScreenResources.*;
import com.extentlisteners.ExtentManager;
import com.mobileinitialization.MobileTestBase;
import com.propertiesreceiver.GetAllPropertiesClass;
import com.utilities.AppiumServerInit;
import com.utilities.ScrollUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class HotelsBookingTest extends MobileTestBase {

    private HomePage loginPage;
    private HotelsSearchPage hotelsSearchPage;
    private LocationChoosePage locationChoosePage;
    private DatesChoosePage datesChoosePage;
    private RoomChoosePage roomChoosePage;
    private static String chosenMonth = GetAllPropertiesClass.getPropertyFileValue("month", "androidMobileSettings.properties");
    private static String chosenStartDay = GetAllPropertiesClass.getPropertyFileValue("daybegin", "androidMobileSettings.properties");
    private static String chosenFinishedDay = GetAllPropertiesClass.getPropertyFileValue("dayFinish", "androidMobileSettings.properties");

    @BeforeTest
    public void initDriver() {
        appiumServerSetup();
        loginPage = new HomePage(getDriver());
        //hotelsSearchPage = new HotelsSearchPage(getDriver());

    }

    @Test(priority = 1)
    public void clickCreateAccountButton() {
        hotelsSearchPage = loginPage.pushHotelSearchButton();
    }

    @Test(priority = 2)
    public void clickSearchIntHotelsButton() {
        locationChoosePage = hotelsSearchPage.pushIntHotelsButton();
    }

    @Test(priority = 3)
    public void textToChooseTextField() {
        locationChoosePage.setTextToChooseTextField();
    }

    @Test(priority = 4)
    public void chooseLocationFromGiven() {
        hotelsSearchPage = locationChoosePage.getChooseLocation();
    }

    @Test(priority = 5)
    public void getPageOfDateHotelBooking() {

        datesChoosePage = hotelsSearchPage.getDateChooseButton();
    }

    @Test(priority = 6)
    public void setDatesOfHotelBooking() {
        for (WebElement element : datesChoosePage.ChooseMonthForHotelBooking()) {
            if (!element.getText().equalsIgnoreCase(chosenMonth)) {
                ScrollUtil.scrollDown((AndroidDriver<MobileElement>) getDriver());
            }
        }
        datesChoosePage.chooseDayForHotelBooking(chosenStartDay);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        datesChoosePage.chooseDayForHotelBooking(chosenFinishedDay);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        datesChoosePage.clickContinueButton();

    }

    @Test(priority = 7)
    public void clickRoomChooseButton() {
        roomChoosePage = hotelsSearchPage.getRoomChooseButton();
//        ExtentManager.captureScreenshotForMobile();
    }

    @Test(priority = 8)
    public void chooseRoomSettings() {
        roomChoosePage.setRoomNumberPlus();
        roomChoosePage.setAdultCountPlus();
        roomChoosePage.setChildCountPlus();
        hotelsSearchPage = roomChoosePage.clickConfirmRoomButton();
    }

    @Test(priority = 9)
    public void pushHotelButton() {
        hotelsSearchPage.confirmHotelChooseButton();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExtentManager.captureScreenshotForMobile();
    }

    @AfterTest
    public void quitDriver() {
        quit();
    }

}
