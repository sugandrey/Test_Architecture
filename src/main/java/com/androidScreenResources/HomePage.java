package com.androidScreenResources;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends ScreenBaseInit {

    @AndroidFindBy(id = "com.goibibo:id/icon")
private List<WebElement> SearchButtons;

    @AndroidFindBy(id = "com.google.android.gms:id/cancel")
    private WebElement phoneCancel;

    @AndroidFindBy(id = "com.goibibo:id/buttonSkip")
    private WebElement skipAuthentication;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public HotelsSearchPage pushHotelSearchButton() {

        phoneCancel.click();
        pressBack();
        skipAuthentication.click();
        MobileElement hotelSearchButton = (MobileElement) SearchButtons.get(0);
        hotelSearchButton.click();

        return new HotelsSearchPage(getScreenDriver());

    }
}
