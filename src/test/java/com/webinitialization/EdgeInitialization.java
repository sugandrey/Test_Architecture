package com.webinitialization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeInitialization {


    private WebDriver driver;

    public WebDriver getEdgeBrowser() {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        return driver;
    }

    public void teardownEdge() {
        if (driver != null) {
            driver.quit();
        }
    }

}
