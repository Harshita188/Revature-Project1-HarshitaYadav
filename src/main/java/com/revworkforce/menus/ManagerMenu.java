package com.revworkforce.menus;

import com.revworkforce.models.LeaveRequest;
import com.revworkforce.services.*;
import java.util.List;
import java.util.Scanner;

public class ManagerMenu {

    private static ManagerService managerService = new ManagerService();

    public static void showMenu(int managerId) {
        Scanner sc = new Scanner(System.in);
        boolean logout = false;

        while (!logout) {
            System.out.println("\n--- Manager Menu ---");
            System.out.println("1. View Leave Requests");
            System.out.println("2. Approve/Reject Leave");
            System.out.println("3. Performance Management");
            System.out.println("4. View Holiday Calendar");
             System.out.println("5. View Announcements / Birthdays");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    List<LeaveRequest> requests = managerService.getAllLeaveRequests(managerId);
                    if (requests.isEmpty()) {
                        System.out.println("No leave requests.");
                    } else {
                        for (LeaveRequest lr : requests) {
                            System.out.println("Request ID: " + lr.getRequestId() +
                                    ", EmployeeID: " + lr.getEmployeeId() +
                                    ", Type: " + lr.getLeaveType() +
                                    ", Days: " + lr.getDays() +
                                    ", Status: " + lr.getStatus());
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter Request ID to process: ");
                    int reqId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("1. Approve  2. Reject: ");
                    int action = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter comment: ");
                    String comment = sc.nextLine();

                    // Get employeeId for notification
                    LeaveRequest lr = managerService.getAllLeaveRequests(managerId).stream()
                            .filter(r -> r.getRequestId() == reqId)
                            .findFirst().orElse(null);

                    if (lr == null) {
                        System.out.println("Invalid Request ID!");
                        break;
                    }

                    boolean result;
                    if (action == 1) {
                        result = managerService.approveLeave(reqId, lr.getEmployeeId(), comment);
                        if (result)
                            System.out.println("Leave approved!");
                    } else if (action == 2) {
                        result = managerService.rejectLeave(reqId, lr.getEmployeeId(), comment);
                        if (result)
                            System.out.println("Leave rejected!");
                    } else {
                        System.out.println("Invalid action!");
                    }
                    break;

                case 3: // Performance Management
                    boolean backPerfM = false;
                    while (!backPerfM) {
                        System.out.println("\n--- Performance Menu ---");
                        System.out.println("1. View Employee Reviews");
                        System.out.println("2. Provide Feedback & Rating");
                        System.out.println("3. Back");
                        System.out.print("Enter choice: ");
                        int mChoice = sc.nextInt();
                        sc.nextLine();

                        switch (mChoice) {
                            case 1: // View Reviews
                                PerformanceService.listEmployeeReviews(managerId); // show only direct reports
                                break;

                            case 2: // Provide Feedback
                                PerformanceService.listEmployeeReviews(managerId);
                                System.out.print("Enter Review ID to provide feedback: ");
                                int reviewId = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Feedback: ");
                                String feedback = sc.nextLine();
                                System.out.print("Enter Rating (1-5): ");
                                int rating = sc.nextInt();
                                sc.nextLine();

                                boolean success = PerformanceService.provideFeedback(reviewId, managerId, feedback,
                                        rating);
                                if (success)
                                    System.out.println("Feedback submitted successfully!");
                                else
                                    System.out.println("Failed to submit feedback!");
                                break;

                            case 3:
                                backPerfM = true;
                                break;

                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;
                case 4:
                    HolidayService holidayService = new HolidayService();
                    List<String> holidays = holidayService.getAllHolidays();
                    if (holidays.isEmpty()) {
                        System.out.println("No holidays found!");
                    } else {
                        System.out.println("\n--- Company Holiday Calendar ---");
                        holidays.forEach(System.out::println);
                    }
                    break;
                case 5: // View Announcements / Birthdays
                    AnnouncementService announcementService = new AnnouncementService();
                    List<String> announcements = announcementService.getAllAnnouncements();
                    if (announcements.isEmpty()) {
                        System.out.println("No announcements at the moment.");
                    } else {
                        System.out.println("\n--- Announcements / Birthdays ---");
                        announcements.forEach(System.out::println);
                    }
                    break;
                case 6:
                    logout = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}