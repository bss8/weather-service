package edu.txstate.bss64.weatherclient;
// WeatherItem displays a city's weather information in a JPanel.

import edu.txstate.bss64.weatherapi.WeatherBean;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;

/**
 *
 */
public class WeatherItem extends JPanel {
    private static final long serialVersionUID = 1;
    private final WeatherBean weatherBean;  // weather information

    // background ImageIcon
    private static final ImageIcon backgroundImage;

    // static initializer block loads background image when class
    // WeatherItem is loaded into memory
    static {
        // get URL for background image
        URL url = WeatherItem.class.getResource("/images/back.jpg");

        // background image for each city's weather info
        backgroundImage = new ImageIcon(url);
    }

    /**
     * initialize a WeatherItem
     * @param bean WeatherBean with info for cities' weather
     */
    public WeatherItem(WeatherBean bean) {
        weatherBean = bean;
    }

    /**
     * display information for city's weather
     * @param g a Graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw background
        backgroundImage.paintIcon(this, g, 0, 0);

        // set font and drawing color,
        // then display city name and temperature
        Font font = new Font("SansSerif", Font.BOLD, 12);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(weatherBean.getCityName(), 10, 19);
        g.drawString(weatherBean.getTemperature(), 130, 19);
        g.drawString(weatherBean.getPrecipitation(), 175, 19);
        // display weather image
        weatherBean.getImage().paintIcon(this, g, 253, 1);
    } // end method paintComponent

    /**
     * make WeatherItem's preferred size the width and height of
     *     the background image
     * @return dimensions of background image
     */
    public Dimension getPreferredSize() {
        return new Dimension(backgroundImage.getIconWidth(),
                backgroundImage.getIconHeight());
    }
} // end class WeatherItem