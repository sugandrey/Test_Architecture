package com.androidScreenResources;

import com.propertiesreceiver.GetAllPropertiesClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LocationChoosePage extends ScreenBaseInit {

    @AndroidFindBy(id = "com.goibibo:id/edtSearch")
    private WebElement chooseTextField;
    private static String locationHotelChoose = GetAllPropertiesClass.getPropertyFileValue("locationOfHotelSearch","androidMobileSettings.properties");
    private static String locationChooseFromGiven = GetAllPropertiesClass.getPropertyFileValue("locationChooseFromGiven","androidMobileSettings.properties");
    @AndroidFindBy(id = "com.goibibo:id/txtPlace")
    private List<WebElement> chooseLocations;

    public LocationChoosePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setTextToChooseTextField() {

        chooseTextField.sendKeys(locationHotelChoose);

    }
    public HotelsSearchPage getChooseLocation() {

        for (WebElement element : chooseLocations) {

            if(element.getText().contains(locationChooseFromGiven)) {
                element.click();
                break;
            }
        }
        return new HotelsSearchPage(getScreenDriver());
    }


}
