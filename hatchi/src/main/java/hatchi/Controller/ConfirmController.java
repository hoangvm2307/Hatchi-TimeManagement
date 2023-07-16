/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Controller;

import hatchi.model.Simon.SimonDAO;
import hatchi.model.Simon.SimonDTO;
import hatchi.model.User.UserDAO;
import hatchi.model.User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGUYEN HUY LINH
 */
@WebServlet(name = "ConfirmController", urlPatterns = {"/ConfirmController"})
public class ConfirmController extends HttpServlet {

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

            SimonDAO simonDAO = new SimonDAO();
            SimonDTO simonDTO;
            HttpSession session = request.getSession();

            UserDAO userDAO = new UserDAO();
            UserDTO userDTO = (UserDTO) session.getAttribute("usersession");
            String userID = String.valueOf(userDTO.getUserID());

            String itemID = request.getParameter("itemID");
            String itemName = request.getParameter("itemName");
            String itemImage = request.getParameter("itemImage");
            int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
//
//            LocalTime currentTime = LocalTime.now();
            LocalTime currentTime = LocalTime.now();
            Timestamp timestamp = Timestamp.valueOf(currentTime.atDate(LocalDate.now()));

            int userMoney = simonDAO.getUserMoney(userID);

            int userMoneyAfterBuy = userMoney - itemPrice;
            String confirm = request.getParameter("confirm");

            if (confirm.equalsIgnoreCase("YES")) {
                simonDTO = new SimonDTO(itemID, itemName, itemImage, userID, "null");
                request.setAttribute("simonDTO", simonDTO);

                //Insert Item to User 
                simonDAO.insertUserItem(itemID, userID, String.valueOf(itemPrice), String.valueOf(timestamp));

                //Change UserMoney
                simonDAO.decreaseUserMoney(userMoneyAfterBuy, userID);

                UserDTO newUserDTO = userDAO.getUser(String.valueOf(userDTO.getUserID()));
                session.setAttribute("usersession", newUserDTO);

                RequestDispatcher rd = request.getRequestDispatcher("congratulations.jsp");
                rd.forward(request, response);
            } else if (confirm.equalsIgnoreCase("NO")) {
                response.sendRedirect("store");
            }
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
