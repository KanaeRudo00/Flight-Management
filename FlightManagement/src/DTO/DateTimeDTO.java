/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author RLappc.com
 */
public class DateTimeDTO {

    public static boolean isBefore(String date1, String date2) {
        Date datetime1 = new Date(date1);
        Date datetime2 = new Date(date2);
        return (datetime1.getYear() < datetime2.getYear())
                || (datetime1.getYear() == datetime2.getYear() && datetime1.getMonth() < datetime2.getMonth())
                || (datetime1.getYear() == datetime2.getYear() && datetime1.getMonth() == datetime2.getMonth() && datetime1.getDay() < datetime2.getDay())
                || (datetime1.getYear() == datetime2.getYear() && datetime1.getMonth() == datetime2.getMonth() && datetime1.getDay() == datetime2.getDay() && datetime1.getHour() < datetime2.getHour())
                || (datetime1.getYear() == datetime2.getYear() && datetime1.getMonth() == datetime2.getMonth() && datetime1.getDay() == datetime2.getDay() && datetime1.getHour() == datetime2.getHour() && datetime1.getMinute() < datetime2.getMinute());
    }
}
