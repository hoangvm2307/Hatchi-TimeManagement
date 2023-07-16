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
import java.time.LocalDate;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class UserStatMonthDAO {

    public void loadMonth(UserDTO user, UserStatMonthDTO userStatMonth) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select userID from UserTimeMonth where month = ? and userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, LocalDate.now().getMonthValue());
            ps.setInt(2, user.getUserID());
            rs = ps.executeQuery();
            if (rs.next()) {
                userStatMonth.setUserID(rs.getInt("userID"));
            }
        } catch (Exception e) {
        } finally {
        }
    }

    public void loadMonthTag(UserDTO user, String tagName, UserStatMonthDTO userStatMonth) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select userID, tagID, timeSpent from TimeSpentInTagMonth where month = ? and tagID = ? and userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, LocalDate.now().getMonthValue());
            ps.setInt(2, new TagDAO().getTagID(tagName));
            ps.setInt(3, user.getUserID());
            rs = ps.executeQuery();
            if (rs.next()) {
                userStatMonth.setUserIDTag(rs.getInt("userID"));
                userStatMonth.getTagTimes().put(rs.getInt("tagID"), rs.getInt("timeSpent"));
            }
        } catch (Exception e) {
        } finally {
        }
    }

    public UserStatMonthDTO loadFull(UserDTO user, int monthsToSubtract, LocalDate date) throws SQLException, NamingException {
        UserStatMonthDTO result = new UserStatMonthDTO();
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from UserTimeMonth where userID = ? and month = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setInt(2, date.minusMonths(monthsToSubtract).getMonthValue());
            rs = ps.executeQuery();
            if (rs.next()) {
                result.setUserID(user.getUserID());
                result.setMonth(date.minusMonths(monthsToSubtract).getMonthValue());
                result.setTotalMinutes(rs.getInt("timeSpent"));
            }
            sql = "select * from TimeSpentInTagMonth where userID = ? and month = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setInt(2, date.minusMonths(monthsToSubtract).getMonthValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                result.getTagTimes().put(rs.getInt("tagID"), rs.getInt("timeSpent"));
            }
            for (int i = 1; i <= date.minusMonths(monthsToSubtract).lengthOfMonth(); i++) {
                UserStatDayDTO statDay = new UserStatDayDAO().loadFull(user, 0, LocalDate.of(LocalDate.now().getYear(), date.minusMonths(monthsToSubtract).getMonthValue(), i));
                if (statDay.getUserID() != 0) result.getDaysTimes().add(statDay.getTotalMinutes());
                else result.getDaysTimes().add(0);
            }
        } catch (SQLException e) {

        } finally {
        }
        return result;
    }

    public void insertMonth(UserDTO user) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into UserTimeMonth(userID, month) values (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setInt(2, LocalDate.now().getMonthValue());
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
        }
    }

    public void insertTagMonth(UserDTO user, String tagName) throws SQLException, NamingException {
        int tagID = 0;
        TagDAO tagDAO = new TagDAO();
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        try {
            tagID = tagDAO.getTagID(tagName);

            String sql = "insert into TimeSpentInTagMonth(userID, tagID, month) values (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setInt(2, tagID);
            ps.setInt(3, LocalDate.now().getMonthValue());
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
            String sql = "update UserTimeMonth "
                    + "set timeSpent = iif (timeSpent is null, ? , timeSpent + ?) "
                    + "where month = ? and userID = ?";
            ps = conn.prepareStatement(sql);
            String tagTimeSql = "update TimeSpentInTagMonth "
                    + "set timeSpent = iif (timeSpent is null, ? , timeSpent + ?) "
                    + "where month = ? and userID = ? and tagID = ?";
            tagTimeps = conn.prepareStatement(tagTimeSql);
            ps.setInt(1, minutesPassed);
            ps.setInt(2, minutesPassed);
            ps.setInt(3, LocalDate.now().getMonthValue());
            ps.setInt(4, user.getUserID());
            tagTimeps.setInt(1, minutesPassed);
            tagTimeps.setInt(2, minutesPassed);
            tagTimeps.setInt(3, LocalDate.now().getMonthValue());
            tagTimeps.setInt(4, user.getUserID());
            tagTimeps.setInt(5, tagID);
            ps.executeUpdate();
            tagTimeps.executeUpdate();
        } catch (Exception e) {

        } finally {
        }
    }
}
