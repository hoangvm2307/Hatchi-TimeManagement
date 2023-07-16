/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Controller;

import hatchi.Utils.StopWatch;
import hatchi.model.User.UserDAO;
import hatchi.model.User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author hoangvmdeptrai
 */
public class TimerController extends HttpServlet {

    private Scheduler quartzScheduler;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        super.init(cfg);
        String key = "org.quartz.impl.StdSchedulerFactory.KEY";
        ServletContext servletContext = cfg.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) servletContext.getAttribute(key);
        try {
            quartzScheduler = factory.getScheduler("MyQuartzScheduler");
            quartzScheduler.start();
        } catch (SchedulerException e) {

        }
    }

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
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login");
        } else {
            String MenuAction = "Timer";
            session.setAttribute("MenuAction", MenuAction);

            StopWatch stopWatch;
            if ((StopWatch) session.getAttribute("clock") == null) {
                session.setAttribute("clock", new StopWatch());
            }
            stopWatch = (StopWatch) session.getAttribute("clock");
            UserDAO userDAO = new UserDAO();
            UserDTO user = (UserDTO) session.getAttribute("usersession");
            String action = request.getParameter("action");
            boolean running = stopWatch.isRunning();
            if (action != null) {
                switch (action) {
                    case "decrease":
                        if (!running) {
                            stopWatch.subtractStartingTime(300);
                            try (PrintWriter out = response.getWriter()) {
                                out.print("<p id=\"timer\">" + (stopWatch.getStartingTimerMinutes() < 10 ? "0" + stopWatch.getStartingTimerMinutes() : stopWatch.getStartingTimerMinutes()) + ":" + (stopWatch.getStartingTimerSeconds() < 10 ? "0" + stopWatch.getStartingTimerSeconds() : stopWatch.getStartingTimerSeconds()) + "</p>\n");
                                out.print("<p id=\"coin\">" + stopWatch.getStartingTimerMinutes() + "</p>");
                            }
                        }
                        break;
                    case "increase":
                        if (!running) {
                            stopWatch.addStartingTime(300);
                            try (PrintWriter out = response.getWriter()) {
                                out.print("<p id=\"timer\">" + (stopWatch.getStartingTimerMinutes() < 10 ? "0" + stopWatch.getStartingTimerMinutes() : stopWatch.getStartingTimerMinutes()) + ":" + (stopWatch.getStartingTimerSeconds() < 10 ? "0" + stopWatch.getStartingTimerSeconds() : stopWatch.getStartingTimerSeconds()) + "</p>\n");
                                out.print("<p id=\"coin\">" + stopWatch.getStartingTimerMinutes() + "</p>");
                            }
                        }
                        break;
                    case "start":
                        stopWatch.start();
                        break;
                    case "stop":
                        stopWatch.reset();
                        user.setMoney(userDAO.getMoney(user.getUserID()));
                        session.setAttribute("usersession", user);
                        try (PrintWriter out = response.getWriter()) {
                            out.print("<p id=\"timer\">" + (stopWatch.getStartingTimerMinutes() < 10 ? "0" + stopWatch.getStartingTimerMinutes() : stopWatch.getStartingTimerMinutes()) + ":" + (stopWatch.getStartingTimerSeconds() < 10 ? "0" + stopWatch.getStartingTimerSeconds() : stopWatch.getStartingTimerSeconds()) + "</p>\n");
                            out.print("<p id=\"coin\">" + stopWatch.getStartingTimerMinutes() + "</p>\n");
                            out.print("<p id=\"money\">" + userDAO.getMoney(user.getUserID()) + "</p>");
                        }
                        break;
                    case "gettime":
                        if (running) {
                            stopWatch.tick();
                            try (PrintWriter out = response.getWriter()) {
                                out.print("<p id=\"timer\">" + (stopWatch.getTimerMinutes() < 10 ? "0" + stopWatch.getTimerMinutes() : stopWatch.getTimerMinutes()) + ":" + (stopWatch.getTimerSeconds() < 10 ? "0" + stopWatch.getTimerSeconds() : stopWatch.getTimerSeconds()) + "</p>");
                            }
                        }
                        break;
                }
            } else {
                session.setAttribute("loaded", false);
                stopWatch.refresh();
                RequestDispatcher rd = request.getRequestDispatcher("timer.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TimerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TimerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TimerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TimerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
