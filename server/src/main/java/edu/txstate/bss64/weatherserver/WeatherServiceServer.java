package edu.txstate.bss64.weatherserver;
// WeatherServiceImpl implements the WeatherService remote
// interface to provide a WeatherService remote object.

import edu.txstate.bss64.weatherapi.WeatherBean;
import edu.txstate.bss64.weatherapi.WeatherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class WeatherServiceServer implements WeatherService {

    public WeatherServiceServer() {
        super();
        updateWeatherConditions();
    }

    private List<WeatherBean> weatherInformation;  // WeatherBean objects

    /**
     * get weather information from NWS
     */
    private void updateWeatherConditions() {
        try {
            System.out.println("Update weather information...");

            // National Weather Service Travelers Forecast page
            URL url = new URL(
                    "https://forecast.weather.gov/product.php?site=CRH&product=SCS&issuedby=01");

            // set up text input stream to read Web page contents
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {

                System.out.println("Update weather information...1");

                String separator = "TEMPERATURES INDICATE";
                String line = "";

                for (line = in.readLine(); !line.startsWith(separator); line = in.readLine())
                    System.out.println(line);    // do nothing

                separator = "CITY";

                for (line = in.readLine(); !line.startsWith(separator); line = in.readLine()) {
                    System.out.println(line);    // do nothing
                }

                String inputLine = "";

                System.out.println("Update weather information...2" + inputLine);

                weatherInformation = new ArrayList<>(); // create List

                inputLine = in.readLine();  // skip an empty line
                inputLine = in.readLine();  // first city info line
                System.out.println("Update weather information...3" + inputLine);

                // The portion of inputLine containing relevant data is
                // 45 characters long. If the line length is not at
                // least 40 characters long, done processing data.

                String cityName, temperatures, precipitation, condition;

                System.out.println("Update weather information...4");

                while (inputLine.length() > 10) {
                    System.out.println("Update weather information...3.5" + inputLine);
                    System.out.println("Update weather information...3.6" + inputLine.substring(0, 15) + "ppp");

                    // Create WeatherBean object for city. First 16
                    // characters are city name. Next, six characters
                    // are weather description. Next six characters
                    // are HI/LO or LO/HI temperature.
                    System.out.println("Update weather information...4" + inputLine.substring(0, 16) + "22" + inputLine.substring(16, 24) + "333" + inputLine.substring(24, 32) + "44" + inputLine.substring(40, 45) + "EEE");
                    System.out.println("Update weather information...----11111--" + inputLine.substring(0, 17) + "--2222--" + inputLine.substring(17, 20) + "--333--" + inputLine.substring(21, 23) + "---4444---" + inputLine.substring(24, 32) + "---555---" + inputLine.substring(32, 38) + "----66666----" + inputLine.substring(36, 40) + "----66666----");
                    System.out.println(inputLine.substring(0, 17) + "--" + inputLine.substring(33, 38) + "--" + inputLine.substring(17, 20));

                    cityName = inputLine.substring(0, 16);
                    temperatures = inputLine.substring(16, 23);
                    precipitation = inputLine.substring(23, 32);
                    condition = inputLine.substring(32, 38);

                    cityName = cityName.trim();
                    temperatures = temperatures.trim();
                    condition = condition.trim();

                    System.out.println("City:" + cityName + ", condition:" + condition + ",temp:" + temperatures +
                            ",pcpt:" + precipitation);
                    WeatherBean weather = new WeatherBean(
                            cityName,
                            condition,
                            temperatures,
                            precipitation);

                    // add WeatherBean to List
                    weatherInformation.add(weather);

                    inputLine = in.readLine();  // get next city's info
                }
                System.out.println("Initializing WeatherService...5");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Weather information updated.");
        }

        // process failure to connect to National Weather Service
        catch (MalformedURLException connectException) {
            connectException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * implementation for WeatherService interface method
     *
     * @return
     * @throws RemoteException
     */
    public List<WeatherBean> getWeatherInformation() throws RemoteException {
        return weatherInformation;
    }

    /**
     * launch WeatherService remote object
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.setSecurityManager(new SecurityManager());

            System.out.println("Initializing WeatherService...");

            WeatherServiceServer obj = new WeatherServiceServer();
            WeatherService stub = (WeatherService) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("WeatherService", stub);

            System.out.println("WeatherService running.");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
} // end class WeatherServiceServer