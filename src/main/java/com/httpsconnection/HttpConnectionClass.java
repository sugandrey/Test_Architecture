package com.httpsconnection;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnectionClass {

    public static int getConnectToURLStatus(String url) {
        int response = 0;
        try {
            URL webAddress = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) webAddress.openConnection();
            connection.setRequestMethod("GET");
            response = connection.getResponseCode();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
            System.out.println("The response is " + response);
        return response;

    }
}
