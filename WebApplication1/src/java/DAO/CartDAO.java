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
public class CartDAO {

    private String userID = "a";


    public CartDAO(String userID) throws SQLException {
        this.userID = userID;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement("IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'" + userID + "') AND type in (N'U'))\n"
            + "BEGIN\n"
            + "    CREATE TABLE " + userID + "(\n"
            + "	mobileID varchar(10) primary key,\n"
            + "	amount int,\n"
            + "	constraint "+userID+"_constraint foreign key (mobileID) references tbl_Mobile(mobileID),\n"
            + ")\n"
            + "END");
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
    }

    public CartDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addToCart(String mobileID, int amount) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("INSERT INTO " + userID + " VALUES (?,?)");
                stm.setString(1, mobileID);
                stm.setInt(2, amount);
                stm.executeUpdate();
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

        }
    }

    public void updateCart(String mobileID, int amount) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("UPDATE " + userID + " SET amount = ? WHERE mobileID = ?");
                stm.setInt(1, amount);
                stm.setString(2, mobileID);
                stm.executeUpdate();
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
        }
    }

    public void removeCart(String mobileID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("DELETE FROM " + userID + " WHERE mobileID = ?");
                stm.setString(1, mobileID);
                stm.executeUpdate();
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
        }
    }

    public List<MobileDTO> getCart() throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<MobileDTO> list = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("SELECT * FROM " + userID);
                rs = stm.executeQuery();
                MobileDAO dao = new MobileDAO();
                List<MobileDTO> moList = dao.search("");
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    int quantity = rs.getInt("amount");
                    String description = "";
                    double price = 0;
                    String mobileName = "";
                    int yearOfProduction = 9999;
                    for (MobileDTO mobileDTO : moList) {
                        if (mobileId.equals(mobileDTO.getMobileId())) {
                            description = mobileDTO.getDescription();
                            price = mobileDTO.getPrice();
                            mobileName = mobileDTO.getMobileName();
                            yearOfProduction = mobileDTO.getYearOfProduction();
                        }
                    }
                    MobileDTO mobile = new MobileDTO(mobileId, description, price, mobileName, yearOfProduction, quantity, 1);
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
}
