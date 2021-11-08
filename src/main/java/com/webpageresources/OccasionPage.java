package com.webpageresources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OccasionPage {

    private static WebDriver driver;
    private By firstItemToBuy = By.cssSelector("img[title='GUN']");


    public OccasionPage(WebDriver driver) {

        this.driver = driver;

    }

    public void chooseFirstItem() {

        driver.findElements(firstItemToBuy).get(0).click();
    }
}
