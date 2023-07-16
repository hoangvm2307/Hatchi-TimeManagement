/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Controller;

import hatchi.Jobs.UpdateDatabaseJob;
import hatchi.Tag.TagDAO;
import hatchi.UserStatistic.UserStatDayDAO;
import hatchi.UserStatistic.UserStatDayDTO;
import hatchi.UserStatistic.UserStatMonthDAO;
import hatchi.UserStatistic.UserStatMonthDTO;
import hatchi.UserStatistic.UserStatWeekDAO;
import hatchi.UserStatistic.UserStatWeekDTO;
import hatchi.Utils.StopWatch;
import hatchi.model.User.UserDAO;
import hatchi.model.User.UserDTO;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.JobDetail;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import org.quartz.JobKey;

/**
 *
 * @author ADMIN
 */
public class DatabaseController extends HttpServlet {

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
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            TagDAO tagDAO = new TagDAO();
            UserDAO userDAO = new UserDAO();
            UserDTO user = (UserDTO) session.getAttribute("usersession");
            StopWatch stopWatch = (StopWatch) session.getAttribute("clock");
            UserStatDayDAO statDayDAO = new UserStatDayDAO();
            UserStatWeekDAO statWeekDAO = new UserStatWeekDAO();
            UserStatMonthDAO statMonthDAO = new UserStatMonthDAO();
            String tagName = request.getParameter("tagname");
            if (action != null) {
                switch (action) {
                    case "start":
                        if (quartzScheduler.getJobDetail(JobKey.jobKey("updateDatabaseJob", user.getUsername())) == null) {
                            JobDetail updateDatabaseJob = newJob(UpdateDatabaseJob.class)
                                    .withIdentity("updateDatabaseJob", user.getUsername())
                                    .usingJobData("userID", user.getUserID())
                                    .usingJobData("tagname", tagName)
                                    .build();
                            updateDatabaseJob.getJobDataMap().put("stopwatch", stopWatch);
                            CronTrigger updateDatabaseTrigger = newTrigger().withIdentity("updateDatabaseTrigger", "updateTrigger").withSchedule(cronSchedule("0 0 * * * ?")).build();
                            quartzScheduler.scheduleJob(updateDatabaseJob, updateDatabaseTrigger);
                        }
                        break;
                    case "stop":
                        quartzScheduler.deleteJob(JobKey.jobKey("updateDatabaseJob", user.getUsername()));
                        break;
                    case "insert":
                        UserStatDayDTO userStatDay = new UserStatDayDTO();
                        UserStatWeekDTO userStatWeek = new UserStatWeekDTO();
                        UserStatMonthDTO userStatMonth = new UserStatMonthDTO();
                        statDayDAO.loadDay(user, userStatDay);
                        statDayDAO.loadDayTag(user, tagName, userStatDay);
                        statWeekDAO.loadWeek(user, userStatWeek);
                        statWeekDAO.loadWeekTag(user, tagName, userStatWeek);
                        statMonthDAO.loadMonth(user, userStatMonth);
                        statMonthDAO.loadMonthTag(user, tagName, userStatMonth);
                        if (userStatDay.getUserID() == 0) {
                            statDayDAO.insertDay(user);
                        }
                        if (userStatDay.getUserIDTag() == 0 && !userStatDay.getTagTime().containsKey(tagDAO.getTagID(tagName))) {
                            statDayDAO.insertTagDay(user, tagName);
                        }
                        if (userStatWeek.getUserID() == 0) {
                            statWeekDAO.insertWeek(user);
                        }
                        if (userStatWeek.getUserIDTag() == 0 && !userStatWeek.getTagTimes().containsKey(tagDAO.getTagID(tagName))) {
                            statWeekDAO.insertTagWeek(user, tagName);
                        }
                        if (userStatMonth.getUserID() == 0) {
                            statMonthDAO.insertMonth(user);
                        }
                        if (userStatMonth.getUserIDTag() == 0 && !userStatMonth.getTagTimes().containsKey(tagDAO.getTagID(tagName))) {
                            statMonthDAO.insertTagMonth(user, tagName);
                        }
                        break;
                    case "update":
                        userDAO.updateMoney(stopWatch.getTimePassed() / 60, user.getUserID());
                        statDayDAO.update(user, tagName, stopWatch);
                        statWeekDAO.update(user, tagName, stopWatch);
                        statMonthDAO.update(user, tagName, stopWatch);
                        break;

                }
            }
        } catch (Exception e) {

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
