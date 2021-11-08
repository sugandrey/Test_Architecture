package com.utilities;

import com.checkexecution.LoadTesting;
import com.webapptestcases.RegistrationAsCustomerTestCase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class WebServerConnection extends Thread {

    int count;

    public WebServerConnection(int count) {
        this.count = count;
    }

    @Override
    public void run() {

            LoadTesting loadTesting = new LoadTesting(count);
            loadTesting.testMethod();
            loadTesting.getHomePage();
            String email = loadTesting.getUniqueEmail();
            System.out.println(email);
            loadTesting.fillRegistrationForm();
            loadTesting.quitDriver();

    }
}
