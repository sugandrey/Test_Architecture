package com.webpageresources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    private By userAccountButton = By.cssSelector("a[title='Login']");
    private WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public RegistrationAndLoginPage clickUserAccountButton() {
        wait = new WebDriverWait(driver,5);
        WebElement element = driver.findElement(userAccountButton);
        wait.until(ExpectedConditions.visibilityOf(element)).click();
        return new RegistrationAndLoginPage(driver);
    }
}
