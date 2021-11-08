package com.utilities;

import com.propertiesreceiver.GetAllPropertiesClass;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadAPKFile {

    private static MobileDriver<MobileElement> driver;
    private static String fileName = "androidMobileSettings.properties";

    public static void runAppiumServerAndDownloadFile() {

        String appName = GetAllPropertiesClass.getPropertyFileValue("appfordownload", fileName);
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withAppiumJS(new File("C:\\Users\\user\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
                .withLogFile(new File(System.getProperty("user.dir") + "\\target\\reports\\logs\\appiumServerLog.txt"))
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));
        service.start();
        File app = new File(".\\src\\main\\resources\\" + appName);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.quit();
        service.stop();
    }

}
