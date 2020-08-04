package edu.txstate.bss64.weatherapi;
// WeatherService interface declares a method for obtaining
// wether information.

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 */
public interface WeatherService extends Remote {
    // obtain Vector of WeatherBean objects from server
    List<WeatherBean> getWeatherInformation() throws RemoteException;
}