package edu.txstate.bss64.weatherapi;// WeatherBean.java
// WeatherBean maintains weather information for one city.
//package com.deitel.advjhtp1.rmi.weather;

// Java core packages

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class WeatherBean implements Serializable {

    //  private static final long serialVersionUID = 1;
    private String cityName;         // name of city
    private String temperature;      // city's temperature
    private String description;      // weather description
    private ImageIcon image;         // weather image

    private static Properties imageNames;

    // initialize imageNames when class WeatherInfo
    // is loaded into memory
    static {
        imageNames = new Properties(); // create properties table

        // load weather descriptions and image names from
        // properties file
        try {
            // obtain URL for properties file
            //URL url = WeatherBean.class.getResource(
            //        "imagenames.properties");
            File file = new File(WeatherBean.class.getResource("/imagenames.properties").toURI());
            // load properties file contents
            imageNames.load(new FileInputStream(file));
        }

        // process exceptions from opening file
        catch (IOException | URISyntaxException ioException) {
            ioException.printStackTrace();
        }

    } // end static block

    // WeatherBean constructor
    public WeatherBean(String city, String weatherDescription,
                       String cityTemperature) {
        cityName = city;
        temperature = cityTemperature;
        description = weatherDescription.trim();


        System.err.println("Update weather bean: City=" + city + "temp=" + cityTemperature + "condition=" + weatherDescription);

        URL url = WeatherBean.class.getResource("/images/" +
                imageNames.getProperty(description, "noinfo.jpg"));

        // get weather image name or noinfo.jpg if weather
        // description not found
        image = new ImageIcon(url);
    }

    // get city name
    public String getCityName() {
        return cityName;
    }

    // get temperature
    public String getTemperature() {
        return temperature;
    }

    // get weather description
    public String getDescription() {
        return description;
    }

    // get weather image
    public ImageIcon getImage() {
        return image;
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
