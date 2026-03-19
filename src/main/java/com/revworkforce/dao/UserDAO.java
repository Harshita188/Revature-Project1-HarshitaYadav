package com.revworkforce.dao;

import com.revworkforce.models.Employee;
import com.revworkforce.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Login: return Employee object if id+password match
    public Employee login(int id, String password) {
        String sql = "SELECT * FROM users WHERE id=? AND password=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get Employee by ID
    public Employee getById(int id) {
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update Employee profile
    public boolean updateProfile(Employee emp) {
        String sql = "UPDATE users SET name=?, email=?, phone=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, emp.getName());
            pst.setString(2, emp.getEmail());
            pst.setString(3, emp.getPhone());
            pst.setInt(4, emp.getId());

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper to map ResultSet -> Employee
    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String role = rs.getString("role");
        int managerId = rs.getInt("manager_id");

        Employee emp = new Employee(id, name, email, phone, managerId);
        emp.setRole(role);
        return emp;
    }

    // Optional: get all employees
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role='Employee'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToEmployee(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}