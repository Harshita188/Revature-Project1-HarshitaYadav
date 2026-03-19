package com.revworkforce.dao;
import com.revworkforce.models.LeaveRequest;
import com.revworkforce.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO {

    // Insert new leave request
    public boolean applyLeave(LeaveRequest lr) {
        String sql = "INSERT INTO leave_requests (employee_id, leave_type, days, status) VALUES (?, ?, ?, 'Pending')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, lr.getEmployeeId());
            pst.setString(2, lr.getLeaveType());
            pst.setInt(3, lr.getDays());

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all leave requests for a manager's team
    public List<LeaveRequest> getLeaveRequestsByManager(int managerId) {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT lr.*, u.name as employee_name FROM leave_requests lr " +
                     "JOIN users u ON lr.employee_id = u.id " +
                     "WHERE u.manager_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, managerId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                LeaveRequest lr = new LeaveRequest(
                        rs.getInt("employee_id"),
                        rs.getString("leave_type"),
                        rs.getInt("days")
                );
                lr.setRequestId(rs.getInt("request_id"));
                lr.setStatus(rs.getString("status"));
                lr.setManagerComment(rs.getString("manager_comment"));
                lr.setEmployeeName(rs.getString("employee_name")); // optional
                list.add(lr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Approve leave
    public boolean approveLeave(int requestId, String comment) {
        return updateLeaveStatus(requestId, "Approved", comment);
    }

    // Reject leave
    public boolean rejectLeave(int requestId, String comment) {
        return updateLeaveStatus(requestId, "Rejected", comment);
    }

    private boolean updateLeaveStatus(int requestId, String status, String comment) {
        String sql = "UPDATE leave_requests SET status=?, manager_comment=? WHERE request_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, status);
            pst.setString(2, comment);
            pst.setInt(3, requestId);

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Inside LeaveRequestDAO.java
public LeaveRequest getById(int requestId) {

    LeaveRequest lr = null;

    String sql = "SELECT * FROM leave_requests WHERE request_id=?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, requestId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            lr = new LeaveRequest(
                    rs.getInt("employee_id"),
                    rs.getString("leave_type"),
                    rs.getInt("days")
            );
            lr.setRequestId(rs.getInt("request_id"));
            lr.setStatus(rs.getString("status"));
            lr.setManagerComment(rs.getString("manager_comment"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lr;
}
public void getLeavesByEmployee(int empId) {

    String sql = "SELECT request_id, leave_type, days, status, manager_comment " +
                 "FROM leave_requests WHERE employee_id = ? ORDER BY request_id DESC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, empId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Your Leave Requests ---");

        boolean found = false;

        while (rs.next()) {
            found = true;
            System.out.println("Request ID: " + rs.getInt("request_id"));
            System.out.println("Type: " + rs.getString("leave_type"));
            System.out.println("Days: " + rs.getInt("days"));
            System.out.println("Status: " + rs.getString("status"));
            System.out.println("Manager Comment: " + rs.getString("manager_comment"));
            System.out.println("----------------------------");
        }

        if (!found) {
            System.out.println("No leave requests found.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
