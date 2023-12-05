/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.ArrayList;

/**
 *
 * @author RLappc.com
 */
public class SeatList extends ArrayList<Seat> {

    private String FlightId;
    private int availableSeat;
    private int max;
    private int reserveCount;
    public SeatList(String FlightId, int availableSeat) {
        this.FlightId = FlightId;
        this.availableSeat = availableSeat;
        this.max = Configuration.max;
        for (int i = 1; i <= max; i++){ 
            String seatId;
            if(i<10) seatId = "S0"+i;
            else seatId = "S"+i;
            this.add(new Seat( seatId, false));
        }
        reserveCount = 0;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getFlightId() {
        return FlightId;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public void setFlightId(String FlightId) {
        this.FlightId = FlightId;
    }

    public int chosenSeats() {
        int count = 0;
        for (Seat item : this) {
            if (item.isChosen()) {
                count++;
            }
        }
        return count;
    }

    public boolean isAvailable() {
        return reserveCount != availableSeat;
    }

    public ArrayList<Seat> getListAvailableSeat() {
        ArrayList<Seat> list = new ArrayList<>();
        for (Seat item : this) {
            if (!item.isChosen()) {
                list.add(item);
            }
        }
        return list;
    }

    public int availableLeft() {
        return availableSeat - reserveCount;
    }

    public int getReserveCount() {
        return reserveCount;
    }

    public void setReserveCount(int reserveCount) {
        this.reserveCount = reserveCount;
    }

    public int getModCount() {
        return modCount;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }
    
    @Override
    public String toString() {
        String str = this.FlightId + "|" + this.availableSeat + "|" + this.max + "|" + this.reserveCount + "|";
        for (Seat item : this) {
            str += item.getSeatNum() + "," + item.isChosen() + "|";
        }
        return str;
    }

}
