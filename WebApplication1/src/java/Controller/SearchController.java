/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CartDAO;
import DAO.MobileDAO;
import DAO.UserDAO;
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
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

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
    private final String US_page = "user.jsp";
    private final String CA_page = "CartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String url = (String) session.getAttribute("url");
        String search_val = request.getParameter("search");
        try {
            switch (url) {
                case AD_page:
                    session.setAttribute("search", search_val);
                    UserDAO dao = new UserDAO();
                    request.setAttribute("list_search", dao.search(search_val));
                    break;
                case ST_page:
                    session.setAttribute("search", search_val);
                    MobileDAO dao_st = new MobileDAO();
                    request.setAttribute("list_search", dao_st.search(search_val));
                    break;
                case US_page:
                    MobileDAO dao_us = new MobileDAO();
                    Double min = Double.parseDouble(request.getParameter("min"));
                    Double max = Double.parseDouble(request.getParameter("max"));
                    if(min>max){
                        double tmp = min;
                        min = max;
                        max = tmp;
                    }
                    session.setAttribute("min", min);
                    session.setAttribute("max", max);
                    request.setAttribute("list_search", dao_us.search(min, max));
                    break;
                case CA_page:
                    url = CA_page;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Error at: " + e);
        } finally {
            request.setAttribute("change", false);
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
