/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RUNTIME;

import DTO.Configuration;
import DTO.DateTimeDTO;
import DTO.Flight;
import DTO.Regex_Pattern;
import DTO.Reserve;
import GUI.MainMenu;
import GUI.TableOutputter;
import REPOSITORIES.FlightManagement;
import REPOSITORIES.ReserveManagement;
import REPOSITORIES.SeatDAO;
import TOOL.Inputter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author RLappc.com
 */
public class Program {

    static final FlightManagement fm = new FlightManagement();
    static final ReserveManagement rm = new ReserveManagement();
    static final SeatDAO sp = new SeatDAO(fm);

    public static void main(String[] args) {
        MainMenu mn = new MainMenu("Flight Management system");

        mn.addOptions("Show all Flight");
        mn.addOptions("Add Flight");
        mn.addOptions("Search Available Flight");
        mn.addOptions("Make Reservation");
        mn.addOptions("Checkin");
        mn.addOptions("Update Flight");
        mn.addOptions("Save To File");
        mn.addOptions("Load From File");
        mn.addOptions("Print All Reserve");
        mn.addOptions("Get Flight Details");
        mn.addOptions("Config Future Flight Capacity");
        String Id;
        while (true) {
            mn.printMenu();
            switch (mn.getOption()) {
                case 1:
                    TableOutputter.printFlightTable(fm);
                    break;
                case 2:
                    Id = Inputter.getString("Flight Number:", "Invalid input!", "F\\d{4}");
                    while (fm.getFlight(Id) != null) {
                        System.out.println("Flight Number has been taken!");
                        Id = Inputter.getString("Flight Number:", "Invalid input!", "F\\d{4}");
                    }
                    Flight flight1 = new Flight(
                            Id,
                            Inputter.getString("Departure City:", "Invalid input!"),
                            Inputter.getString("Destination City:", "Invalid input!"),
                            Inputter.getString("Departure Date:", "Invalid input!", Regex_Pattern.datetimeRegex),
                            Inputter.getString("Arrival Date:", "Invalid input!", Regex_Pattern.datetimeRegex),
                            Inputter.getInteger("Available Seat:", "Invalid input!", 1, Configuration.max)
                    );
                    while (!DateTimeDTO.isBefore(flight1.getDepartureTime(), flight1.getArrivalTime())) {
                        System.out.println("Departure Datetime is After Arrival Datetime, please input again!");
                        flight1.setDepartureTime(Inputter.getString("Departure Date:", "Invalid input!", Regex_Pattern.datetimeRegex));
                        flight1.setArrivalTime(Inputter.getString("Arrival Date:", "Invalid input!", Regex_Pattern.datetimeRegex));
                    }
                    while (flight1.getDepartureCity() == null ? flight1.getDestinationCity() == null : flight1.getDepartureCity().equals(flight1.getDestinationCity())) {
                        System.out.println("Departure city cannot be the same as Destination City, please input again!");
                        flight1.setDepartureCity(Inputter.getString("Departure City:", "Invalid input!"));
                        flight1.setDestinationCity(Inputter.getString("Destination City:", "Invalid input!"));
                    }
                    fm.add(flight1);

                    break;
                case 3:
                    TableOutputter.printFlightTable(fm.searchFlights(
                            Inputter.getString("Departure City:", 0, 30),
                            Inputter.getString("Destination City:", 0, 30),
                            Inputter.getString("Departure Date:", 0, 30, Regex_Pattern.datetimeRegex)
                    ));
                    break;
                case 4:
                    Id = Inputter.getString("Flight Number:", "Invalid input!", "F\\d{4}");
                    if (fm.getFlight(Id) == null) {
                        System.out.println("Invalid Flight number!");
                    } else if (!fm.getFlight(Id).getSeats().isAvailable()) {
                        System.out.println("Flight not available!");
                    } else {
                        int count = 0;
                        int size = rm.size();
                        do {
                            size /= 10;
                            count++;
                        } while (size != 0);
                        String rid = "R";
                        for (int i = 1; i <= 7 - count; i++) {
                            rid += "0";
                        }
                        rm.createReserve(new Reserve(
                                rid + rm.size(),
                                Inputter.getName("Full Name:", "Invalid Input!", 0, 20),
                                Inputter.getString("Date of Birth:", "Invalid input!", Regex_Pattern.dateRegex),
                                Inputter.getString("Phone:", "Invalid input!", Regex_Pattern.phoneRegex),
                                Inputter.getString("Gorv Id:", "Invalid input!", "\\d{12}"),
                                Id
                        ));
                        fm.getFlight(Id).getSeats().setReserveCount(fm.getFlight(Id).getSeats().getReserveCount() + 1);
                    }
                    break;
                case 5:
                    Reserve res = rm.getReserve(Inputter.getString("Reserve Id:", "Invalid input!", "R\\d{7}"));
                    if (res != null) {
                        TableOutputter.printFlightSeat(fm.getFlight(res.getFlightNumber()).getSeats());
                        int choice = Inputter.getInteger("Seat choice: ", "Invalid input!", 0, fm.getFlight(res.getFlightNumber()).getSeats().getMax());
                        if (choice / 10 == 0) {
                            res.setSeat("S0" + choice);
                        } else {
                            res.setSeat("S" + choice);
                        }
                        fm.getFlight(res.getFlightNumber()).getSeats().get(choice - 1).setIsChosen(true);
                        res.setCheckinTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
                        TableOutputter.printFlightBoarding(res, fm.getFlight(res.getFlightNumber()));
                        res.setStatus("Checked");
                    } else {
                        System.out.println("Invalid id!");
                    }
                    break;
                case 6:
                    Id = Inputter.getString("Flight Number:", "Invalid input!", "F\\d{4}");
                    Flight item = fm.getFlight(Id);

                    if (item == null) {
                        System.out.println("Invalid Flight number!");
                    } else {
                        TableOutputter.printDetailFlight(item);
                        String input = Inputter.getString("Departure City:", 0, 15);
                        item.setDepartureCity(input.length() == 0 ? item.getDepartureCity() : input);
                        input = Inputter.getString("Destination City:", 0, 15);
                        item.setDestinationCity(input.length() == 0 ? item.getDestinationCity() : input);
                        input = Inputter.getString("Departure Date:", 0, 11, Regex_Pattern.datetimeRegex);
                        item.setDepartureTime(input.length() == 0 ? item.getDepartureTime() : input);
                        input = Inputter.getString("Arrival Date:", 0, 11, Regex_Pattern.datetimeRegex);
                        item.setArrivalTime(input.length() == 0 ? item.getArrivalTime() : input);
                        item.getSeats().setAvailableSeat(Inputter.getInteger("Available Seat:", "Invalid input!", item.getSeats().getReserveCount(), Configuration.max));
                        input = Inputter.getString("Captain: ", 0, 15);
                        item.setCaptain(input.length() == 0 ? item.getCaptain() : input);
                        input = Inputter.getString("FirstOfficer: ", 0, 15);
                        item.setFirstOfficer(input.length() == 0 ? item.getFirstOfficer() : input);
                        input = Inputter.getString("FlightAttendant: ", 0, 50);
                        item.setFlightAttendant(input.length() == 0 ? item.getFlightAttendants() : input);
                        input = Inputter.getString("GroundStaff: ", 0, 50);
                        item.setGroundStaff(input.length() == 0 ? item.getGroundStaff() : input);
                        while (!DateTimeDTO.isBefore(item.getDepartureTime(), item.getArrivalTime())) {
                            System.out.println("Departure Datetime is After Arrival Datetime, please input again!");
                            item.setDepartureTime(Inputter.getString("Departure Date:", "Invalid input!", Regex_Pattern.datetimeRegex));
                            item.setArrivalTime(Inputter.getString("Arrival Date:", "Invalid input!", Regex_Pattern.datetimeRegex));
                        }
                        while (item.getDepartureCity() == null ? item.getDestinationCity() == null : item.getDepartureCity().equals(item.getDestinationCity())) {
                            System.out.println("Departure city cannot be the same as Destination City, please input again!");
                            item.setDepartureCity(Inputter.getString("Departure City:", "Invalid input!"));
                            item.setDestinationCity(Inputter.getString("Destination City:", "Invalid input!"));
                        }
                        TableOutputter.printDetailFlight(item);
                    }
                    break;
                case 7:
                    sp.saveToFile("seat.dat");
                    fm.saveToFile("flight.dat");
                    rm.saveToFile("reserve.dat");
                    break;
                case 8:
                    fm.loadFromFile("flight.dat");
                    rm.loadFromFile("reserve.dat");
                    sp.loadFromFile("seat.dat");
                    TableOutputter.printFlightTable(fm);
                    TableOutputter.printReserve(rm);
                    break;
                case 9:
                    TableOutputter.printReserve(rm);
                    break;
                case 10:
                    Flight flight = fm.getFlight(Inputter.getString("Flight Number:", "Invalid input!", "F\\d{4}"));
                    if (flight == null) {
                        System.out.println("Flight not found!");
                    } else {
                        TableOutputter.printDetailFlight(flight);
                    }
                    break;
                case 11:
                    System.out.println("Current capacity: " + Configuration.max);
                    Configuration.max = Inputter.getInteger("Please input Future capacity:", "Invalid input!", 1, 100);
                    break;
                case 0:
                    return;
            }
        }
    }
}
