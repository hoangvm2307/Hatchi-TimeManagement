/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Jobs;

import hatchi.Tag.TagDAO;
import hatchi.Utils.DBUtils;
import hatchi.Utils.StopWatch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author ADMIN
 */
public class UpdateDatabaseJob implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        TagDAO tagDAO = new TagDAO();
        JobDataMap jobdata = jec.getJobDetail().getJobDataMap();
        int userID = jobdata.getInt("userID");
        int hour = LocalTime.now().minusHours(1).getHour();
        StopWatch stopWatch = (StopWatch)jobdata.get("stopwatch");
//        int time = stopWatch.getTimePassed()/60;
        int time = 60;
        stopWatch.setTimePassed(0);
        String tagName = jobdata.getString("tagname");
        int tagID = tagDAO.getTagID(tagName);
        String updateMoneySQL = "update UserAccount set money = money + ? where userID = ?";
        String updateDaySQL = "update UserTimeDay "
                + "set hour" + hour + " = iif(hour" + hour + " is null, ?, hour" + hour + " + ?) "
                + "where createdDay = ? and userID = ? ";
        String updateDayTotalSQL = "update UserTimeDay "
                + "set totalTime = iif (totalTime is null, ? , totalTime + ?) "
                + "where createdDay = ? and userID = ? ";
        String updateDayTagSQL = "update TimeSpentInTagDay "
                + "set timeSpent = iif (timeSpent is null, ? , timeSpent + ?) "
                + "where dayCreated = ? and userID = ? and tagID = ?";
        String updateWeekSQL = "update UserTimeWeek "
                + "set timeSpent = iif (timeSpent is null, ? , timeSpent + ?) "
                + "where (? between weekStart and weekEnd) and userID = ?";
        String updateWeekTagSQL = "update TimeSpentInTagWeek "
                + "set timeSpent = iif (timeSpent is null, ? , timeSpent + ?) "
                + "where (? between weekStart and weekEnd) and userID = ? and tagID = ?";
        String updateMonthSQL = "update UserTimeMonth "
                + "set timeSpent = iif (timeSpent is null, ? , timeSpent + ?) "
                + "where month = ? and userID = ?";
        String updateMonthTagSQL = "update TimeSpentInTagMonth "
                + "set timeSpent = iif (timeSpent is null, ? , timeSpent + ?) "
                + "where month = ? and userID = ? and tagID = ?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement updateMoneyPS = null;
        PreparedStatement updateDayPS = null;
        PreparedStatement updateDayTotalPS = null;
        PreparedStatement updateDayTagPS = null;
        PreparedStatement updateWeekPS = null;
        PreparedStatement updateWeekTagPS = null;
        PreparedStatement updateMonthPS = null;
        PreparedStatement updateMonthTagPS = null;

        try {
            updateMoneyPS = conn.prepareStatement(updateMoneySQL);
            updateMoneyPS.setInt(1, time);
            updateMoneyPS.setInt(2, userID);
            updateDayPS = conn.prepareStatement(updateDaySQL);
            updateDayPS.setInt(1, time);
            updateDayPS.setInt(2, time);
            updateDayPS.setDate(3, Date.valueOf(LocalTime.now().getHour() == 0 ? LocalDate.now().minusDays(1) : LocalDate.now()));
            updateDayPS.setInt(4, userID);
            updateDayTotalPS = conn.prepareStatement(updateDayTotalSQL);
            updateDayTotalPS.setInt(1, time);
            updateDayTotalPS.setInt(2, time);
            updateDayTotalPS.setDate(3, Date.valueOf(LocalTime.now().getHour() == 0 ? LocalDate.now().minusDays(1) : LocalDate.now()));
            updateDayTotalPS.setInt(4, userID);
            updateDayTagPS = conn.prepareStatement(updateDayTagSQL);
            updateDayTagPS.setInt(1, time);
            updateDayTagPS.setInt(2, time);
            updateDayTagPS.setDate(3, Date.valueOf(LocalTime.now().getHour() == 0 ? LocalDate.now().minusDays(1) : LocalDate.now()));
            updateDayTagPS.setInt(4, userID);
            updateDayTagPS.setInt(5, tagID);
            updateWeekPS = conn.prepareStatement(updateWeekSQL);
            updateWeekPS.setInt(1, time);
            updateWeekPS.setInt(2, time);
            updateWeekPS.setDate(3, Date.valueOf(LocalTime.now().getHour() == 0 ? LocalDate.now().minusDays(1) : LocalDate.now()));
            updateWeekPS.setInt(4, userID);
            updateWeekTagPS = conn.prepareStatement(updateWeekTagSQL);
            updateWeekTagPS.setInt(1, time);
            updateWeekTagPS.setInt(2, time);
            updateWeekTagPS.setDate(3, Date.valueOf(LocalTime.now().getHour() == 0 ? LocalDate.now().minusDays(1) : LocalDate.now()));
            updateWeekTagPS.setInt(4, userID);
            updateWeekTagPS.setInt(5, tagID);
            updateMonthPS = conn.prepareStatement(updateMonthSQL);
            updateMonthPS.setInt(1, time);
            updateMonthPS.setInt(2, time);
            updateMonthPS.setInt(3, LocalTime.now().getHour() == 0 ? LocalDate.now().minusDays(1).getMonthValue() : LocalDate.now().getMonthValue());
            updateMonthPS.setInt(4, userID);
            updateMonthTagPS = conn.prepareStatement(updateMonthTagSQL);
            updateMonthTagPS.setInt(1, time);
            updateMonthTagPS.setInt(2, time);
            updateMonthTagPS.setInt(3, LocalTime.now().getHour() == 0 ? LocalDate.now().minusDays(1).getMonthValue() : LocalDate.now().getMonthValue());
            updateMonthTagPS.setInt(4, userID);
            updateMonthTagPS.setInt(5, tagID);
            updateMoneyPS.executeUpdate();
            updateDayPS.executeUpdate();
            updateDayTotalPS.executeUpdate();
            updateDayTagPS.executeUpdate();
            updateWeekPS.executeUpdate();
            updateWeekTagPS.executeUpdate();
            updateMonthPS.executeUpdate();
            updateMonthTagPS.executeUpdate();
        } catch (SQLException e) {
        } finally {
        }
    }

}
