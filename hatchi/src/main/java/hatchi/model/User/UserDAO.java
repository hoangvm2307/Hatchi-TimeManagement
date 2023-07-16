/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.model.User;

import hatchi.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;

/**
 *
 * @author DUNGHUYNH
 */
public class UserDAO {

    public UserDTO login(String user, String password) {

        String sql = "select username, userID, email, role, money, time, createAt from UserAccount "
                + " where username = ? and password = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                UserDTO userDTO = new UserDTO();

                userDTO.setUsername(rs.getString("username"));
                userDTO.setUserID(rs.getInt("userID"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setRole(rs.getString("role"));
                userDTO.setMoney(Integer.parseInt(rs.getString("money")));
                userDTO.setTime(Integer.parseInt(rs.getString("time")));
                userDTO.setCreateAt(rs.getString("createAt"));
                userDTO.setLoginDate(new Date());
                conn.close();
                System.out.println("GET SUCCESS");

                rs.close();
                ps.close();
                conn.close();
                return userDTO;

            }
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }

        return null;
    }


    public UserDTO getLastUser() {
        String sql = "SELECT * FROM UserAccount ORDER BY userID DESC";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                UserDTO userDTO = new UserDTO();

                userDTO.setUsername(rs.getString("username"));
                userDTO.setUserID(rs.getInt("userID"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setRole(rs.getString("role"));
                userDTO.setPassword(rs.getString("password"));
                userDTO.setMoney(Integer.parseInt(rs.getString("money")));
                userDTO.setTime(Integer.parseInt(rs.getString("time")));
                userDTO.setCreateAt(rs.getString("createAt"));
                userDTO.setLoginDate(new Date());
                conn.close();
                System.out.println("GET LAST USER SUCCESS");

                rs.close();
                ps.close();
                conn.close();
                return userDTO;

            }
        } catch (SQLException ex) {
            System.out.println("GET USER BY ID FAILED" + ex.getMessage());
        }

        return null;

    }

    public UserDTO getUser(String id) {

        String sql = "SELECT * FROM UserAccount where userID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                UserDTO userDTO = new UserDTO();

                userDTO.setUsername(rs.getString("username"));
                userDTO.setUserID(rs.getInt("userID"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setRole(rs.getString("role"));
                userDTO.setPassword(rs.getString("password"));
                userDTO.setMoney(Integer.parseInt(rs.getString("money")));
                userDTO.setTime(Integer.parseInt(rs.getString("time")));
                userDTO.setCreateAt(rs.getString("createAt"));
                userDTO.setLoginDate(new Date());
                conn.close();
                System.out.println("GET USER BY ID SUCCESS");

                rs.close();
                ps.close();
                conn.close();
                return userDTO;

            }
        } catch (SQLException ex) {
            System.out.println("GET USER BY ID FAILED" + ex.getMessage());
        }

        return null;
    }

    public int CountUsers() {
        String sql = "select count(*) as CountUser from UserAccount";
        int page = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                page = rs.getInt("CountUser");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Count User Error: " + ex);
        }

        return page;

    }

    public ArrayList<UserDTO> pagingUserAccount(int index, int pageLength, String searchValue) {
        String sql = "select * from UserAccount\n"
                + "where username like ?\n"
                + "order by UserID\n"
                + "offset ? rows fetch next ? rows only";

        ArrayList<UserDTO> users = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int itemOnePage = (index - 1) * pageLength;

            ps.setString(1, "%" + searchValue + "%");
            ps.setInt(2, itemOnePage);
            ps.setInt(3, pageLength);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserDTO userDTO = new UserDTO();

                userDTO.setUserID(rs.getInt("userID"));
                userDTO.setUsername(rs.getString("username"));
                userDTO.setPassword(rs.getString("password"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setRole(rs.getString("role"));
                userDTO.setMoney(Integer.parseInt(rs.getString("money")));
                userDTO.setTime(Integer.parseInt(rs.getString("time")));
                userDTO.setCreateAt(rs.getString("createAt"));

                users.add(userDTO);
            }

            rs.close();
            ps.close();
            conn.close();

            return users;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return null;
    }

    public int PagingCountUserAccounts(String username) {
        String sql = "select count(*) as CountUser from UserAccount\n"
                + "where username like ?";

        int page = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + username + "%");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                page = rs.getInt("CountUser");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("PagingCount Error: " + ex);
        }

        return page;
    }

    public ArrayList<UserDTO> getAllUsers() {
        ArrayList<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM UserAccount";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserDTO userDTO = new UserDTO();

                userDTO.setUserID(rs.getInt("userID"));
                userDTO.setUsername(rs.getString("username"));
                userDTO.setPassword(rs.getString("password"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setRole(rs.getString("role"));
                userDTO.setMoney(Integer.parseInt(rs.getString("money")));
                userDTO.setTime(Integer.parseInt(rs.getString("time")));
                userDTO.setCreateAt(rs.getString("createAt"));

                users.add(userDTO);
            }
            rs.close();
            ps.close();
            conn.close();
            System.out.println("GET ALL USERS SUCCESS");
            return users;
        } catch (Exception e) {
            System.out.println("GET ALL USERS FAILED " + e);
        }
        return null;
    }

    public void insert(UserDTO user) {
        String sql = "INSERT INTO UserAccount(username, password, email, role, money, time, createAt) "
                + " VALUES (?,?,?,?,?,?,?)";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole());
            ps.setString(5, "" + user.getMoney());
            ps.setString(6, "" + user.getTime());
            ps.setString(7, user.getCreateAt());
            ps.executeUpdate();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Insert User error!" + ex.getMessage());
        }
    }

    public boolean update(UserDTO user, String id) {
        String sql = "UPDATE UserAccount SET username = ? , password= ?, email = ?,money=?,time=? WHERE userID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getMoney() + "");
            ps.setString(5, user.getTime() + "");
            ps.setString(6, id);

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Update Student error!" + ex.getMessage());
        }
        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM UserAccount WHERE userID = ?";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Delete Student error!" + ex.getMessage());
        }

        return false;
    }

    public boolean usernameNotExist(String username) {
        String sql = "Select * from UserAccount where username = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {

        }
        return true;
    }

    public boolean emailNotExist(String email) {
        String sql = "Select * from UserAccount where email = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {

        }
        return true;
    }

    public void updateMoney(int money, int userID) throws SQLException, NamingException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "update UserAccount set money = money + ? where userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, money);
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

    public int getMoney(int userID) throws SQLException, NamingException {
        int money = 0;
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select money from UserAccount where userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                money = rs.getInt("money");
            }
        } catch (Exception e) {

        } finally {
        }
        return money;
    }

    public ArrayList<UserDTO> search(String name) {
        ArrayList<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM UserAccount WHERE username like ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserDTO userDTO = new UserDTO();

                userDTO.setUserID(rs.getInt("userID"));
                userDTO.setUsername(rs.getString("username"));
                userDTO.setPassword(rs.getString("password"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setRole(rs.getString("role"));
                userDTO.setMoney(Integer.parseInt(rs.getString("money")));
                userDTO.setTime(Integer.parseInt(rs.getString("time")));
                userDTO.setCreateAt(rs.getString("createAt"));

                users.add(userDTO);
            }
            rs.close();
            ps.close();
            conn.close();
            System.out.println("GET ALL USERS SEARCH SUCCESS");
            return users;
        } catch (Exception e) {
            System.out.println("GET ALL USERS SEARCH FAILED " + e);
        }
        return null;
    }
}
