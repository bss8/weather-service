package edu.txstate.bss64.weatherclient;

import edu.txstate.bss64.weatherapi.WeatherBean;
import edu.txstate.bss64.weatherapi.WeatherService;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

/**
 * WeatherServiceClient uses the WeatherService remote object
 * to retrieve weather information.
 */
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
            List<WeatherBean> weatherInformation = new ArrayList<>(
                    stub.getWeatherInformation());

            // create WeatherListModel for weather information
            ListModel<WeatherBean> weatherListModel =
                    new WeatherListModel(weatherInformation);

            // create JList, set ListCellRenderer and add to layout
            JList<WeatherBean> weatherJList = new JList<>(weatherListModel);
            weatherJList.setCellRenderer(new WeatherCellRenderer());
            getContentPane().add(new JScrollPane(weatherJList));
        } // end try

        // handle exception connecting to remote server
        catch (ConnectException connectionException) {
            System.out.println("Connection to server failed. " +
                    "Server may be temporarily unavailable.");
            connectionException.printStackTrace();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    } // end WeatherServiceClient constructor

    /**
     * execute WeatherServiceClient
     * @param args
     */
    public static void main(String[] args) {
        WeatherServiceClient client;
        System.setSecurityManager(new SecurityManager());

        // if no sever IP address or host name specified,
        // use "localhost"; otherwise use specified host
        if (args.length == 0) {
            client = new WeatherServiceClient("localhost");
        } else {
            client = new WeatherServiceClient(args[0]);
        }

        // configure and display application window
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.pack();
        client.setResizable(true);
        client.setVisible(true);
    }
}