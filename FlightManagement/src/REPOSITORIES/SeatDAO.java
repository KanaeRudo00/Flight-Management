/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package REPOSITORIES;

import DTO.Flight;
import DTO.Seat;
import DTO.SeatList;
import INTERFACE.ISeat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 *
 * @author RLappc.com
 */
public class SeatDAO implements ISeat{
    
    public FlightManagement fm;

    public SeatDAO(FlightManagement fm) {
        this.fm = fm;
    }
    
    
    @Override
    public boolean saveToFile(String url) {
        File f = new File(url);
        try {
            try ( OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f))) {
                for (Flight obj : fm) {
                    writer.write(obj.getSeats().toString());
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
        File f = new File(url);
        StringTokenizer tk, tk2;
        try ( BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line = reader.readLine();
            while (line != null) {
                tk = new StringTokenizer(line, "|");
                SeatList item = fm.getFlight(tk.nextToken()).getSeats() ;
                item.setAvailableSeat(Integer.parseInt(tk.nextToken()));
                item.setMax(Integer.parseInt(tk.nextToken()));
                item.setReserveCount(Integer.parseInt(tk.nextToken()));
                item.clear();
                while(tk.hasMoreTokens()){
                    tk2 = new StringTokenizer(tk.nextToken(),",");
                    item.add(new Seat(tk2.nextToken().trim(), Boolean.parseBoolean(tk2.nextToken().trim())));
                }
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
}
