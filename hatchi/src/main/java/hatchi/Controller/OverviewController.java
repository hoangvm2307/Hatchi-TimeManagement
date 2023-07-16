/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Controller;

import hatchi.Tag.TagDAO;
import hatchi.UserStatistic.UserStatDayDAO;
import hatchi.UserStatistic.UserStatDayDTO;
import hatchi.UserStatistic.UserStatMonthDAO;
import hatchi.UserStatistic.UserStatMonthDTO;
import hatchi.UserStatistic.UserStatWeekDAO;
import hatchi.UserStatistic.UserStatWeekDTO;
import hatchi.model.User.UserDTO;
import io.quickchart.QuickChart;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class OverviewController extends HttpServlet {

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
            HttpSession session = request.getSession(false);
            if (!session.getAttribute("MenuAction").equals("Overview") || session.getAttribute("MenuAction") == null) {
                session.setAttribute("MenuAction", request.getParameter("MenuAction"));
            }
            UserDTO user = (UserDTO) session.getAttribute("usersession");
            String activeNav = request.getParameter("activeNav");
            String chartConfig;
            String chartTagConfig;
            if (activeNav != null) {
                switch (activeNav) {
                    case "Day":
                        UserStatDayDTO userStatDay = new UserStatDayDAO().loadFull(user, 0, LocalDate.now());
                        chartConfig = "{"
                                + "type: 'bar',"
                                + "data: {"
                                + "labels: [";
                        for (int i = 0; i < 24; i++) {
                            if (i == 23) {
                                chartConfig += "'" + i + "'";
                            } else {
                                chartConfig += "'" + i + "', ";
                            }
                        }
                        chartConfig += "],"
                                + "datasets: [{"
                                + "label: 'Minutes',"
                                + "data: [";
                        for (int i = 0; i < 24; i++) {
                            if (i == 23) {
                                chartConfig += userStatDay.getHoursTime().get(i);
                            } else {
                                chartConfig += userStatDay.getHoursTime().get(i) + ", ";
                            }
                        }
                        chartConfig += "]"
                                + "}]"
                                + "},"
                                + "options:{"
                                + "scales: {"
                                + "y: {"
                                + "beginAtZero: true"
                                + "}"
                                + "}"
                                + "}"
                                + "}";
                        chartTagConfig = "{"
                                + "type: 'doughnut',"
                                + "data: {"
                                + "labels: [";
                        for (Map.Entry<Integer, Integer> entry : userStatDay.getTagTime().entrySet()) {
                            chartTagConfig += "'" + new TagDAO().load(entry.getKey()).getName() + "', ";
                        }
                        chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                        chartTagConfig += "],"
                                + "datasets: [{"
                                + "label: 'Time',"
                                + "data: [";
                        for (Map.Entry<Integer, Integer> entry : userStatDay.getTagTime().entrySet()) {
                            chartTagConfig += entry.getValue() + ", ";
                        }
                        chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                        chartTagConfig += "],"
                                + "backgroundColor: [";
                        for (Map.Entry<Integer, Integer> entry : userStatDay.getTagTime().entrySet()) {
                            chartTagConfig += "'" + new TagDAO().load(entry.getKey()).getColor() + "', ";
                        }
                        chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                        chartTagConfig += "]"
                                + "}]"
                                + "}"
                                + "}";
                        request.setAttribute("chartConfig", chartConfig);
                        request.setAttribute("chartTagConfig", chartTagConfig);
                        request.setAttribute("hours", userStatDay.getTotalMinutes() / 60);
                        request.setAttribute("minutes", userStatDay.getTotalMinutes() % 60);
                        break;
                    case "Week":
                        UserStatWeekDTO userStatWeek = new UserStatWeekDAO().loadFull(user, 0, LocalDate.now());

                        chartConfig = "{"
                                + "type: 'bar',"
                                + "data: {"
                                + "labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'";
                        chartConfig += "],"
                                + "datasets: [{"
                                + "label: 'Minutes',"
                                + "data: [";
                        for (int i = 0; i < 7; i++) {
                            if (i == 23) {
                                chartConfig += userStatWeek.getDaysTimes().get(i);
                            } else {
                                chartConfig += userStatWeek.getDaysTimes().get(i) + ", ";
                            }
                        }
                        chartConfig += "]"
                                + "}]"
                                + "},"
                                + "options:{"
                                + "scales: {"
                                + "y: {"
                                + "beginAtZero: true"
                                + "}"
                                + "}"
                                + "}"
                                + "}";
                        chartTagConfig = "{"
                                + "type: 'doughnut',"
                                + "data: {"
                                + "labels: [";
                        for (Map.Entry<Integer, Integer> entry : userStatWeek.getTagTimes().entrySet()) {
                            chartTagConfig += "'" + new TagDAO().load(entry.getKey()).getName() + "', ";
                        }
                        chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                        chartTagConfig += "],"
                                + "datasets: [{"
                                + "label: 'Time',"
                                + "data: [";
                        for (Map.Entry<Integer, Integer> entry : userStatWeek.getTagTimes().entrySet()) {
                            chartTagConfig += entry.getValue() + ", ";
                        }
                        chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                        chartTagConfig += "],"
                                + "backgroundColor: [";
                        for (Map.Entry<Integer, Integer> entry : userStatWeek.getTagTimes().entrySet()) {
                            chartTagConfig += "'" + new TagDAO().load(entry.getKey()).getColor() + "', ";
                        }
                        chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                        chartTagConfig += "]"
                                + "}]"
                                + "}"
                                + "}";

                        request.setAttribute("chartConfig", chartConfig);
                        request.setAttribute("chartTagConfig", chartTagConfig);
                        request.setAttribute("hours", userStatWeek.getTotalMinutes() / 60);
                        request.setAttribute("minutes", userStatWeek.getTotalMinutes() % 60);

                        break;
                    case "Month":
                        UserStatMonthDTO userStatMonth = new UserStatMonthDAO().loadFull(user, 0, LocalDate.now());
                        chartConfig = "{"
                                + "type: 'bar',"
                                + "data: {"
                                + "labels: [";
                        for (int i = 0; i < LocalDate.now().lengthOfMonth(); i++) {
                            if (i == LocalDate.now().lengthOfMonth()-1) {
                                chartConfig += "'" + i + "'";
                            } else {
                                chartConfig += "'" + i + "', ";
                            }
                        }
                        chartConfig += "],"
                                + "datasets: [{"
                                + "label: 'Minutes',"
                                + "data: [";
                        for (int i = 0; i < LocalDate.now().lengthOfMonth(); i++) {
                            if (i == LocalDate.now().lengthOfMonth()-1) {
                                chartConfig += userStatMonth.getDaysTimes().get(i);
                            } else {
                                chartConfig += userStatMonth.getDaysTimes().get(i) + ", ";
                            }
                        }
                        chartConfig += "]"
                                + "}]"
                                + "},"
                                + "options:{"
                                + "scales: {"
                                + "y: {"
                                + "beginAtZero: true"
                                + "}"
                                + "}"
                                + "}"
                                + "}";
                        chartTagConfig = "{"
                                + "type: 'doughnut',"
                                + "data: {"
                                + "labels: [";
                        for (Map.Entry<Integer, Integer> entry : userStatMonth.getTagTimes().entrySet()) {
                            chartTagConfig += "'" + new TagDAO().load(entry.getKey()).getName() + "', ";
                        }
                        chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                        chartTagConfig += "],"
                                + "datasets: [{"
                                + "label: 'Time',"
                                + "data: [";
                        for (Map.Entry<Integer, Integer> entry : userStatMonth.getTagTimes().entrySet()) {
                            chartTagConfig += entry.getValue() + ", ";
                        }
                        chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                        chartTagConfig += "],"
                                + "backgroundColor: [";
                        for (Map.Entry<Integer, Integer> entry : userStatMonth.getTagTimes().entrySet()) {
                            chartTagConfig += "'" + new TagDAO().load(entry.getKey()).getColor() + "', ";
                        }
                        chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                        chartTagConfig += "]"
                                + "}]"
                                + "}"
                                + "}";
                        request.setAttribute("chartConfig", chartConfig);
                        request.setAttribute("chartTagConfig", chartTagConfig);
                        request.setAttribute("hours", userStatMonth.getTotalMinutes() / 60);
                        request.setAttribute("minutes", userStatMonth.getTotalMinutes() % 60);

                        break;
                }

                session.setAttribute("activeNav", activeNav);
            } else {
                UserStatDayDTO userStatDay = new UserStatDayDAO().loadFull(user, 0, LocalDate.now());
                chartConfig = "{"
                        + "type: 'bar',"
                        + "data: {"
                        + "labels: [";
                for (int i = 0; i < 24; i++) {
                    if (i == 23) {
                        chartConfig += "'" + (i + 1) + "'";
                    } else {
                        chartConfig += "'" + (i + 1) + "', ";
                    }
                }
                chartConfig += "],"
                        + "datasets: [{"
                        + "label: 'Minutes',"
                        + "data: [";
                for (int i = 0; i < 24; i++) {
                    if (i == 23) {
                        chartConfig += userStatDay.getHoursTime().get(i);
                    } else {
                        chartConfig += userStatDay.getHoursTime().get(i) + ", ";
                    }
                }
                chartConfig += "]"
                        + "}]"
                        + "},"
                        + "options:{"
                        + "scales: {"
                        + "y: {"
                        + "beginAtZero: true"
                        + "}"
                        + "}"
                        + "}"
                        + "}";
                chartTagConfig = "{"
                        + "type: 'doughnut',"
                        + "data: {"
                        + "labels: [";
                for (Map.Entry<Integer, Integer> entry : userStatDay.getTagTime().entrySet()) {
                    chartTagConfig += "'" + new TagDAO().load(entry.getKey()).getName() + "', ";
                }
                chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                chartTagConfig += "],"
                        + "datasets: [{"
                        + "label: 'Time',"
                        + "data: [";
                for (Map.Entry<Integer, Integer> entry : userStatDay.getTagTime().entrySet()) {
                    chartTagConfig += entry.getValue() + ", ";
                }
                chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                chartTagConfig += "],"
                        + "backgroundColor: [";
                for (Map.Entry<Integer, Integer> entry : userStatDay.getTagTime().entrySet()) {
                    chartTagConfig += "'" + new TagDAO().load(entry.getKey()).getColor() + "', ";
                }
                chartTagConfig = chartTagConfig.substring(0, chartTagConfig.length() - 2);
                chartTagConfig += "]"
                        + "}]"
                        + "}"
                        + "}";
                request.setAttribute("chartConfig", chartConfig);
                request.setAttribute("chartTagConfig", chartTagConfig);
                request.setAttribute("hours", userStatDay.getTotalMinutes() / 60);
                request.setAttribute("minutes", userStatDay.getTotalMinutes() % 60);
                session.setAttribute("activeNav", "Day");
            }
        } catch (Exception e) {

        }

        RequestDispatcher rd = request.getRequestDispatcher("overview.jsp");
        rd.forward(request, response);
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
