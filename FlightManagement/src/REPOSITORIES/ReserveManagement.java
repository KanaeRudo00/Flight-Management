/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package REPOSITORIES;

import DTO.Reserve;
import INTERFACE.IReserve;
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
public class ReserveManagement extends ArrayList<Reserve> implements IReserve {

    @Override
    public Reserve checkin(String reserveId){
        for (Reserve item : this) {
            if (item.getId().equals(reserveId) && item.getStatus().equals("Not Checked")) {
                item.setStatus("Checked");
                return item;
            }
        }
        return null;
    }
    public Reserve getReserve(String reserveId) {
        for (Reserve item : this) {
            if (item.getId().equals(reserveId)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean updateReserve(Reserve input) {
        Reserve item = getReserve(input.getId());
        if (item != null) {
            item.setBirthday(input.getBirthday());
            item.setCheckinTime(input.getCheckinTime());
            item.setFlightNumber(input.getFlightNumber());
            item.setPhoneNumber(input.getPhoneNumber());
            item.setNumId(input.getNumId());
        }
        return false;
    }

    @Override
    public boolean createReserve(Reserve input) {
        return this.add(input);
    }

    @Override
    public boolean abortReserve(String reserveId) {
        Reserve item = getReserve(reserveId);
        if (item != null) {
            item.setStatus("Aborted");
            return true;
        }
        return false;
    }

    @Override
    public boolean saveToFile(String url) {
        File f = new File(url);
        try {
            try ( OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f))) {
                for (Reserve pub : this) {
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
                this.add(new Reserve(
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
}
