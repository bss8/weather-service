package edu.txstate.bss64.weatherclient;

import edu.txstate.bss64.weatherapi.WeatherBean;

import javax.swing.AbstractListModel;
import java.util.ArrayList;
import java.util.List;

/**
 * WeatherListModel extends AbstractListModel to provide a
 * ListModel for storing a List of WeatherBeans.
 */
public class WeatherListModel extends AbstractListModel<WeatherBean> {
    private static final long serialVersionUID = 1;

    // List of elements in ListModel
    private final List<WeatherBean> list;

    /**
     * no-argument WeatherListModel constructor
     */
    public WeatherListModel() {
        // create new List for WeatherBeans
        list = new ArrayList<>();
    }

    /**
     * WeatherListModel constructor
     * @param itemList List of WeatherBeans to set the model
     */
    public WeatherListModel(List<WeatherBean> itemList) {
        list = itemList;
    }

    /**
     * get size of List
     * @return size of the list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * get Object reference to element at given index
     * @param index position of city's WeatherBean in the list
     * @return the WeatherBean at the specified position
     */
    public WeatherBean getElementAt(int index) {
        return list.get(index);
    }

    /**
     * add element to WeatherListModel
     * @param element a WeatherBean with city weather info
     */
    public void add(WeatherBean element) {
        list.add(element);
        fireIntervalAdded(this, list.size(), list.size());
    }

    /**
     * remove element from WeatherListModel
     * @param element a WeatherBean to be removed from the list
     */
    public void remove(WeatherBean element) {
        int index = list.indexOf(element);

        if (index != -1) {
            list.remove(element);
            fireIntervalRemoved(this, index, index);
        }
    } // end method remove

    /**
     * remove all elements from WeatherListModel
     */
    public void clear() {
        // get original size of List
        int size = list.size();

        // clear all elements from List
        list.clear();

        // notify listeners that content changed
        fireContentsChanged(this, 0, size);
    }
}
