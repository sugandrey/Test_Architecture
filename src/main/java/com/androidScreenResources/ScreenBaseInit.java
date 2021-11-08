package com.androidScreenResources;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ScreenBaseInit {

    private static AppiumDriver<MobileElement> driver;

    public ScreenBaseInit(AppiumDriver<MobileElement> driver) {

        ScreenBaseInit.driver = driver;
    }
    public void hideKeyBoard() {
        driver.hideKeyboard();
    }
    public void pressEnter() {

        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
    public void pressBack() {

        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void pressTab() {

        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.TAB));
    }

    public static AppiumDriver<MobileElement> getScreenDriver() {
        return driver;
    }
}
