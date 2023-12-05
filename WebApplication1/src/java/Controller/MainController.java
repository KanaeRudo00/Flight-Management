/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RLappc.com
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ERROR = "fail.jsp";
    private final String LOGIN = "Login";
    private final String LOGIN_CONTROLLER = "LoginController";
    private final String REGISTER = "Register";
    private final String REGISTER_CONTROLLER = "RegisterController";
    private final String SEARCH = "Search";
    private final String SEARCH_CONTROLLER = "SearchController";
    private final String UPDATE = "Update";
    private final String UPDATE_CONTROLLER = "UpdateController";
    private final String REMOVE = "Remove";
    private final String REMOVE_CONTROLLER = "RemoveController";
    private final String LOGOUT = "Logout";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    private final String SORT = "Sort";
    private final String SORT_CONTROLLER = "SortController";
    private final String ADD = "Add";
    private final String ADD_CONTROLLER = "AddController";
    private final String BUY = "Buy";
    private final String BUY_CONTROLLER = "BuyController";
    private final String CART = "Cart";
    private final String CART_CONTROLLER = "CartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            log(action);
            switch (action) {
                case LOGIN:
                    url = LOGIN_CONTROLLER;
                    break;
                case REGISTER:
                    url = REGISTER_CONTROLLER;
                    break;
                case SEARCH:
                    url = SEARCH_CONTROLLER;
                    break;
                case UPDATE:
                    url = UPDATE_CONTROLLER;
                    break;
                case REMOVE:
                    url = REMOVE_CONTROLLER;
                    break;
                case LOGOUT:
                    url = LOGOUT_CONTROLLER;
                    break;
                case SORT:
                    url = SORT_CONTROLLER;
                    break;
                case ADD:
                    url = ADD_CONTROLLER;
                    break;
                case BUY:
                    url = BUY_CONTROLLER;
                    break;
                case CART:
                    url = CART_CONTROLLER;
                    break;
                default:
                    url =ERROR;
                    break;
            }
        } catch (Exception e) {
            log("Error at: MainController" + e.toString());
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
