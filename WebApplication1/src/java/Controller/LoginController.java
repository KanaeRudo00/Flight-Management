/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DTO.UserDTO;
import java.io.IOException;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String admin = "AD";
    private final String staff = "ST";
    private final String user = "US";
    private final String AD_page = "admin.jsp";
    private final String ST_page = "staff.jsp";
    private final String US_page = "user.jsp";

    private final String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserDAO dao = new UserDAO();
            String username = request.getParameter("userID");
            String password = request.getParameter("password");
            String keepLoggedIn = request.getParameter("keepLoggedIn");

            UserDTO us = dao.checkLogin(username, password);
            if (us == null) {
                request.setAttribute("ERROR", "Invalid username or password");
//                ERROR = "Invalid username or password"
            } else {
                switch (us.getRoleID()) {
                    case 1:
                        url = AD_page;
                        break;
                    case 2:
                        url = ST_page;
                        break;
                    case 0:
                        url = US_page;
                        break;
                    default:
                        request.setAttribute("ERROR", "Your role is not supported yet!");
                        break;
                }
                HttpSession session = request.getSession();
                if (keepLoggedIn != null) {
                    session.setMaxInactiveInterval(7 * 24 * 60 * 60); // 1 week in seconds
                    session.setAttribute("username", us.getUserID());
                    session.setAttribute("password", us.getPassword());
                } else {
                    session.setMaxInactiveInterval(30 * 60); // Default session timeout
                }
                session.setAttribute("recent_user", us);
            }

        } catch (Exception e) {
            log("Error at: Login Controller" + e.toString());
        } finally {
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
