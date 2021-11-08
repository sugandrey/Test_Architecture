package com.utilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumServerInit {

    private  static AppiumDriverLocalService service;

    public static void startAppiumServer() {
        service =
                AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe")).withAppiumJS(
                                new File("C:\\Users\\user\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
                        .withLogFile(new File(System.getProperty("user.dir")+"\\target\\reports\\logs\\appiumServerStartLog.txt"))
                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));
        service.start();
    }

    public static void stopAppiumServer() {

        service.stop();
    }
}
