/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hatchi.Tag;

import hatchi.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class TagDAO {

    public List<TagDTO> getTags() {
        ArrayList<TagDTO> list;
        list = new ArrayList<TagDTO>();

        String sql = "SELECT * FROM Tags";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TagDTO(
                        rs.getInt("tagID"),
                        rs.getString("name"),
                        rs.getString("color")));
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Tags error!" + ex.getMessage());
        }
        return null;
    }

    public TagDTO load(int id) {
        TagDTO tag = new TagDTO();

        String sql = "SELECT * FROM Tags WHERE tagID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tag.setId(rs.getInt("tagID"));
                tag.setName(rs.getString("name"));
                tag.setColor(rs.getString("color"));
            }
            return tag;

        } catch (SQLException ex) {
            System.out.println("Query Tags error!" + ex.getMessage());
        }
        return null;
    }

    /*
    Insert tag and return Id
     */
    public boolean insert(TagDTO tag) {
        String sql = "INSERT INTO Tags(name, color) "
                + " VALUES (?, ?)";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, tag.getName());
            ps.setString(2, tag.getColor());

            if (ps.executeUpdate() > 0) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Insert new Tag error!" + ex.getMessage());
        }
        return false;
    }

    /*
    Update Tag and return Id
     */
    public boolean update(TagDTO tag) {
        String sql = "UPDATE Tags SET name = ? , color = ? WHERE tagId = ?";
        Connection conn = DBUtils.getConnection();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, tag.getName());
            ps.setString(2, tag.getColor());
            ps.setLong(3, tag.getId());

            if (ps.executeUpdate() > 0) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Update tag error!" + ex.getMessage());
        } catch (Exception e) {
        }

        return false;
    }

    /*
    Delete student 
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM Tags WHERE tagID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Delete tag error!" + ex.getMessage());
        }

        return false;
    }

    public int getTagID(String name) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int tagID = 0;
        String sql = "select tagID from Tags where name = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                tagID = rs.getInt("tagID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) conn.close();
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
            }
        }
        
        return tagID;
    }
}
