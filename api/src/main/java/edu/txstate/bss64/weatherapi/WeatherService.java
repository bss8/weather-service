package edu.txstate.bss64.weatherapi;
// WeatherService interface declares a method for obtaining
// weather information.

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * This interface declares methods that will be used by both the client and the server.
 * Implementation will be on the server, the callee side. The call will originate from the client,
 * the caller side.
 *
 */
public interface WeatherService extends Remote {
    /**
     * Implementation is in WeatherServiceServer in the server code.
     * Obtain Vector of WeatherBean objects from server. Each WeatherBean
     * represents weather information for one city.
     *
     * The invocation is in WeatherServiceClient (line 42).
     */
    List<WeatherBean> getWeatherInformation() throws RemoteException;
}