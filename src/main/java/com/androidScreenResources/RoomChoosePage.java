package com.androidScreenResources;

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

public class RoomChoosePage extends ScreenBaseInit {

    @AndroidFindBy(id = "com.goibibo:id/txtPlus")
    private WebElement roomNumberPlus;

    @AndroidFindBy(id = "com.goibibo:id/txtPlusA")
    private WebElement adultCountPlus;

    @AndroidFindBy(id = "com.goibibo:id/txtPlusC")
    private WebElement childCountPlus;

    private By childAgeButtons = By.id("com.goibibo:id/lytChildAgeParent");

    @AndroidFindBy(id = "com.goibibo:id/lytDoneBtn")
    private WebElement doneButton;

    private WebDriverWait wait = new WebDriverWait(getScreenDriver(),10);

    public static Logger logger = LogManager.getLogger(RoomChoosePage.class.getName());

    public RoomChoosePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setRoomNumberPlus() {
        wait.until(ExpectedConditions.visibilityOf(doneButton));
        roomNumberPlus.click();
    }

    public void setAdultCountPlus() {

        adultCountPlus.click();
    }

    public void setChildCountPlus() {

        childCountPlus.click();
        int x = childCountPlus.getLocation().x;
        int y = childCountPlus.getLocation().y;
    }

    public HotelsSearchPage clickConfirmRoomButton() {

        getScreenDriver().findElement(By.id("com.goibibo:id/lytDoneBtn")).click();
        return new HotelsSearchPage(getScreenDriver());
    }
}
