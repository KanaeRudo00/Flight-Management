/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.StringTokenizer;

/**
 *
 * @author RLappc.com
 */
public class Date {

    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private String time;

    public Date(String date) {
        StringTokenizer tk = new StringTokenizer(date, "/");
        day = Integer.parseInt(tk.nextToken());
        month = Integer.parseInt(tk.nextToken());
        StringTokenizer tk2 = new StringTokenizer(tk.nextToken(), " ");
        year = Integer.parseInt(tk2.nextToken());
        if (year < 100) {
            year += 2000;
        }
        if (year > 2050) {
            year -= 100;
        }
        if (!tk2.hasMoreTokens()) {
            hour = 0;
            minute = 0;
            time = "AM";
        } else {
            String str[] = tk2.nextToken().split(":");
            hour = Integer.parseInt(str[0]);
            minute = Integer.parseInt(str[1]);
            if(tk2.hasMoreTokens())
                time = tk2.nextToken();
            else
            time = "AM" ;
            if ("PM".equals(time) && hour != 12) {
                hour += 12;
            }
        }
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year + " " + hour + ":" + minute + " " + time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
