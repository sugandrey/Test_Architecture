package com.propertiesreceiver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetAllPropertiesClass {

    private static String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    private static FileInputStream input;
    private static Properties property = new Properties();


    public static String getPropertyFileValue(String propertyValue, String fileName) {

        String completePath = path + fileName;
        try {
            input = new FileInputStream(completePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            property.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property.getProperty(propertyValue);
    }

}
