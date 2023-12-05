/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INTERFACE;

import DTO.Flight;
import java.util.ArrayList;

/**
 *
 * @author RLappc.com
 */
public interface IFlight {

    public ArrayList<Flight> searchFlights(String departureCity, String destinationCity, String departureDate);

    public boolean createFlight(Flight input);

    public boolean updateFlight(Flight input);

    public boolean removeFlight(String id);

    public boolean saveToFile(String url);
    
    public boolean loadFromFile(String url);
}
