package com.webinitialization;

import com.httpsconnection.HttpConnectionClass;
import com.propertiesreceiver.GetAllPropertiesClass;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class WebSettingsSetup {

    private static String fileName = "webSetting.properties";

    public WebDriver getInitialWebSiteWindow(WebDriver driver, String urlName) {
        String browserName = driver.getClass().toString();
        String webSiteWindow = GetAllPropertiesClass.getPropertyFileValue(urlName,fileName);

        if (!browserName.contains("InternetExplorerDriver")) {

            driver.get(webSiteWindow);

        } else {

            driver.get(webSiteWindow);
            int response = HttpConnectionClass.getConnectToURLStatus(webSiteWindow);
            if (response > 400) {
                StringSelection selection = new StringSelection(GetAllPropertiesClass.getPropertyFileValue("loginForIE",fileName));
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, null);

                try {

                    Robot robot = new Robot();
                    robot.delay(500);
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.delay(200);
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_TAB);
                    selection = new StringSelection(GetAllPropertiesClass.getPropertyFileValue("passwordForIE", fileName));
                    clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, null);
                    robot.delay(500);
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.delay(200);
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_TAB);
                    robot.delay(200);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);

                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        }
        return driver;
    }

    public WebDriver getMaxWebWindowSize(WebDriver driver) {

        if (!GetAllPropertiesClass.getPropertyFileValue(
                "windowSize", fileName).equals("")) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    public WebDriver getCookiesCleaning(WebDriver driver) {

        if (!GetAllPropertiesClass.getPropertyFileValue(
                "cookiesCleaning", fileName).equals("")) {
            driver.manage().deleteAllCookies();
        }
        return driver;
    }

    public WebDriver getImplicitWait(WebDriver driver) {
        long value = 0;
        if (!GetAllPropertiesClass.getPropertyFileValue(
                "implicitWait", fileName).equals("")) {
            String longValue = GetAllPropertiesClass.getPropertyFileValue(
                    "implicitWait", fileName);
            try {
                value = Long.parseLong(longValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return driver;
            }
            if (value > 0) {
                driver.manage().timeouts().implicitlyWait(value, TimeUnit.SECONDS);
                return driver;
            }
        }
        return driver;
    }
}
