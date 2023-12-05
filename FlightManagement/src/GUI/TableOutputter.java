/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.Flight;
import DTO.Reserve;
import DTO.Seat;
import DTO.SeatList;
import java.util.ArrayList;

/**
 *
 * @author RLappc.com
 */
public class TableOutputter {

    public static void printFlightTable(ArrayList<Flight> list) {
        System.out.println("==========================================================================================================================================");
        System.out.println("|   ID   |    Departure    |   Destination   |     Depart. Time     |      Arriv. Time     | Seats |      Captain     |    1stOfficer    |");
        System.out.println("==========================================================================================================================================");
        for (Flight flight : list) {
            System.out.println(String.format("| %6s | %15s | %15s | %20s | %20s | %5s | %16s | %16s |",
                    flight.getFlightNumber(),
                    flight.getDepartureCity(),
                    flight.getDestinationCity(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getSeats().availableLeft(),
                    flight.getCaptain(),
                    flight.getFirstOfficer()
            ));
        }
        System.out.println("==========================================================================================================================================");
    }

    public static void printDetailFlight(Flight flight) {
        System.out.println("=================================================");
        System.out.println("FLIGHT        :" + flight.getFlightNumber());
        System.out.println("DEPARTURE     : " + flight.getDepartureCity() + " - " + flight.getDepartureTime());
        System.out.println("DESTINATION   : " + flight.getDestinationCity() + " - " + flight.getArrivalTime());
        System.out.println("SEATS         : " + flight.getSeats().getReserveCount() + "/" + flight.getSeats().getAvailableSeat());
        System.out.println("CHECKIN       : " + flight.getSeats().chosenSeats() + "/" + flight.getSeats().getReserveCount());
        System.out.println("CAPACITY      : " + flight.getSeats().getMax());
        System.out.println("CAPTAIN       : " + flight.getCaptain());
        System.out.println("FIRST OFFICER : " + flight.getFirstOfficer());
        System.out.println("FLIGHT ATTEN. : " + flight.getFlightAttendants());
        System.out.println("GROUND STAFF  : " + flight.getGroundStaff());
    }

    public static void printFlightBoarding(Reserve reserve, Flight flight) {
        System.out.println("---------------------------");
        System.out.println("FULLNAME    :" + reserve.getFullName());
        System.out.println("RESERVEID   :" + reserve.getId());
        System.out.println("FLIGHT      :" + flight.getFlightNumber());
        System.out.println("DEPARTURE   :" + flight.getDepartureCity() + " - " + flight.getDepartureTime());
        System.out.println("DESTINATION :" + flight.getDestinationCity() + " - " + flight.getArrivalTime());
        System.out.println("CHECKIN     :" + reserve.getCheckinTime());
        System.out.println("SEAT        :" + reserve.getSeat());
    }

    public static void printReserve(ArrayList<Reserve> list) {
        System.out.println(list.size());

        System.out.println("========================================================================================================================================");
        System.out.println("|    ID    |    GorvID    |       FullName       |    Birth    |     Phone    | FlightId | SeatNum |    CheckinTime      |    Status   |");
        System.out.println("========================================================================================================================================");
        for (Reserve reserve : list) {
            System.out.println(String.format("| %8s | %12s | %20s | %11s | %12s | %8s | %7s | %19s | %11s |",
                    reserve.getId(),
                    reserve.getNumId(),
                    reserve.getFullName(),
                    reserve.getBirthday(),
                    reserve.getPhoneNumber(),
                    reserve.getFlightNumber(),
                    reserve.getSeat(),
                    reserve.getCheckinTime() != null ? reserve.getCheckinTime() : "null",
                    reserve.getStatus()
            ));
        }
        System.out.println("========================================================================================================================================");

    }

    public static void printFlightSeat(SeatList seats) {
        System.out.println("Flight - " + seats.getFlightId() + "  " + seats.chosenSeats() + "/" + seats.getAvailableSeat());
        System.out.println("==================================================");
        int count = 1;
        String str = "|";
        for (Seat seat : seats) {
            if (!seat.isChosen()) {

                str += "    " + seat.getSeatNum() + "    |";
            } else {
                str += "    xxx    |";
            }
            if (count % 4 == 0 || count == seats.getMax()) {
                System.out.println(str);
                str = "|";
            }
            if (count == seats.getMax()) {
                return;
            }
            count++;
        }
    }
    
}
