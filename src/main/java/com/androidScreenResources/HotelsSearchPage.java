package com.androidScreenResources;

import com.propertiesreceiver.GetAllPropertiesClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class HotelsSearchPage extends ScreenBaseInit {

//    @AndroidFindBy(className = "")
//    private List<WebElement> chooseButtons;
    By chooseButtons = By.className("android.widget.TextView");
    private static String internationalHotelsButton = GetAllPropertiesClass.getPropertyFileValue("internationalButton","androidMobileSettings.properties");
    private static String whereSearchField = GetAllPropertiesClass.getPropertyFileValue("locationSearchField","androidMobileSettings.properties");
    private static String searchButton = GetAllPropertiesClass.getPropertyFileValue("searchButton","androidMobileSettings.properties");
    public static Logger logger = LogManager.getLogger(HotelsSearchPage.class.getName());

    public HotelsSearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private WebElement chooseActionsButton(String nameButton) {

        WebElement chooseElement = null;
        waitForElements(chooseButtons);
        List<MobileElement> buttons = getScreenDriver().findElements(chooseButtons);
        int count = 0;
        for (WebElement element : buttons) {
            count++;
            if (element.getText().equalsIgnoreCase("1 Room")) {
                logger.info("The element rooms' count and count of guests has index = " + count);
            }
            if (element.getText().equalsIgnoreCase(nameButton)) {
                chooseElement = element;

                logger.info("The element Dates of booking has index =  = " + count);

                break;
            }

        }
        return chooseElement;
    }

        public LocationChoosePage pushIntHotelsButton() {

            chooseActionsButton(internationalHotelsButton).click();
            chooseActionsButton(whereSearchField).click();
            return new LocationChoosePage(getScreenDriver());
        }

        public DatesChoosePage getDateChooseButton() {
            waitForElements(chooseButtons);
            getScreenDriver().findElements(chooseButtons).get(6).click();
            //chooseActionsButton(dateButton).click();
            return new DatesChoosePage(getScreenDriver());
        }

        public RoomChoosePage getRoomChooseButton() {
            waitForElements(chooseButtons);
            getScreenDriver().findElements(chooseButtons).get(9).click();
            return new RoomChoosePage(getScreenDriver());
        }

        protected static void waitForElements(By elements) {
            WebDriverWait wait = new WebDriverWait(getScreenDriver(), 10);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elements));
        }

        public void confirmHotelChooseButton() {
        chooseActionsButton(searchButton).click();
        }

    }
