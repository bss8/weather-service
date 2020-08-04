package edu.txstate.bss64.weatherapi;
// WeatherBean maintains weather information for one city.

import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 *
 */
public class WeatherBean implements Serializable {
    private final String cityName;         // name of city
    private final String temperature;      // city's temperature
    private final String description;      // weather description
    private final String precipitation;    // precipitation
    private final ImageIcon image;         // weather image

    private static final Properties imageNames;

    // initialize imageNames when class WeatherInfo
    // is loaded into memory
    static {
        imageNames = new Properties(); // create properties table

        // load weather descriptions and image names from
        // properties file
        try {
            // obtain URL for properties file
            File file = new File(WeatherBean.class.getResource("/imagenames.properties").toURI());
            // load properties file contents
            imageNames.load(new FileInputStream(file));
        }
        // process exceptions from opening file
        catch (IOException | URISyntaxException ioException) {
            ioException.printStackTrace();
        }
    } // end static block

    /**
     * WeatherBean constructor
     *
     * @param city
     * @param weatherDescription
     * @param cityTemperature
     */
    public WeatherBean(String city, String weatherDescription,
                       String cityTemperature, String chanceForRain) {
        this.cityName = city;
        this.temperature = cityTemperature;
        this.description = weatherDescription.trim();
        this.precipitation = chanceForRain.trim();

        System.out.println("Update weather bean: City=" + city + "temp=" + cityTemperature +
                "condition=" + weatherDescription + "prcpt:" + precipitation);

        URL url = WeatherBean.class.getResource("/images/" +
                imageNames.getProperty(description, "noinfo.jpg"));

        // get weather image name or noinfo.jpg if weather description not found
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

    // get precipitation
    public String getPrecipitation() { return precipitation; }

    // get weather image
    public ImageIcon getImage() {
        return image;
    }
} // end WeatherBean