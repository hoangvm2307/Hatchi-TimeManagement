/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Controller;

import hatchi.Tag.TagDAO;
import hatchi.Tag.TagDTO;
import hatchi.Utils.StopWatch;
import hatchi.model.Simon.SimonDAO;
import hatchi.model.User.UserDAO;
import hatchi.model.User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoangvmdeptrai
 */
public class AccountController extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("userClassName", "menu__list--item-active");

        System.out.println("***" + username);
        System.out.println("***" + password);
//            RequestDispatcher rd1 = request.getRequestDispatcher("menu.html");
//            rd1.include(request, response);
        if (action != null && action.equals("create")) {
            RequestDispatcher rd = request.getRequestDispatcher("signUp.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("insert")) {
            System.out.println(action.toUpperCase());
            UserDTO userDTO = null;
            UserDAO userDAO = new UserDAO();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String email = request.getParameter("email");
            username = request.getParameter("username");
            password = request.getParameter("password");
            // Validate Insert
            if (userDAO.usernameNotExist(username) && userDAO.emailNotExist(email)) {
                userDTO = new UserDTO();
                userDTO.setUsername(username);
                userDTO.setPassword(password);
                userDTO.setEmail(email);
                userDTO.setRole("US");
                userDTO.setMoney(0);
                userDTO.setTime(0);
                userDTO.setCreateAt(dtf.format(now));

                userDAO.insert(userDTO);
                System.out.println("ADD SUCCESSFULLY");
            } else {
                // username or email exist, go back to signUp
                RequestDispatcher rd = request.getRequestDispatcher("signUp.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = request.getRequestDispatcher("signIn.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("logout")) {
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }
        } else if (action != null && action.equals("edit")) {// Action edit gọi từ menuAdmin
            //=====================EDIT======================
            UserDAO userDAO = new UserDAO();
//            ArrayList<UserDTO> users = userDAO.getAllUsers();

            int index = 1;
            int pageLength = 7;
            String searchValue = request.getParameter("Searchvalue");
            if (searchValue == null) {
                searchValue = "";
            }
            int totalPages = userDAO.PagingCountUserAccounts(searchValue);

            ArrayList<UserDTO> users = userDAO.pagingUserAccount(index, pageLength, searchValue);
            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }
            request.setAttribute("endPage", endPage);
            request.setAttribute("users", users);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);

            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_users.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("update")) { // Action update gọi từ admin manage users
            //=====================UPDATE======================
            String idToUpdate = request.getParameter("id");
            UserDAO userDAO = new UserDAO();
            UserDTO userDTO = userDAO.getUser(idToUpdate);

            System.out.println("ID TO UPDATE: " + idToUpdate);
            String name = request.getParameter("username");
            String pass = request.getParameter("password");
            String email = request.getParameter("email");
            String money = request.getParameter("money");
//            String time = request.getParameter("time");
            String time = "";
            UserDTO newUserDTO = new UserDTO();
            if (!name.equals("")) {
                newUserDTO.setUsername(name);
            } else {
                newUserDTO.setUsername(userDTO.getUsername());
            }
            if (!pass.equals("")) {
                newUserDTO.setPassword(pass);
            } else {
                System.out.println(userDTO.getPassword());
                newUserDTO.setPassword(userDTO.getPassword());
            }
            if (!email.equals("")) {
                newUserDTO.setEmail(email);
            } else {
                newUserDTO.setEmail(userDTO.getEmail());
            }
            if (!money.equals("")) {
                newUserDTO.setMoney(Integer.parseInt(money));
            } else {
                newUserDTO.setMoney(userDTO.getMoney());
            }
            if (!time.equals("")) {
                newUserDTO.setTime(Integer.parseInt(time));
            } else {
                newUserDTO.setTime(userDTO.getTime());
            }

            boolean check = userDAO.update(newUserDTO, idToUpdate);
            if (!check) {
                request.setAttribute("mess", "Update user failed");
            } else {
                request.setAttribute("mess", "Update user successfully");
            }

            //List Users
            int index = 1;
            int pageLength = 7;
            String searchValue = request.getParameter("Searchvalue");
            if (searchValue == null) {
                searchValue = "";
            }
            int totalPages = userDAO.PagingCountUserAccounts(searchValue);

            ArrayList<UserDTO> users = userDAO.pagingUserAccount(index, pageLength, searchValue);
            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }
            request.setAttribute("endPage", endPage);
            request.setAttribute("users", users);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);

//            ArrayList<UserDTO> users = userDAO.getAllUsers();
            request.setAttribute("users", users);
            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_users.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("delete")) {
            UserDAO userDAO = new UserDAO();
            String idToDelete = request.getParameter("id");
            boolean check = userDAO.delete(idToDelete);
            if (!check) {
                request.setAttribute("mess", "This User cannot be deleted");
            } else {
                request.setAttribute("mess", "Delete user successfully");
            }

            int index = 1;
            int pageLength = 7;
            String searchValue = request.getParameter("Searchvalue");
            if (searchValue == null) {
                searchValue = "";
            }
            int totalPages = userDAO.PagingCountUserAccounts(searchValue);

            ArrayList<UserDTO> users = userDAO.pagingUserAccount(index, pageLength, searchValue);
            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }
            request.setAttribute("endPage", endPage);
            request.setAttribute("users", users);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);

//            ArrayList<UserDTO> users = userDAO.getAllUsers();
            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_users.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("search")) {

            UserDAO dao = new UserDAO();

            String searchValue = request.getParameter("searchValue");

            if (searchValue == null) {
                searchValue = "";
            }
//            ArrayList<UserDTO> list = dao.search(name);
            String indexPara = request.getParameter("index");
            int index = 1;
            int pageLength = 7;

            if (indexPara != null) {
                index = Integer.parseInt(indexPara);
            }

//            System.out.println("***LIST: " + list);
//            request.setAttribute("users", list
            int totalPages = dao.PagingCountUserAccounts(searchValue);
            ArrayList<UserDTO> users = dao.pagingUserAccount(index, pageLength, searchValue);
            int endPage = totalPages / pageLength;

            if (totalPages % pageLength != 0) {
                endPage++;
            }

            request.setAttribute("endPage", endPage);
            request.setAttribute("users", users);
            request.setAttribute("tagActive", index);
            request.setAttribute("searchValue", searchValue);

            RequestDispatcher rd = request.getRequestDispatcher("admin_manage_users.jsp");
            rd.forward(request, response);

        } else {
            log("Debug user : " + username + " " + password);
            // no user and password input yet, go to signIn.jsp
            if (username == null && password == null) {
                log("Debug user : Go to login " + username + " " + password);
                RequestDispatcher rd = request.getRequestDispatcher("signIn.jsp");
                rd.forward(request, response);
            } else { //User and password input, create userDAO

                log("Debug user : Go to here " + username + " " + password);
                UserDAO userDAO = new UserDAO();
                UserDTO userDTO = userDAO.login(username, password);
                SimonDAO simonDAO = new SimonDAO();

                //Get All Items in Stores
                int Items = simonDAO.getTotalPage();
                TagDAO tagDAO = new TagDAO();
                List<TagDTO> tagList = tagDAO.getTags();

                if (userDTO != null && userDTO.getRole().equals("US")) { //Login successfully

                    System.out.println("login successfully");
                    HttpSession session = request.getSession(true);

                    //gatcha Simon
                    boolean isHaveSimon = simonDAO.isHaveSimon(String.valueOf(userDTO.getUserID()));

                    if (isHaveSimon == false) {
                        int min = 1;
                        int max = 8;
                        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                        LocalTime currentTime = LocalTime.now();
                        Timestamp timestamp = Timestamp.valueOf(currentTime.atDate(LocalDate.now()));

                        simonDAO.insertUserItem(String.valueOf(random_int), String.valueOf(userDTO.getUserID()), "0", String.valueOf(timestamp));
                    }

                    session.setAttribute("MenuAction", "Simon");
                    session.setAttribute("usersession", userDTO);
                    session.setAttribute("allTags", tagList);
                    session.setAttribute("allItems", Items); //set  All items in Store to session

                    response.sendRedirect("simon");

                } else if (userDTO != null && userDTO.getRole().equals("AD")) {
                    System.out.println("login successfully");
                    HttpSession session = request.getSession(true);

                    session.setAttribute("usersession", userDTO);
                    session.setAttribute("allTags", tagList);
                    session.setAttribute("allItems", Items);

                    response.sendRedirect("edit?action=edit");
                } else {
                    request.setAttribute("error", "Wrong username or password");
                    RequestDispatcher rd = request.getRequestDispatcher("signIn.jsp");
                    rd.forward(request, response);
                }
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
