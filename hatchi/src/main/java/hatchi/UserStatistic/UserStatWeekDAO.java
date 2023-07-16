/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.UserStatistic;

import hatchi.Tag.TagDAO;
import hatchi.Utils.DBUtils;
import hatchi.Utils.StopWatch;
import hatchi.model.User.UserDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class UserStatWeekDAO {

    public void loadWeek(UserDTO user, UserStatWeekDTO userStatWeek) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select userID from UserTimeWeek where (? between weekStart and weekEnd) and userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, user.getUserID());
            rs = ps.executeQuery();
            if (rs.next()) {
                userStatWeek.setUserID(rs.getInt("userID"));
            }
        } catch (Exception e) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public void loadWeekTag(UserDTO user, String tagName, UserStatWeekDTO userStatWeek) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select userID, tagID, timeSpent from TimeSpentInTagWeek where (? between weekStart and weekEnd) and tagID = ? and userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, new TagDAO().getTagID(tagName));
            ps.setInt(3, user.getUserID());
            rs = ps.executeQuery();
            if (rs.next()) {
                userStatWeek.setUserIDTag(rs.getInt("userID"));
                userStatWeek.getTagTimes().put(rs.getInt("tagID"), rs.getInt("timeSpent"));
            }
        } catch (Exception e) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public UserStatWeekDTO loadFull(UserDTO user, int daysToSubtract, LocalDate date) throws SQLException, NamingException {
        UserStatWeekDTO result = new UserStatWeekDTO();
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        LocalDate now = date.minusDays(daysToSubtract), monday = now, sunday = now;
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.minusDays(1);
        }

        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            sunday = sunday.plusDays(1);
        }
        try {
            String sql = "select * from UserTimeWeek where userID = 1 and weekStart = '2023-03-20' and weekEnd = '2023-03-26'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                result.setUserID(user.getUserID());
                result.setWeekStart(rs.getDate("weekStart"));
                result.setWeekEnd(rs.getDate("weekEnd"));
                result.setTotalMinutes(rs.getInt("timeSpent"));
            }
            sql = "select * from TimeSpentInTagWeek where userID = ? and weekStart = ? and weekEnd = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setDate(2, Date.valueOf(monday));
            ps.setDate(3, Date.valueOf(sunday));
            rs = ps.executeQuery();
            while (rs.next()) {
                result.getTagTimes().put(rs.getInt("tagID"), rs.getInt("timeSpent"));
            }
            for (LocalDate i = monday; i.isBefore(sunday.plusDays(1)); i = i.plusDays(1)) {
                UserStatDayDTO statDay = new UserStatDayDAO().loadFull(user, 0, i);
                if (statDay.getUserID() != 0) result.getDaysTimes().add(statDay.getTotalMinutes());
                else result.getDaysTimes().add(0);
            }
        } catch (SQLException e) {

        } finally {
        }
        return result;
    }

    public void insertWeek(UserDTO user) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into UserTimeWeek(userID, weekStart, weekEnd) values (?, ?, ?)";
            LocalDate now = LocalDate.now();
            LocalDate monday = now;
            LocalDate sunday = now;
            while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
                monday = monday.minusDays(1);
            }

            while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
                sunday = sunday.plusDays(1);
            }
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setDate(2, Date.valueOf(monday));
            ps.setDate(3, Date.valueOf(sunday));
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
        }
    }

    public void insertTagWeek(UserDTO user, String tagName) throws SQLException, NamingException {
        int tagID = 0;
        TagDAO tagDAO = new TagDAO();
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        try {
            tagID = tagDAO.getTagID(tagName);

            String sql = "insert into TimeSpentInTagWeek(userID, tagID, weekStart, weekEnd) values (?, ?, ?, ?)";
            LocalDate now = LocalDate.now();
            LocalDate monday = now;
            LocalDate sunday = now;
            while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
                monday = monday.minusDays(1);
            }

            while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
                sunday = sunday.plusDays(1);
            }
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setInt(2, tagID);
            ps.setDate(3, Date.valueOf(monday));
            ps.setDate(4, Date.valueOf(sunday));
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
        }
    }

    public void update(UserDTO user, String tagName, StopWatch stopwatch) throws SQLException, NamingException {
        int tagID = 0;
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        PreparedStatement tagIDPs = null;
        PreparedStatement tagTimeps = null;
        ResultSet rs = null;
        TagDAO tagDAO = new TagDAO();
        try {
            int minutesPassed = stopwatch.getTimePassed() / 60;
            tagID = tagDAO.getTagID(tagName);
            String sql = "update UserTimeWeek "
                    + "set timeSpent = iif (timeSpent is null, ? , timeSpent + ?) "
                    + "where (? between weekStart and weekEnd) and userID = ?";
            ps = conn.prepareStatement(sql);
            String tagTimeSql = "update TimeSpentInTagWeek "
                    + "set timeSpent = iif (timeSpent is null, ? , timeSpent + ?) "
                    + "where (? between weekStart and weekEnd) and userID = ? and tagID = ?";
            tagTimeps = conn.prepareStatement(tagTimeSql);
            ps.setInt(1, minutesPassed);
            ps.setInt(2, minutesPassed);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setInt(4, user.getUserID());
            tagTimeps.setInt(1, minutesPassed);
            tagTimeps.setInt(2, minutesPassed);
            tagTimeps.setDate(3, Date.valueOf(LocalDate.now()));
            tagTimeps.setInt(4, user.getUserID());
            tagTimeps.setInt(5, tagID);
            ps.executeUpdate();
            tagTimeps.executeUpdate();
        } catch (Exception e) {

        } finally {
        }
    }
}
