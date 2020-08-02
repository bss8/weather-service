package edu.txstate.bss64.weatherclient;// WeatherServiceClient.java
// WeatherServiceClient uses the WeatherService remote object
// to retrieve weather information.
//package com.deitel.advjhtp1.rmi.weather;

// Java core packages

import edu.txstate.bss64.weatherapi.WeatherService;

import javax.swing.*;
import java.rmi.ConnectException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class WeatherServiceClient extends JFrame {
    // WeatherServiceClient constructor
    public WeatherServiceClient(String server) {
        super("RMI WeatherService Client");


        // connect to server and get weather information
        try {

            Registry registry = LocateRegistry.getRegistry(server, 1099);
            WeatherService stub = (WeatherService) registry.lookup("WeatherService");

            // name of remote server object bound to rmi registry
            //String remoteName = "rmi://" + server + "/WeatherService";

            // lookup WeatherServiceImpl remote object
            //WeatherService weatherService =
            //   ( WeatherService ) Naming.lookup( remoteName );

            // get weather information from server
            List weatherInformation = new ArrayList(
                    stub.getWeatherInformation());

            // create WeatherListModel for weather information
            ListModel weatherListModel =
                    new WeatherListModel(weatherInformation);

            // create JList, set ListCellRenderer and add to layout
            JList weatherJList = new JList(weatherListModel);
            weatherJList.setCellRenderer(new WeatherCellRenderer());
            getContentPane().add(new JScrollPane(weatherJList));

        } // end try

        // handle exception connecting to remote server
        catch (ConnectException connectionException) {
            System.err.println("Connection to server failed. " +
                    "Server may be temporarily unavailable.");

            connectionException.printStackTrace();
        }

        // handle exceptions communicating with remote object
        catch (Exception exception) {
            exception.printStackTrace();
        }

    } // end WeatherServiceClient constructor

    // execute WeatherServiceClient
    public static void main(String args[]) {
//      System.setProperty("java.security.policy", "file:./policy-all.txt");
        WeatherServiceClient client = null;
        System.setSecurityManager(new SecurityManager());

        // if no sever IP address or host name specified,
        // use "localhost"; otherwise use specified host
        if (args.length == 0)
            client = new WeatherServiceClient("localhost");
        else
            client = new WeatherServiceClient(args[0]);

        // configure and display application window
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.pack();
        client.setResizable(true);
        client.setVisible(true);
    }
}

/**************************************************************************
 * (C) Copyright 2001 by Deitel & Associates, Inc. and Prentice Hall.     *
 * All Rights Reserved.                                                   *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
