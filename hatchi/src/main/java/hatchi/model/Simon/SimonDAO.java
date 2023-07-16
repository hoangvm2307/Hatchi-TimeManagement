/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.model.Simon;

import hatchi.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoangvmdeptrai
 */
public class SimonDAO {

    public List<SimonDTO> listOwnItem(String userID) {
        ArrayList<SimonDTO> listOwnItem;
        listOwnItem = new ArrayList<>();
        String sql = "SELECT UserItem.itemId , name, item_link, price from UserItem \n"
                + "                JOIN Items\n"
                + "                ON UserItem.itemId = Items.itemID\n"
                + "                WHERE userID = ?\n"
                + "				order by createAt ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listOwnItem.add(new SimonDTO(
                        rs.getString("itemId"),
                        rs.getString("name"),
                        rs.getString("item_link"),
                        rs.getString("price"),
                        "OWNED"
                ));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return listOwnItem;
    }

    public List<SimonDTO> listStoreItem(String userID) {
        ArrayList<SimonDTO> ownList;
        ownList = new ArrayList<>();

        String sql = "SELECT Items.itemID, Items.item_link, Items.name, Items.price,\n"
                + "       CASE WHEN userItem.userID IS NULL THEN 'BUY' ELSE 'OWNED' END AS ownership\n"
                + "FROM Items \n"
                + "LEFT JOIN userItem ON Items.itemID = userItem.itemID AND UserItem.userID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, userID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ownList.add(new SimonDTO(
                        rs.getString("itemId"),
                        rs.getString("name"),
                        rs.getString("item_link"),
                        rs.getString("price"),
                        rs.getString("ownership")
                ));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return ownList;
    }

    public SimonDTO loadImageById(String id) {
        String sql = "Select itemid,name, item_link, price from Items \n"
                + "Where itemID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new SimonDTO(
                        rs.getString("itemId"),
                        rs.getString("name"),
                        rs.getString("item_link"),
                        rs.getString("price"),
                        "ACTIVE");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return null;
    }

    public int getTotalPageBySearch(String searchValue) {

        String sql = "select  count(*)from Items\n"
                + "where name like ?";

        int totalPage = 0;

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalPage = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return totalPage;
    }

    public int getTotalPage() {
        String sql = "select count(*) from Items";

        int totalPage = 0;

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalPage = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return totalPage;
    }

    public List<SimonDTO> pagingOwnedItem(int index, int pageLength, String userId) {
        String sql = "SELECT Items.itemID, Items.item_link, Items.name, Items.price,\n"
                + "       CASE WHEN userItem.userID IS NULL THEN 'BUY' ELSE 'OWNED' END AS ownership\n"
                + "FROM Items \n"
                + "JOIN userItem ON Items.itemID = userItem.itemID AND UserItem.userID = ?\n"
                + "ORDER BY itemID\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        ArrayList<SimonDTO> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int itemOnePage = (index - 1) * pageLength;
            ps.setString(1, userId);
            ps.setInt(2, itemOnePage);
            ps.setInt(3, pageLength);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new SimonDTO(
                        rs.getString("itemId"),
                        rs.getString("name"),
                        rs.getString("item_link"),
                        rs.getString("price"),
                        rs.getString("ownership")));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return list;

    }

    public List<SimonDTO> pagingItem(int index, int pageLength, String userId) {
        String sql = "SELECT Items.itemID, Items.item_link, Items.name, Items.price,\n"
                + "       CASE WHEN userItem.userID IS NULL THEN 'BUY' ELSE 'OWNED' END AS ownership\n"
                + "FROM Items \n"
                + "LEFT JOIN userItem ON Items.itemID = userItem.itemID AND UserItem.userID = ?\n"
                + "ORDER BY itemID\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        ArrayList<SimonDTO> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int itemOnePage = (index - 1) * pageLength;
            ps.setString(1, userId);

            ps.setInt(2, itemOnePage);
            ps.setInt(3, pageLength);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new SimonDTO(
                        rs.getString("itemId"),
                        rs.getString("name"),
                        rs.getString("item_link"),
                        rs.getString("price"),
                        rs.getString("ownership")));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return list;

    }

    public List<SimonDTO> pagingItemBySearch(int index, int pageLength, String userId, String searchValue) {
        String sql = "SELECT Items.itemID, Items.item_link, Items.name, Items.price,\n"
                + "       CASE WHEN userItem.userID IS NULL THEN 'BUY' ELSE 'OWNED' END AS ownership\n"
                + "FROM Items \n"
                + "LEFT JOIN userItem ON Items.itemID = userItem.itemID AND UserItem.userID = ?\n"
                + "WHERE name LIKE ?\n"
                + "ORDER BY itemID\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        ArrayList<SimonDTO> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int itemOnePage = (index - 1) * pageLength;
            ps.setString(1, userId);
            ps.setString(2, "%" + searchValue + "%");
            ps.setInt(3, itemOnePage);
            ps.setInt(4, pageLength);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new SimonDTO(
                        rs.getString("itemId"),
                        rs.getString("name"),
                        rs.getString("item_link"),
                        rs.getString("price"),
                        rs.getString("ownership")));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return list;

    }

    public int loadPrice(String id) {
        String sql = "select price from Items\n"
                + "where itemId = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int price = Integer.parseInt(rs.getString("price"));
                return price;
            }

        } catch (SQLException ex) {
            System.out.println("loadPrice Error: " + ex);
        }

        return 0;
    }

    /* Load User Money*/
    public int getUserMoney(String userID) {
        String sql = "select money from UserAccount\n"
                + "where userID = ?";

        int money = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                money = Integer.parseInt(rs.getString("money"));
            }

        } catch (SQLException ex) {
            System.out.println("getUserMoney Error: " + ex);
        }

        return money;
    }
    
    public boolean isHaveSimon(String userID) {
        String sql = "Select * from UserItem\n"
                + "where userId= ?";

        boolean isHaveSimon = false;
        
        try{
        
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                if(rs.getString("userId") != null){
                    isHaveSimon = true;
                }
            }
            
            rs.close();
            ps.close();
            conn.close();
            
        }catch(SQLException ex){
            System.out.println("IsHaveSimon Error" + ex);
        }
        
        
        return isHaveSimon;
    }

    public boolean insertUserItem(String itemID, String userID, String priceAtTimeBuy, String time) {
        String sql = "insert into userItem(itemid, userID,priceAtTimeBuy, createAt)\n"
                + "values (?,?,?,?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, itemID);
            ps.setString(2, userID);
            ps.setString(3, priceAtTimeBuy);
            ps.setString(4, time);

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("inserUserItem Error: " + ex);
        }

        return true;
    }

    public boolean decreaseUserMoney(int money, String userID) {
        String sql = "update UserAccount \n"
                + "set money = ? \n"
                + "where userID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, money);
            ps.setString(2, userID);

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("decreaseUserMoney Error: " + ex);
        }

        return true;
    }

    /*
       List SimonDTO 
     */
    public List<SimonDTO> list() {
        ArrayList<SimonDTO> list = new ArrayList<SimonDTO>();

        String sql = "SELECT * FROM Items";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SimonDTO(
                        rs.getString("itemID"),
                        rs.getString("name"),
                        rs.getString("item_link"),
                        rs.getString("price"),
                        rs.getString("enable")));
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Items list error!" + ex.getMessage());
        }
        return null;
    }

    /*
       Load SimonDTO by ID
     */
    public SimonDTO loadSimon(String itemID) {
        SimonDTO simonDTO = null;

        String sql = "select * from items\n"
                + "where itemid = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, itemID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                simonDTO = new SimonDTO(
                        rs.getString("itemId"),
                        rs.getString("name"),
                        rs.getString("item_link"),
                        rs.getString("price"),
                        "null"
                );
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("loadSimon by ID error: " + ex);
        }

        return simonDTO;
    }

    /*
       Search SimonDTO by name
     */
    public List<SimonDTO> search(String name) {
        ArrayList<SimonDTO> list;
        list = new ArrayList<SimonDTO>();

        String sql = "SELECT * FROM Items WHERE name like ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new SimonDTO(
                        rs.getString("itemID"),
                        rs.getString("name"),
                        rs.getString("item_link"),
                        rs.getString("price"),
                        rs.getString("enable")));
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Items list error!" + ex.getMessage());
        }
        return null;
    }

    /*
    Insert item and return Id
     */
    public boolean insert(SimonDTO item) {
        String sql = "INSERT INTO Items (name,item_link, price)\n"
                + "values (?, ?, ?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, item.getName());
            ps.setString(2, item.getItemLink());
            ps.setString(3, item.getPrice());

            if (ps.executeUpdate() > 0) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Insert new Item error!" + ex.getMessage());
        }
        return false;
    }

    /*
    Insert student and return Id
     */
    public boolean update(SimonDTO item) {
        String sql = "UPDATE Items "
                + "SET name = ? , item_link = ?, price = ? \n"
                + "WHERE itemID = ?";
        Connection conn = DBUtils.getConnection();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, item.getName());
            ps.setString(2, item.getItemLink());
            ps.setString(3, item.getPrice());
            ps.setString(4, item.getId());

            if (ps.executeUpdate() > 0) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Update item error!" + ex.getMessage());
        } catch (Exception e) {
        }

        return false;
    }

    /*
    Delete student and return Id
     */
    public boolean delete(String id) {
        String sql = "DELETE FROM Items WHERE itemID = ?";

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
            System.out.println("Delete item error!" + ex.getMessage());
        }
        return false;
    }
}
