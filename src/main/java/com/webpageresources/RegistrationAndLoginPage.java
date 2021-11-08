package com.webpageresources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationAndLoginPage {

    private WebDriver driver;
    private By firstNameField = By.id("registration-form-fname");
    private By lastNameField = By.id("registration-form-lname");
    private By emailRegField = By.id("registration-form-email");
    private By passwordRegField = By.id("registration-form-password");
    private By createAccountButton = By.className("register-submit-btn");
    private By EmailFieldForLogin = By.id("login-form-email");
    private By PasswordFieldForLogin = By.id("login-form-password");
    private By submitLoginButton = By.className("login-submit-btn");

    public RegistrationAndLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void inputRegEmail(String regEmail) {
        driver.findElement(emailRegField).sendKeys(regEmail);
    }

    public void inputRegPassword(String password) {
        driver.findElement(passwordRegField).sendKeys(password);
    }

    public HomeAccountPage clickCreateAccountButton() {
        driver.findElement(createAccountButton).click();
        return new HomeAccountPage(driver);
    }
    public void inputEmailFieldForLogin(String LogEmail) {
        driver.findElement(EmailFieldForLogin).sendKeys(LogEmail);
    }

    public void inputPasswordFieldForLogin(String logPassword) {
        driver.findElement(PasswordFieldForLogin).sendKeys(logPassword);
    }
    public HomeAccountPage clickSubmitLoginButton() {
        driver.findElement(submitLoginButton).click();
        return new HomeAccountPage(driver);
    }

}
