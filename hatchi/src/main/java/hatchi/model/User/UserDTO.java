/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hatchi.model.User;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DUNGHUYNH
 */
public class UserDTO implements Serializable {

    private String username;
    private String password;
    private int userID;
    private String email;
    private Date loginDate;
    private String role;
    private int money;
    private int time;
    private String createAt;

    public UserDTO() {

    }

    public UserDTO(String username, String password, int userID, String email,
            Date loginDate, String role, int money, int time, String createAt) {
        this.username = username;
        this.userID = userID;
        this.email = email;
        this.loginDate = loginDate;
        this.role = role;
        this.money = money;
        this.time = time;
        this.createAt = createAt;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

}
