/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@WebServlet(name = "RemoveController", urlPatterns = {"/RemoveController"})
public class RemoveController extends HttpServlet {

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

        HttpSession session = request.getSession();
        String url = (String) session.getAttribute("jspName");
        try {
            switch (url) {
                case AD_page:
                    UserDAO dao = new UserDAO();
                    String userID = request.getParameter("userID");
                    UserDTO loginUser = (UserDTO) session.getAttribute("recent_user");
                    if (loginUser.getUserID().equals(userID)) {
                        request.setAttribute("ERROR", "Can't delete your own account");
                    } else {
                        boolean checkDelete = dao.delete(userID);
                        if (!checkDelete) {
                            request.setAttribute("ERROR", "Delete fail: ");
                        }
                    }
                    break;
                case ST_page:
                    MobileDAO daoM = new MobileDAO();
                    String mobileID = request.getParameter("mobileID");
                    boolean checkDelete = daoM.delete(mobileID);
                    if (!checkDelete) {
                        request.setAttribute("ERROR", "Delete fail: ");
                    }
                    break;
                case US_page:
                    CartDAO daoU = new CartDAO(request.getParameter("userID"));
                    String mobileID_ = request.getParameter("mobileID");
                    daoU.removeCart(mobileID_);
                    break;
            }

        } catch (Exception ex) {
            log("Error at DeleteController: " + ex.toString());
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
