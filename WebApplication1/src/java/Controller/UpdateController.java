/*
 * To change license header, choose License Headers in Project Properties.
 * To change template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CartDAO;
import DAO.MobileDAO;
import DAO.UserDAO;
import DTO.MobileDTO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RLappc.com
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String AD_page = "admin.jsp";
    private final String ST_page = "staff.jsp";
    private final String US_page = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String url = (String) session.getAttribute("jspName");
        try {
            switch (url) {
                case AD_page:
                    String userID = (String) request.getParameter("userID");
                    String newPassword = request.getParameter(userID + "_password");
                    String newFullName = request.getParameter(userID + "_fullName");
                    Integer newRoleID = Integer.parseInt(request.getParameter(userID + "_role"));
                    UserDAO dao = new UserDAO();
                    UserDTO us = new UserDTO(userID, newFullName, newPassword, newRoleID);
                    dao.update(us);
                    break;
                case ST_page:
                    String mobileID = (String) request.getParameter("mobileID");
                    String description = request.getParameter(mobileID + "_desc");
                    double price = Double.parseDouble(request.getParameter(mobileID + "_price"));
                    String mobileName = request.getParameter(mobileID + "_name");
                    int yearOfProduction = Integer.parseInt(request.getParameter(mobileID + "_yop"));
                    int quantity = Integer.parseInt(request.getParameter(mobileID + "_quan"));
                    int sale = request.getParameter(mobileID + "_sale").equals("False") ? 0 : 1;
                    MobileDAO daoM = new MobileDAO();
                    MobileDTO dto = new MobileDTO(mobileID, description, price, mobileName, yearOfProduction, quantity, sale);
                    daoM.update(dto);
                    break;
                case US_page:
                    String mobileID_ = (String) request.getParameter("mobileID");
                    int quantity_ = Integer.parseInt(request.getParameter(mobileID_+"_quan"));
                    CartDAO daoU = new CartDAO(request.getParameter("userID"));
                    daoU.updateCart(mobileID_, quantity_);
                    break;
                
            }

        } catch (Exception e) {
            log("Error at: MainController" + e.toString());
        } finally {
            request.setAttribute("change", true);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
