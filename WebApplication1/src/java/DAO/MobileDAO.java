/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.MobileDTO;
import Util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RLappc.com
 */
public class MobileDAO {

    private final String REGISTER = "INSERT INTO tbl_Mobile VALUES (?,?,?,?,?,?,?)";
    private final String SEARCH = "Select * from tbl_Mobile where mobileId like ? or mobileName like ? order by mobileId asc, mobileName asc";
    private final String DELETE = "Delete from tbl_Mobile where mobileId = ?";
    private final String UPDATE = "UPDATE tbl_Mobile SET description = ? , price = ?, mobileName = ? , yearOfProduction = ?, quantity = ?, sale =? where mobileId = ?";

    public boolean register(String mobileId, String description, double price, String mobileName, int yearOfProduction, int quantity, int sale) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        MobileDTO dto = new MobileDTO(mobileId, description, price, mobileName, yearOfProduction, quantity, sale);

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(REGISTER);
                stm.setString(1, dto.getMobileId());
                stm.setString(2, dto.getDescription());
                stm.setDouble(3, dto.getPrice());
                stm.setString(4, dto.getMobileName());
                stm.setInt(5, dto.getYearOfProduction());
                stm.setInt(6, dto.getQuantity());
                stm.setInt(7, dto.getSale());
                stm.executeUpdate();
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<MobileDTO> search(String str) throws SQLException {
        Connection conn = null;
        List<MobileDTO> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SEARCH);
                stm.setString(1, "%" + str + "%");
                stm.setString(2, "%" + str + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    int sale = rs.getInt("sale");
                    MobileDTO mobile = new MobileDTO(mobileId, description, price, mobileName, yearOfProduction, quantity, sale);
                    list.add(mobile);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;

    }

    public boolean delete(String mobileId) throws SQLException {//To change body of generated methods, choose Tools | Templates.
        Connection conn = null;
        boolean check = false;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE);
                stm.setString(1, mobileId);
                stm.executeUpdate();
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return check;
    }

    public boolean update(MobileDTO dto) throws SQLException {//To change body of generated methods, choose Tools | Templates.
        Connection conn = null;
        boolean check = false;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE);
                stm.setString(7, dto.getMobileId());
                stm.setString(1, dto.getDescription());
                stm.setDouble(2, dto.getPrice());
                stm.setString(3, dto.getMobileName());
                stm.setInt(4, dto.getYearOfProduction());
                stm.setInt(5, dto.getQuantity());
                stm.setInt(6, dto.getSale());
                stm.executeUpdate();
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return check;
    }

    public List<MobileDTO> search(double min, double max) throws SQLException {
        List<MobileDTO> list = new ArrayList<>();
        list = search("");
        List<MobileDTO> list_ret = new ArrayList<>();
        for (MobileDTO mobileDTO : list) {
            if (mobileDTO.getPrice() >= min && mobileDTO.getPrice() <= max && mobileDTO.getSale() != 0) {
                list_ret.add(mobileDTO);
            }
        }
        return list_ret;
    }
}
