/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Controller;

import hatchi.model.Simon.SimonDAO;
import hatchi.model.Simon.SimonDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngocn
 */
public class ItemAdmin extends HttpServlet {

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
        request.setAttribute("itemClassName", "menu__list--item-active");

        int pageLength = 7;
        SimonDAO dao = new SimonDAO();

        if (action == null || action.equals("list")) {

            String indexPara = request.getParameter("index");
            int index = 1;

            if (indexPara != null) {
                index = Integer.parseInt(indexPara);
            }

            String searchValue = request.getParameter("searchValue");
            if (searchValue == null) {
                searchValue = "";
            }
            int totalPages = dao.getTotalPageBySearch(searchValue.trim());
            List<SimonDTO> list = dao.pagingItemBySearch(index, pageLength, "", searchValue.trim());

            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }

            request.setAttribute("endPage", endPage);
            request.setAttribute("list", list);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);

            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_items.jsp");
            rd.forward(request, response);
        } else if (action.equals("edit")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String itemLink = request.getParameter("link");
            String price = request.getParameter("price");

            SimonDTO item = new SimonDTO(id, name, itemLink, price, "true");

            boolean check = dao.update(item);
            if (!check) {
                request.setAttribute("mess", "Update item failed");
            } else {
                request.setAttribute("mess", "Update successfully");
            }

//            List<SimonDTO> list = dao.list();
            String indexPara = request.getParameter("index");
            int index = 1;

            if (indexPara != null) {
                index = Integer.parseInt(indexPara);
            }

            String searchValue = request.getParameter("searchValue");
            if (searchValue == null) {
                searchValue = "";
            }
            int totalPages = dao.getTotalPageBySearch(searchValue.trim());
            List<SimonDTO> list = dao.pagingItemBySearch(index, pageLength, "", searchValue.trim());

            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }

            request.setAttribute("endPage", endPage);
            request.setAttribute("list", list);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);
            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_items.jsp");
            rd.forward(request, response);

        } else if (action.equals("add")) {
            String id = null;
            String name = request.getParameter("name");
            String itemLink = request.getParameter("link");
            String price = request.getParameter("price");

            SimonDTO item = new SimonDTO(id, name, itemLink, price, "true");

            boolean check = dao.insert(item);
            if (!check) {
                request.setAttribute("mess", "Add new item failed");
            } else {
                request.setAttribute("mess", "Add successfully");
            }

//            List<SimonDTO> list = dao.list();
//            
//            request.setAttribute("list", list);
            String indexPara = request.getParameter("index");
            int index = 1;

            if (indexPara != null) {
                index = Integer.parseInt(indexPara);
            }

            String searchValue = request.getParameter("searchValue");
            if (searchValue == null) {
                searchValue = "";
            }
            int totalPages = dao.getTotalPageBySearch(searchValue.trim());
            List<SimonDTO> list = dao.pagingItemBySearch(index, pageLength, "", searchValue.trim());

            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }

            request.setAttribute("endPage", endPage);
            request.setAttribute("list", list);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);

            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_items.jsp");
            rd.forward(request, response);

        } else if (action.equals("delete")) {
            String id = null;

            try {
                id = request.getParameter("id");
            } catch (NumberFormatException e) {
                System.out.println("***ERR get tagID" + e);
            }
            System.out.println("***ID: " + id);

            if (id != null) {
                boolean check = dao.delete(id);
                if (check) {
                    request.setAttribute("mess", "Delete Sucessfully");
                } else {
                    request.setAttribute("mess", "This tag is already used");
                }
            }
            String indexPara = request.getParameter("index");
            int index = 1;

            if (indexPara != null) {
                index = Integer.parseInt(indexPara);
            }

            String searchValue = request.getParameter("searchValue");
            if (searchValue == null) {
                searchValue = "";
            }
            int totalPages = dao.getTotalPageBySearch(searchValue.trim());
            List<SimonDTO> list = dao.pagingItemBySearch(index, pageLength, "", searchValue.trim());

            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }

            request.setAttribute("endPage", endPage);
            request.setAttribute("list", list);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);

            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_items.jsp");
            rd.forward(request, response);

        } else if (action.equals("search")) {
            String indexPara = request.getParameter("index");
            int index = 1;

            if (indexPara != null) {
                index = Integer.parseInt(indexPara);
            }

            String searchValue = request.getParameter("searchValue");
            if (searchValue == null) {
                searchValue = "";
            }
            int totalPages = dao.getTotalPageBySearch(searchValue.trim());
            List<SimonDTO> list = dao.pagingItemBySearch(index, pageLength, "", searchValue.trim());

            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }

            request.setAttribute("endPage", endPage);
            request.setAttribute("list", list);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);

            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_items.jsp");
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
