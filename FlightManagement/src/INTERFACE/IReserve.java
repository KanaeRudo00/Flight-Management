/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INTERFACE;

import DTO.Reserve;

/**
 *
 * @author RLappc.com
 */
public interface IReserve {

    public Reserve checkin(String reserveId);

    public boolean updateReserve(Reserve input);

    public boolean createReserve(Reserve input);

    public boolean abortReserve(String reserveId);

    public boolean saveToFile(String url);
    
    public boolean loadFromFile(String url);
}
