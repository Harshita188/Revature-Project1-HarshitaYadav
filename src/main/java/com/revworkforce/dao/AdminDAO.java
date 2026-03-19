package com.revworkforce.dao;

import com.revworkforce.models.Employee;
import com.revworkforce.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // ✅ Get all employees
   public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();

        String sql = "SELECT id, name, email, phone, manager_id, role FROM users";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                // ✅ constructor use — setters nahi
                Employee e = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("manager_id")
                );

                // role setter available hai — use kar sakte
                e.setRole(rs.getString("role"));

                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Add employee
    public boolean addEmployee(Employee e, String password) {

        String sql = "INSERT INTO users (id, name, email, phone, role, manager_id, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPhone());
            ps.setString(5, e.getRole());
            ps.setInt(6, e.getManagerId());
            ps.setString(7, password);

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    // ✅ Delete employee
    public boolean deleteEmployee(int empId) {

        String sql = "DELETE FROM users WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ Update role
    public boolean updateRole(int empId, String role) {

        String sql = "UPDATE users SET role=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, role);
            ps.setInt(2, empId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ View all leave requests (Admin global view)
    public void viewAllLeaveRequests() {

        String sql = """
                SELECT request_id, employee_id, leave_type, days, status
                FROM leave_requests
                ORDER BY request_id DESC
                """;

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- All Leave Requests ---");

            while (rs.next()) {
                System.out.println(
                        "RequestID: " + rs.getInt("request_id") +
                        " | EmpID: " + rs.getInt("employee_id") +
                        " | Type: " + rs.getString("leave_type") +
                        " | Days: " + rs.getInt("days") +
                        " | Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewAllBalances() {
    String sql = "SELECT employee_id, casual_leave, sick_leave, paid_leave FROM leave_balance ORDER BY employee_id";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        System.out.println("\n--- All Leave Balances ---");

        while (rs.next()) {
            System.out.println(
                "Employee ID: " + rs.getInt("employee_id") +
                " | Casual Leave: " + rs.getInt("casual_leave") +
                " | Sick Leave: " + rs.getInt("sick_leave") +
                " | Paid Leave: " + rs.getInt("paid_leave")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
