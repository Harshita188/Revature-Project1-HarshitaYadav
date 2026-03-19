
package com.revworkforce.menus;

import com.revworkforce.models.Employee;
import com.revworkforce.models.Notification;
import com.revworkforce.services.*;
import java.util.List;
import java.util.Scanner;
import com.revworkforce.dao.LeaveBalanceDAO;
import com.revworkforce.models.LeaveBalance;

public class EmployeeMenu {

    private static EmployeeService employeeService = new EmployeeService();
    private static LeaveBalanceDAO leaveBalanceDAO = new LeaveBalanceDAO();

    public static void showMenu(int employeeId) {
        Scanner sc = new Scanner(System.in);
        boolean logout = false;

        Employee emp = employeeService.getEmployee(employeeId);
        if (emp == null) {
            System.out.println("Employee not found!");
            return;
        }

        while (!logout) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Apply for Leave");
            System.out.println("4. View Notifications");
            System.out.println("5. View Leave Balance");
            System.out.println("6. View My Leave Requests");
            System.out.println("7. Performance Management");
            System.out.println("8. View Holiday Calendar");
            System.out.println("9. View Announcements / Birthdays");
            System.out.println("10. Logout");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Profile ---");
                    System.out.println("Name: " + emp.getName());
                    System.out.println("Email: " + emp.getEmail());
                    System.out.println("Phone: " + emp.getPhone());
                    System.out.println("Manager ID: " + emp.getManagerId());
                    break;

                case 2:
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter new phone: ");
                    String phone = sc.nextLine();

                    boolean updated = employeeService.updateProfile(emp, name, email, phone);
                    if (updated)
                        System.out.println("Profile updated successfully!");
                    else
                        System.out.println("Profile update failed!");
                    break;

                case 3:
                    System.out.print("Enter leave type (CL/SL/PL): ");
                    String type = sc.nextLine();
                    System.out.print("Enter number of days: ");
                    int days = sc.nextInt();
                    sc.nextLine();

                    boolean applied = employeeService.applyLeave(emp, type, days);
                    if (applied)
                        System.out.println("Leave applied successfully and sent to Manager!");
                    else
                        System.out.println("Leave application failed!");
                    break;

                case 4:
                    List<Notification> notifications = employeeService.getNotifications(emp.getId());
                    if (notifications.isEmpty()) {
                        System.out.println("No new notifications.");
                    } else {
                        for (Notification n : notifications) {
                            System.out.println(n.getMessage());
                            employeeService.markNotificationRead(n.getId());
                        }
                    }
                    break;
                case 5: // View Leave Balance yaha changes kiye h mene
                    LeaveBalance lb = leaveBalanceDAO.getBalance(emp.getId());

                    if (lb != null) {
                        System.out.println("\n--- Your Leave Balance ---");
                        System.out.println("Casual Leave (CL): " + lb.getCasualLeave());
                        System.out.println("Sick Leave (SL): " + lb.getSickLeave());
                        System.out.println("Paid Leave (PL): " + lb.getPaidLeave());
                    } else {
                        System.out.println("Leave balance not found!");
                    }
                    break;
                case 6:
                    employeeService.viewMyLeaves(emp.getId());
                    break;

                case 7: // Performance Management
                    boolean backPerf = false;
                    while (!backPerf) {
                        System.out.println("\n--- Performance Menu ---");
                        System.out.println("1. Create Performance Review");
                        System.out.println("2. Set Goals");
                        System.out.println("3. Update Goal Progress");
                        System.out.println("4. View Feedback");
                        System.out.println("5. Back");
                        System.out.print("Enter choice: ");
                        int pChoice = sc.nextInt();
                        sc.nextLine();

                        switch (pChoice) {
                            case 1: // Create Review
                                System.out.print("Enter Year: ");
                                int year = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Key Deliverables: ");
                                String kd = sc.nextLine();
                                System.out.print("Accomplishments: ");
                                String ac = sc.nextLine();
                                System.out.print("Areas of Improvement: ");
                                String ai = sc.nextLine();
                                System.out.print("Self Rating (1-5): ");
                                int rating = sc.nextInt();
                                sc.nextLine();

                                boolean created = PerformanceService.createReview(emp.getId(), year, kd, ac, ai,
                                        rating);
                                if (created)
                                    System.out.println("Performance Review Created Successfully!");
                                else
                                    System.out.println("Error creating review!");
                                break;

                            case 2: // Set Goals
                                System.out.print("Goal Description: ");
                                String desc = sc.nextLine();
                                System.out.print("Deadline (YYYY-MM-DD): ");
                                String deadline = sc.nextLine();
                                System.out.print("Priority (High/Medium/Low): ");
                                String prio = sc.nextLine();
                                System.out.print("Success Metrics: ");
                                String metrics = sc.nextLine();

                                boolean goalSet = PerformanceService.addGoal(emp.getId(), desc, deadline, prio,
                                        metrics);
                                if (goalSet)
                                    System.out.println("Goal Set Successfully!");
                                else
                                    System.out.println("Error setting goal!");
                                break;
                            case 3: // Update Goal Progress
                                PerformanceService.listGoals(emp.getId()); // List current goals
                                System.out.print("Enter Goal ID to update progress: ");
                                int gId = sc.nextInt();
                                System.out.print("Enter Progress (0-100): ");
                                int prog = sc.nextInt();
                                sc.nextLine();

                                boolean goalUpdated = PerformanceService.updateGoalProgress(gId, prog); // rename
                                                                                                        // variable
                                if (goalUpdated)
                                    System.out.println("Goal Progress Updated!");
                                else
                                    System.out.println("Error updating progress!");
                                break;
                            case 4: // View Feedback
                                PerformanceService.viewFeedback(emp.getId());
                                break;

                            case 5:
                                backPerf = true;
                                break;

                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;

                case 8:
                    HolidayService holidayService = new HolidayService();
                    List<String> holidays = holidayService.getAllHolidays();
                    if (holidays.isEmpty()) {
                        System.out.println("No holidays found!");
                    } else {
                        System.out.println("\n--- Company Holiday Calendar ---");
                        holidays.forEach(System.out::println);
                    }
                    break;
                case 9: // View Announcements / Birthdays
                    AnnouncementService announcementService = new AnnouncementService();
                    List<String> announcements = announcementService.getAllAnnouncements();
                    if (announcements.isEmpty()) {
                        System.out.println("No announcements at the moment.");
                    } else {
                        System.out.println("\n--- Announcements / Birthdays ---");
                        announcements.forEach(System.out::println);
                    }
                    break;
                case 10:
                    logout = true;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}