package com.androidScreenResources;

import com.propertiesreceiver.GetAllPropertiesClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DatesChoosePage extends ScreenBaseInit {

    public static Logger logger = LogManager.getLogger(DatesChoosePage.class.getName());
    private static String chosenMonth = GetAllPropertiesClass.getPropertyFileValue("month","androidMobileSettings.properties");




    private By chooseDaysForHotelBooking = By.id("com.goibibo:id/tvDay");
    @AndroidFindBy(id = "com.goibibo:id/tvMonthName")
    private List<WebElement> chooseMonthForHotelBooking;

    @AndroidFindBy(id = "com.goibibo:id/btnCalendar")
    private WebElement continueButton;

    public DatesChoosePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<WebElement> ChooseMonthForHotelBooking() {

//        for (WebElement month : chooseMonthForHotelBooking) {
//            if(month.getText().equalsIgnoreCase(chosenMonth)) {
//                logger.info("Visibility of the month of year 2022 January is " + ExpectedConditions.visibilityOf(month));
//                for (WebElement day : chooseDaysForHotelBooking) {
//                    if (day.getText().equalsIgnoreCase(chosenStartDay)) {
//                        day.click();
//                        if (day.getText().equalsIgnoreCase(chosenFinishedDay)) {
//                            day.click();
//                            break;
//                        }
//                    }
//                }
//            }
        return chooseMonthForHotelBooking;
    }

    public void chooseDayForHotelBooking(String chosenDay) {
        WebDriverWait wait = new WebDriverWait(getScreenDriver(), 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(chooseDaysForHotelBooking));
        List<MobileElement> days = getScreenDriver().findElements(chooseDaysForHotelBooking);
        for (WebElement day : days) {
            if (day.getText().equalsIgnoreCase(chosenDay)) {
                day.click();
                break;
            }
        }
    }

    public HotelsSearchPage clickContinueButton() {

        continueButton.click();
        return new HotelsSearchPage(getScreenDriver());
    }


}
