/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.UserStatistic;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class UserStatWeekDTO implements Serializable {

    private int userID;
    private int userIDTag;
    private Map<Integer, Integer> tagTimes;
    private Date weekStart;
    private Date weekEnd;
    private int totalMinutes;
    private List<Integer> daysTimes;

    public UserStatWeekDTO() {
        tagTimes = new HashMap<>();
        daysTimes = new ArrayList<>();
    }
    
    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the tagTimes
     */
    public Map<Integer, Integer> getTagTimes() {
        return tagTimes;
    }

    /**
     * @param tagTimes the tagTimes to set
     */
    public void setTagTimes(Map<Integer, Integer> tagTimes) {
        this.tagTimes = tagTimes;
    }

    /**
     * @return the weekStart
     */
    public Date getWeekStart() {
        return weekStart;
    }

    /**
     * @param weekStart the weekStart to set
     */
    public void setWeekStart(Date weekStart) {
        this.weekStart = weekStart;
    }

    /**
     * @return the weekEnd
     */
    public Date getWeekEnd() {
        return weekEnd;
    }

    /**
     * @param weekEnd the weekEnd to set
     */
    public void setWeekEnd(Date weekEnd) {
        this.weekEnd = weekEnd;
    }

    /**
     * @return the totalTime
     */
    public int getTotalMinutes() {
        return totalMinutes;
    }

    /**
     * @param totalMinutes the totalTime to set
     */
    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    /**
     * @return the userIDTag
     */
    public int getUserIDTag() {
        return userIDTag;
    }

    /**
     * @param userIDTag the userIDTag to set
     */
    public void setUserIDTag(int userIDTag) {
        this.userIDTag = userIDTag;
    }

    /**
     * @return the daysTimes
     */
    public List<Integer> getDaysTimes() {
        return daysTimes;
    }

    /**
     * @param daysTimes the daysTimes to set
     */
    public void setDaysTimes(List<Integer> daysTimes) {
        this.daysTimes = daysTimes;
    }

}
