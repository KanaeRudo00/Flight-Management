/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserDTO;
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
public class UserDAO {

    private final String LOGIN = "SELECT fullName, role from tbl_User where userID = ? and password = ?";
    private final String REGISTER = "INSERT INTO tbl_User(userId, password, fullName, role) VALUES (?, ?, ?, ?)";
    private final String SEARCH = "SELECT * from tbl_User where userID like ?";
    private final String DELETE = "DELETE from tbl_User where userID = ?";
    private final String UPDATE = "UPDATE tbl_User SET fullName = ? , password = ? , role = ? where userID = ?";
    private final String FIND = "SELECT * FROM tbl_User where userID = ?";

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(LOGIN);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    int role = rs.getInt("role");
                    user = new UserDTO(userID, fullName, password, role);
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
        return user;
    }

    public UserDTO getUser(String username) throws SQLException {
        DTO.UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            if (conn == null) {
                conn = DBUtil.getConnection();
            }
            stm = conn.prepareStatement(FIND);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                user = new UserDTO(
                        rs.getString("userID"),
                        rs.getString("fullName"),
                        rs.getString("password"),
                        rs.getInt("role")
                );
            }
        } catch (Exception e) {

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
        return user;
    }

    public boolean register(String username, String fullName, String password, int role) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(REGISTER);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullName);
                stm.setInt(4, role);
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

    public List<UserDTO> search(String str) throws SQLException {
        Connection conn = null;
        List<UserDTO> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SEARCH);
                stm.setString(1, "%" + str + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String fullName = rs.getString("fullName");
                    String password = rs.getString("password");
                    int role = rs.getInt("role");
                    UserDTO us = new UserDTO(userID, fullName, password, role);
                    list.add(us);
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

    public boolean delete(String userID) throws SQLException {//To change body of generated methods, choose Tools | Templates.
        Connection conn = null;
        boolean check = false;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE);
                stm.setString(1, userID);
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
    
        public boolean update(UserDTO us) throws SQLException {//To change body of generated methods, choose Tools | Templates.
        Connection conn = null;
        boolean check = false;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE);
                stm.setString(1,us.getFullName());
                stm.setString(2,us.getPassword() );
                stm.setInt(3, us.getRoleID());
                stm.setString(4, us.getUserID());
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
}
