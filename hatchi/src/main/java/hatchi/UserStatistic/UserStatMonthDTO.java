/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.UserStatistic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class UserStatMonthDTO implements Serializable {
    private int userID;
    private int userIDTag;
    private Map<Integer, Integer> tagTimes;
    private int month;
    private int totalMinutes;
    private List<Integer> daysTimes;

    public UserStatMonthDTO() {
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
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
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
