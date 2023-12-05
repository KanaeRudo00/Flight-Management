/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author RLappc.com
 */
public class Reserve {

    private String id;
    private String fullName;
    private String birthday;
    private String phoneNumber;
    private String numId;
    private String flightNumber;
    private String checkinTime;
    private String seat;
    private String status;

    public Reserve(String id, String fullName, String birthday, String phoneNumber, String numId, String flightNumber) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.numId = numId;
        this.flightNumber = flightNumber;
        status = "Not Checked";

    }

    public Reserve(String id, String fullName, String birthday, String phoneNumber, String numId, String flightNumber, String checkinTime, String seat, String status) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.numId = numId;
        this.flightNumber = flightNumber;
        this.checkinTime = checkinTime;
        this.seat = seat;
        this.status = status;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + "| " + fullName + "| " + birthday + "| " + phoneNumber + "| " + numId + "| " + flightNumber + "| " + checkinTime + "| " + seat + "| " + status;
    }
}
