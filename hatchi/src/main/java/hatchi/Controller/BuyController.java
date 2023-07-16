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
import java.time.LocalTime;
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
@WebServlet(name = "BuyController", urlPatterns = {"/BuyController"})
public class BuyController extends HttpServlet {

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
            // itemID by parameter 

            String itemID = request.getParameter("itemID");

            HttpSession session = request.getSession();
            
            UserDTO userDTO = (UserDTO) session.getAttribute("usersession");
            String userID = String.valueOf(userDTO.getUserID());

            SimonDAO simonDAO = new SimonDAO();

            SimonDTO simonDTO = simonDAO.loadSimon(itemID);
            int price = Integer.parseInt(simonDTO.getPrice());
            int userMoney = simonDAO.getUserMoney(userID);

            LocalTime currentTime = LocalTime.now();

            int userMoneyAfterBuy = userMoney - price;
            if (userMoneyAfterBuy >= 0){
                request.setAttribute("simonItem", simonDTO);
                RequestDispatcher rd = request.getRequestDispatcher("confirm.jsp");
                rd.forward(request, response);
            }else{
                int needCoin = userMoneyAfterBuy * -1;
                request.setAttribute("needCoin", needCoin);
                request.setAttribute("simonDTO", simonDTO);
                RequestDispatcher rd = request.getRequestDispatcher("notEnoughMoney.jsp");
                rd.forward(request, response);
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
