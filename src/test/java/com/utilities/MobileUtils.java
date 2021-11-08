package com.utilities;

import com.propertiesreceiver.GetAllPropertiesClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileUtils {

    private static AppiumDriver<MobileElement> driver;
    private static URL serverURL;
    private static String AndroidPropertiesFileName = Constants.PROPERTIES_FILE_NAME;
    private static DesiredCapabilities capabilities = new DesiredCapabilities();
    private static String APPIUM_PORT;
    private static int IMPLICIT_WAIT_TIME;
    public static int EXPLICIT_WAIT_TIME;
    private static String BASE_PKG;
    private static String APP_ACTIVITY;
    private static String APP_PATH;
    private static String BROWSER_NAME;
    private static String PLATFORM_NAME;
    private static String PLATFORM_VERSION;
    private static String DEVICE_NAME;
    private static String NO_RESET_CAP;
    private static String UDID; // FOR iOS
    private static String BUNDLE_ID;// FOR iOS
    private static String APP;// FOR iOS

    private static void loadAndroidConfProp() {

        IMPLICIT_WAIT_TIME = Integer.parseInt(GetAllPropertiesClass.getPropertyFileValue("implicit.wait", AndroidPropertiesFileName));
        EXPLICIT_WAIT_TIME = Integer.parseInt(GetAllPropertiesClass.getPropertyFileValue("explicit.wait", AndroidPropertiesFileName));
        BASE_PKG = GetAllPropertiesClass.getPropertyFileValue("base.pkg", AndroidPropertiesFileName);
        APP_ACTIVITY = GetAllPropertiesClass.getPropertyFileValue("app.activity", AndroidPropertiesFileName);
//        BROWSER_NAME = GetAllPropertiesClass.getPropertyFileValue("browser.name", AndroidPropertiesFileName);
        PLATFORM_NAME = GetAllPropertiesClass.getPropertyFileValue("platform.name", AndroidPropertiesFileName);
        PLATFORM_VERSION = GetAllPropertiesClass.getPropertyFileValue("platform.version", AndroidPropertiesFileName);
        DEVICE_NAME = GetAllPropertiesClass.getPropertyFileValue("device.name", AndroidPropertiesFileName);
        APPIUM_PORT = GetAllPropertiesClass.getPropertyFileValue("appium.server.port", AndroidPropertiesFileName);
        APP_PATH = GetAllPropertiesClass.getPropertyFileValue("application.path", AndroidPropertiesFileName);
        NO_RESET_CAP = GetAllPropertiesClass.getPropertyFileValue("noReset.cap", AndroidPropertiesFileName);

    }

    private static void setAndroidCapabilities() {

        loadAndroidConfProp();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, BROWSER_NAME);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, BASE_PKG);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, NO_RESET_CAP);

    }

    public static void setIOSCapabilities() {

    }
    public static AppiumDriver<MobileElement> getAndroidDriver() {
            setAndroidCapabilities();
        try {
            serverURL = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AndroidDriver<MobileElement>(serverURL, capabilities);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        return driver;
    }

    public static AppiumDriver<MobileElement> getIOSdDriver() {

        return driver;
    }

}
