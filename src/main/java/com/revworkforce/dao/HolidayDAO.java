package com.revworkforce.dao;

import com.revworkforce.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HolidayDAO {

    // Add a holiday
    public boolean addHoliday(String name, String desc, Date date) {
        String sql = "INSERT INTO holidays (holiday_date, name, description) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, date);
            ps.setString(2, name);
            ps.setString(3, desc);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a holiday by ID
    public boolean deleteHoliday(int id) {
        String sql = "DELETE FROM holidays WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // View all holidays
    public List<String> getAllHolidays() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT holiday_date, name, description FROM holidays ORDER BY holiday_date";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getDate("holiday_date") + " - " + rs.getString("name") +
                         (rs.getString("description") != null ? " (" + rs.getString("description") + ")" : ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
