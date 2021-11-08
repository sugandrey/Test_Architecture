package com.webpageresources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeAccountPage {

    private static WebDriver driver;
    private static By logOutButton = By.className("account-menu-sign-out");
    private static By collectionsButton = By.id("collections");

    public HomeAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleHomeAccountPage() {
        return driver.getTitle();
    }

    public void logOutClickButton() {

        driver.findElement(logOutButton).click();
    }

    public void clickCollectionsButton() {

        driver.findElement(collectionsButton).click();

    }




}
