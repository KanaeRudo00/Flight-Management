package DTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author RLappc.com
 */
public class Flight {

    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private SeatList seats;
    private String captain;
    private String firstOfficer;
    private String flightAttendants;
    private String groundStaff;

    public Flight(String flightNumber, String departureCity, String destinationCity, String departureTime, String arrivalTime, String captain, String firstOfficer, String flightAttendants, String groundStaff, int availableSeat) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seats = new SeatList(flightNumber, availableSeat);
        this.captain = captain;
        this.firstOfficer = firstOfficer;
        this.flightAttendants = flightAttendants;
        this.groundStaff = groundStaff;
    }

    public Flight(String flightNumber, String departureCity, String destinationCity, String departureTime, String arrivalTime, int avaiSeat) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seats = new SeatList(flightNumber, avaiSeat);
    }

    public Flight(String flightNumber, String departureCity, String destinationCity, String departureTime, String arrivalTime, String captain, String firstOfficer, String flightAttendants, String groundStaff) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.captain = captain;
        this.firstOfficer = firstOfficer;
        this.flightAttendants = flightAttendants;
        this.groundStaff = groundStaff;
        this.seats = new SeatList(flightNumber,0);
    }
    
    public String getGroundStaff() {
        return groundStaff;
    }

    public void setGroundStaff(String groundStaff) {
        this.groundStaff = groundStaff;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public void setFirstOfficer(String firstOfficer) {
        this.firstOfficer = firstOfficer;
    }

    public void setFlightAttendant(String flightAttendants) {
        this.flightAttendants = flightAttendants;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCaptain() {
        return captain;
    }

    public String getFirstOfficer() {
        return firstOfficer;
    }

    public String getFlightAttendants() {
        return flightAttendants;
    }

    public SeatList getSeats() {
        return seats;
    }

    public void setSeats(SeatList seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return flightNumber + "| " + departureCity + "| " + destinationCity + "| " + departureTime + "| " + arrivalTime +  "| " + captain + "| " + firstOfficer + "| " + flightAttendants + "|" + groundStaff;
    }

}




























































