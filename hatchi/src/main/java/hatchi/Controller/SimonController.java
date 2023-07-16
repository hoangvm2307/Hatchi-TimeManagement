/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Controller;

import hatchi.model.Simon.SimonDAO;
import hatchi.model.Simon.SimonDTO;
import hatchi.model.User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoangvmdeptrai
 */
public class SimonController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            HttpSession session = request.getSession();
            String MenuAction = "Simon";
            session.setAttribute("MenuAction", MenuAction);
            
            UserDTO userDTO = (UserDTO) session.getAttribute("usersession");
            String userID = String.valueOf(userDTO.getUserID());

            SimonDAO simonDAO = new SimonDAO();

            //Select Simon
            String indexImage = request.getParameter("indexImage");
            SimonDTO selected = simonDAO.loadSimon(indexImage);

            request.setAttribute("selected", selected);

            //Paging Finished
            String indexPara = request.getParameter("index");

            int index = 1;
            int pageLength = 5;

            if (indexPara != null) {
                index = Integer.parseInt(indexPara);
            }
            String searchValue = request.getParameter("searchValue");
            if (searchValue == null) {
                searchValue = "";
            }
            int totalPages = simonDAO.listOwnItem(userID).size();
            ArrayList<SimonDTO> listItem = (ArrayList<SimonDTO>) simonDAO.pagingOwnedItem(index, pageLength, userID);
            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }

            ArrayList<SimonDTO> listOwnItem = (ArrayList<SimonDTO>) simonDAO.listOwnItem(userID);
            session.setAttribute("listOwnedItem", listOwnItem);
            session.setAttribute("loaded", false);

            request.setAttribute("endPage", endPage);
            request.setAttribute("listOwnedItem", listItem);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);
            RequestDispatcher rd = request.getRequestDispatcher("ownPets.jsp");
            rd.forward(request, response);
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
