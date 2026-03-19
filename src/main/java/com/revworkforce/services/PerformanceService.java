package com.revworkforce.services;

import com.revworkforce.utils.DBConnection;
import java.sql.*;

public class PerformanceService {

    // Create Review
    public static boolean createReview(int empId, int year, String kd, String ac, String ai, int rating) {
        String sql = "INSERT INTO performance_reviews (employee_id, year, key_deliverables, accomplishments, areas_of_improvement, self_rating) VALUES (?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, empId);
            pst.setInt(2, year);
            pst.setString(3, kd);
            pst.setString(4, ac);
            pst.setString(5, ai);
            pst.setInt(6, rating);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add Goal
    public static boolean addGoal(int empId, String desc, String deadline, String prio, String metrics) {
        String sql = "INSERT INTO performance_goals (employee_id, goal_description, deadline, priority, success_metrics) VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, empId);
            pst.setString(2, desc);
            pst.setDate(3, Date.valueOf(deadline));
            pst.setString(4, prio);
            pst.setString(5, metrics);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update Goal Progress
    public static boolean updateGoalProgress(int goalId, int progress) {
        String sql = "UPDATE performance_goals SET progress=? WHERE goal_id=?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, progress);
            pst.setInt(2, goalId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // View Feedback for Employee
    public static void viewFeedback(int empId) {
        String sql = "SELECT pr.review_id, pr.year, pf.comments, pf.rating, pf.manager_id " +
                "FROM performance_reviews pr LEFT JOIN performance_feedback pf ON pr.review_id=pf.review_id " +
                "WHERE pr.employee_id=?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, empId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Review ID: " + rs.getInt("review_id") + ", Year: " + rs.getInt("year") +
                        ", Manager ID: " + rs.getInt("manager_id") +
                        ", Rating: " + rs.getInt("rating") +
                        ", Comments: " + rs.getString("comments"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Manager: list employee reviews (direct reports)
    public static void listEmployeeReviews(int managerId) {
        String sql = "SELECT pr.review_id, u.id AS emp_id, u.name, pr.year, pr.key_deliverables FROM performance_reviews pr "
                +
                "JOIN users u ON pr.employee_id=u.id WHERE u.manager_id=?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, managerId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Review ID: " + rs.getInt("review_id") +
                        ", Employee: " + rs.getString("name") +
                        ", Year: " + rs.getInt("year") +
                        ", Key Deliverables: " + rs.getString("key_deliverables"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List Goals for Employee (needed by EmployeeMenu)
    public static void listGoals(int empId) {
        String sql = "SELECT goal_id, goal_description, deadline, priority, success_metrics, progress FROM performance_goals WHERE employee_id=?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, empId);
            ResultSet rs = pst.executeQuery();
            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("Goal ID: " + rs.getInt("goal_id") +
                        ", Description: " + rs.getString("goal_description") +
                        ", Deadline: " + rs.getDate("deadline") +
                        ", Priority: " + rs.getString("priority") +
                        ", Success Metrics: " + rs.getString("success_metrics") +
                        ", Progress: " + rs.getInt("progress") + "%");
            }

            if (!found) {
                System.out.println("No goals found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Manager: add feedback
    public static boolean addFeedback(int reviewId, int managerId, String comments, int rating) {
        String sql = "INSERT INTO performance_feedback (review_id, manager_id, comments, rating) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, reviewId);
            pst.setInt(2, managerId);
            pst.setString(3, comments);
            pst.setInt(4, rating);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Provide feedback on a review
public static boolean provideFeedback(int reviewId, int managerId, String comments, int rating) {
    String sql = "INSERT INTO performance_feedback (review_id, manager_id, comments, rating) VALUES (?,?,?,?)";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, reviewId);
        pst.setInt(2, managerId);
        pst.setString(3, comments);
        pst.setInt(4, rating);

        return pst.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
