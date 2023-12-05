/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MobileDAO;
import DTO.MobileDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
@WebServlet(name = "SortController", urlPatterns = {"/SortController"})
public class SortController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String sortParam = request.getParameter("sort");

        // Retrieve the list of mobiles and apply sorting if necessary
        HttpSession session = request.getSession();
        String url = (String) session.getAttribute("jspName");
        String search_val = session.getAttribute("search") == null ? "" : (String) session.getAttribute("search");
        MobileDAO dao = new MobileDAO();
        try {
            List<MobileDTO> listMobile = dao.search(search_val);

            if (sortParam != null) {
                switch (sortParam) {
                    case "mobileID_asc":
                        Collections.sort(listMobile, Comparator.comparing(MobileDTO::getMobileId));
                        break;
                    case "mobileName_asc":
                        Collections.sort(listMobile, Comparator.comparing(MobileDTO::getMobileName));
                        break;
                    // Add more cases for other sorting options if needed
                    default:
                        break;
                }
            }

            // Set the sorted mobile list as an attribute in the request
            request.setAttribute("list_search", listMobile);

            // Forward the request to the staff.jsp page
        } catch (Exception e) {
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
