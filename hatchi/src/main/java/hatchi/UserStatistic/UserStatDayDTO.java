/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.UserStatistic;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class UserStatDayDTO implements Serializable {

    private int userID;
    private int userIDTag;
    private Map<Integer, Integer> tagTime;
    private Date createdDate;
    private Map<Integer, Integer> hoursTime;
    private int totalMinutes;

    public UserStatDayDTO() {
        tagTime = new HashMap<>();
        hoursTime = new HashMap<>();
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
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @return the totalHours
     */
    public int getTotalMinutes() {
        return totalMinutes;
    }

    /**
     * @return the tagTime
     */
    public Map<Integer, Integer> getTagTime() {
        return tagTime;
    }

    /**
     * @param tagTime the tagTime to set
     */
    public void setTagTime(Map<Integer, Integer> tagTime) {
        this.tagTime = tagTime;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the hoursTime
     */
    public Map<Integer, Integer> getHoursTime() {
        return hoursTime;
    }

    /**
     * @param hoursTime the hoursTime to set
     */
    public void setHoursTime(Map<Integer, Integer> hoursTime) {
        this.hoursTime = hoursTime;
    }

    /**
     * @param totalMinutes the totalHours to set
     */
    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

}
