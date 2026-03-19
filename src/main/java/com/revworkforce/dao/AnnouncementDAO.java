package com.revworkforce.dao;

import com.revworkforce.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAO {

    // Add announcement
    public boolean addAnnouncement(String type, Integer empId, String msg) {
        String sql = "INSERT INTO announcements (type, employee_id, message) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, type);
            if (empId != null) ps.setInt(2, empId);
            else ps.setNull(2, Types.INTEGER);
            ps.setString(3, msg);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // View all announcements
    public List<String> getAllAnnouncements() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT type, employee_id, message, date_created FROM announcements ORDER BY date_created DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String type = rs.getString("type");
                String msg = rs.getString("message");
                Date date = rs.getDate("date_created");
                Integer empId = rs.getInt("employee_id");
                if (rs.wasNull()) empId = null;

                list.add(date + " | " + type + (empId != null ? " (Employee ID: " + empId + ")" : "") + " : " + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
