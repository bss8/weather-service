package edu.txstate.bss64.weatherclient;
// WeatherCellRenderer is a custom ListCellRenderer for 
// WeatherBeans in a JList.

import javax.swing.*;
import java.awt.*;

import edu.txstate.bss64.weatherapi.WeatherBean;

/**
 *
 */
public class WeatherCellRenderer extends DefaultListCellRenderer {
    /**
     * returns a WeatherItem object that displays city's weather
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param focus
     * @return
     */
    public Component getListCellRendererComponent
    (JList list, Object value, int index, boolean isSelected, boolean focus) {
        return new WeatherItem((WeatherBean) value);
    }
}
