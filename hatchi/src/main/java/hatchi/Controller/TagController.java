/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package hatchi.Controller;

import hatchi.Tag.TagDAO;
import hatchi.Tag.TagDTO;
import java.io.IOException;
import static java.lang.System.out;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DUNGHUYNH
 */
public class TagController extends HttpServlet {

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

        String action = request.getParameter("action");
        request.setAttribute("tagClassName", "menu__list--item-active");

        TagDAO tagDAO = new TagDAO();
        
        if (action == null || action.equals("list")) {
            List<TagDTO> list = tagDAO.getTags();

            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_tags.jsp");
            rd.forward(request, response);
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String color = request.getParameter("color");
            
            TagDTO tag = new TagDTO(id, name, color);
            
            boolean check = tagDAO.update(tag);
            if(!check){
                request.setAttribute("mess", "Update tag failed");
            }else {
                request.setAttribute("mess", "Update successfully");
            }
            
            List<TagDTO> list = tagDAO.getTags();

            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_tags.jsp");
            rd.forward(request, response);
            
        } else if (action.equals("add")) {
            int id = 0;
            String name = request.getParameter("name");
            String color = request.getParameter("color");
            
            TagDTO tag = new TagDTO(id, name, color);
            
            boolean check = tagDAO.insert(tag);
            if(!check){
                request.setAttribute("mess", "Add new tag failed");
            }else {
                request.setAttribute("mess", "Add successfully");
            }
            
            List<TagDTO> list = tagDAO.getTags();

            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_tags.jsp");
            rd.forward(request, response);
            
        } else if (action.equals("delete")) {
            int id = 0;
            
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                System.out.println("***ERR get tagID" +e);
            }
            System.out.println("***ID: " + id);
            
            if(id != 0){
                boolean check = tagDAO.delete(id);
                if(!check){
                    request.setAttribute("mess", "This tag is already used");
                } else {
                    request.setAttribute("mess", "Delete Sucessfully"); //Chinh o day
                }
            }
            
            List<TagDTO> list = tagDAO.getTags();

            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_tags.jsp");
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
