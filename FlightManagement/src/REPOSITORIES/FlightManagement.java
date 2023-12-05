/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package REPOSITORIES;

import DTO.Flight;
import INTERFACE.IFlight;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author RLappc.com
 */
public class FlightManagement extends ArrayList<Flight> implements IFlight {

    @Override
    public boolean saveToFile(String url) {
        File f = new File(url);
        try {
            try ( OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f))) {
                for (Flight pub : this) {
                    writer.write(pub.toString());
                    writer.write("\n");
                }
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("File Error!");
            return false;
        }
        return true;
    }

    @Override
    public boolean loadFromFile(String url) {
        this.clear();
        File f = new File(url);
        StringTokenizer tk;
        try ( BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line = reader.readLine();
            while (line != null) {
                tk = new StringTokenizer(line, "|");
                this.add(new Flight(
                        tk.nextToken().trim(),
                        tk.nextToken().trim(),
                        tk.nextToken().trim(),
                        tk.nextToken().trim(),
                        tk.nextToken().trim(),
                        tk.nextToken().trim(),
                        tk.nextToken().trim(),
                        tk.nextToken().trim(),
                        tk.nextToken().trim()
                ));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("End of File");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Flight> searchFlights(String departureCity, String destinationCity, String departureDate) {
        ArrayList<Flight> list = new ArrayList<>();
        for (Flight item : this) {
            if ((item.getDepartureCity().contains(departureCity) || item.getDestinationCity().contains(destinationCity) || departureDate.equals(item.getDepartureTime())) && item.getSeats().isAvailable()) {
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public boolean createFlight(Flight input) {
        return this.add(input);
    }

    @Override
    public boolean updateFlight(Flight input) {
        Flight item = this.getFlight(input.getFlightNumber());
        if (item == null) {
            return false;
        }
        item.setArrivalTime(input.getArrivalTime());
        item.setSeats(input.getSeats());
        item.setCaptain(input.getCaptain());
        item.setDepartureCity(input.getDepartureCity());
        item.setDestinationCity(input.getDestinationCity());
        item.setFirstOfficer(input.getFirstOfficer());
        item.setFlightAttendant(input.getFlightAttendants());
        item.setDepartureTime(input.getDepartureTime());
        item.setGroundStaff(input.getGroundStaff());
        return true;
    }

    public Flight getFlight(String flightNumber) {
        for (Flight item : this) {
            if (item.getFlightNumber().equals(flightNumber)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean removeFlight(String id) {
        Flight item = getFlight(id);
        if (item != null) {
            return this.remove(item);
        } else {
            return false;
        }
    }
}
