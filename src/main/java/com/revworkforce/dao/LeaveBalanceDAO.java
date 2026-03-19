package com.revworkforce.dao;
import com.revworkforce.models.LeaveBalance;
import com.revworkforce.utils.DBConnection;

import java.sql.*;

public class LeaveBalanceDAO {

    // ✅ Get Leave Balance
    public LeaveBalance getBalance(int empId) {

        String sql = "SELECT * FROM leave_balance WHERE employee_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LeaveBalance lb = new LeaveBalance();
                lb.setEmployeeId(rs.getInt("employee_id"));
                lb.setCasualLeave(rs.getInt("casual_leave"));
                lb.setSickLeave(rs.getInt("sick_leave"));
                lb.setPaidLeave(rs.getInt("paid_leave"));
                return lb;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ✅ Deduct Leave
    public boolean deductLeave(int empId, String leaveType, int days) {

        String column = "";

        if (leaveType.equalsIgnoreCase("CL")) column = "casual_leave";
        else if (leaveType.equalsIgnoreCase("SL")) column = "sick_leave";
        else if (leaveType.equalsIgnoreCase("PL")) column = "paid_leave";
        else return false;

        String sql = "UPDATE leave_balance SET " + column + " = " + column + " - ? WHERE employee_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, days);
            ps.setInt(2, empId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
}
