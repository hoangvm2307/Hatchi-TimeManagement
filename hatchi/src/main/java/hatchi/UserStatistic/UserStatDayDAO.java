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
import java.time.LocalTime;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class UserStatDayDAO {

    public void loadDay(UserDTO user, UserStatDayDTO userStatDay) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select userID, createdDay from UserTimeDay where createdDay = ? and userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, user.getUserID());
            rs = ps.executeQuery();
            if (rs.next()) {
                userStatDay.setUserID(rs.getInt("userID"));
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
    
    public void loadDayTag(UserDTO user, String tagName, UserStatDayDTO userStatDay) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select userID, tagID, timeSpent from TimeSpentInTagDay where dayCreated = ? and tagID = ? and userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, new TagDAO().getTagID(tagName));
            ps.setInt(3, user.getUserID());
            rs = ps.executeQuery();
            if (rs.next()) {
                userStatDay.setUserIDTag(rs.getInt("userID"));
                userStatDay.getTagTime().put(rs.getInt("tagID"), rs.getInt("timeSpent"));
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
    
    public UserStatDayDTO loadFull (UserDTO user, int dayToSubtract, LocalDate date) throws SQLException, NamingException {
        UserStatDayDTO result = new UserStatDayDTO();
        Connection conn = DBUtils.getConnection();
        try {
            String sql = "select * from UserTimeDay where userID = ? and createdDay = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setDate(2, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                result.setUserID(user.getUserID());
                result.setCreatedDate(Date.valueOf(date.minusDays(dayToSubtract)));
                for (int i = 0; i < 24; i++) {
                    result.getHoursTime().put(i, rs.getInt("hour" + i));
                }
                result.setTotalMinutes(rs.getInt("totalTime"));
            }
            sql = "select * from TimeSpentInTagDay where userID = ? and dayCreated = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setDate(2, Date.valueOf(date));
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("tagID") != 0) result.getTagTime().put(rs.getInt("tagID"), rs.getInt("timeSpent"));
            }
        } catch (SQLException e) {
            
        } finally {
        }
        return result;
    }

    public void insertDay(UserDTO user) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into UserTimeDay(userID, createdDay, totalTime) values (?, ?, 0)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            for (int i = 0; i < 24; i++) {
                sql = "update UserTimeDay set hour" + i + "= coalesce(null, 0) where userID = ? and createdDay = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, user.getUserID());
                ps.setDate(2, Date.valueOf(LocalDate.now()));
                ps.executeUpdate();
            }
        } catch (Exception e) {

        } finally {
        }
    }

    public void insertTagDay(UserDTO user, String tagName) throws SQLException, NamingException {
        int tagID = 0;
        TagDAO tagDAO = new TagDAO();
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        try {
            tagID = tagDAO.getTagID(tagName);

            String sql = "insert into TimeSpentInTagDay(userID, tagID, dayCreated) values (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setInt(2, tagID);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
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
        TagDAO tagDAO = new TagDAO();
        try {
            int minutesPassed = stopwatch.getTimePassed() / 60;
            int hour = LocalTime.now().getHour();
            tagID = tagDAO.getTagID(tagName);
            String sql = "update UserTimeDay "
                    + "set hour" + hour + " = iif(hour" + hour + " is null, ?, hour" + hour + " + ?) "
                    + "where createdDay = ? and userID = ?";
            ps = conn.prepareStatement(sql);
            String tagTimeSql = "update TimeSpentInTagDay "
                    + "set timeSpent = iif(timeSpent is null, ?, timeSpent + ?) "
                    + "where dayCreated = ? and userID = ? and tagID = ?";
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
