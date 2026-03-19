// package com.revworkforce.menus;


// import java.util.Scanner;

// public class AdminMenu {
//     public static void showMenu(int adminId) {
//         Scanner sc = new Scanner(System.in);
//         boolean logout = false;

//         while (!logout) {
//             System.out.println("\n--- Admin Menu ---");
//             System.out.println("1. Add Employee");
//             System.out.println("2. Update Employee");
//             System.out.println("3. Configure Leave Policies");
//             System.out.println("4. View Reports");
//             System.out.println("5. Logout");
//             System.out.print("Enter choice: ");

//             int choice = sc.nextInt();
//             sc.nextLine(); // consume newline

//             switch (choice) {
//                 case 1:
//                     System.out.println("Adding new employee...");
//                     break;
//                 case 2:
//                     System.out.println("Updating employee information...");
//                     break;
//                 case 3:
//                     System.out.println("Configuring leave policies...");
//                     break;
//                 case 4:
//                     System.out.println("Viewing reports...");
//                     break;
//                 case 5:
//                     logout = true;
//                     System.out.println("Logging out...");
//                     break;
//                 default:
//                     System.out.println("Invalid choice!");
//             }
//         }
//     }
// }
// package com.revworkforce.menus;

// import com.revworkforce.services.AdminService;
// import com.revworkforce.models.Employee;
// import java.util.List;
// import java.util.Scanner;

// public class AdminMenu {

//     private static AdminService adminService = new AdminService();

//     public static void showMenu() {

//         Scanner sc = new Scanner(System.in);
//         boolean logout = false;

//         while (!logout) {

//             System.out.println("\n--- Admin Dashboard ---");
//             System.out.println("1. View All Employees");
//             System.out.println("2. Add Employee");
//             System.out.println("3. Delete Employee");
//             System.out.println("4. View All Leave Requests");
//             System.out.println("5. View Leave Balances");
//             System.out.println("6. Logout");
//             System.out.print("Enter choice: ");

//             int choice = sc.nextInt();
//             sc.nextLine();

//             switch (choice) {

//                 case 1:
//                     List<Employee> employees = adminService.getAllEmployees();
//                     for (Employee e : employees) {
//                         System.out.println(e.getId() + " | " + e.getName() + " | " + e.getEmail());
//                     }
//                     break;

//                 case 2:
//                     System.out.print("Name: ");
//                     String name = sc.nextLine();
//                     System.out.print("Email: ");
//                     String email = sc.nextLine();
//                     System.out.print("Phone: ");
//                     String phone = sc.nextLine();
//                     System.out.print("Password: ");
//                     String pass = sc.nextLine();
//                     System.out.print("Manager ID: ");
//                     int mid = sc.nextInt();
//                     sc.nextLine();

//                     boolean added = adminService.addEmployee(name, email, phone, pass, mid);
//                     System.out.println(added ? "Employee Added!" : "Failed!");
//                     break;

//                 case 3:
//                     System.out.print("Enter Employee ID to delete: ");
//                     int id = sc.nextInt();
//                     sc.nextLine();

//                     boolean deleted = adminService.deleteEmployee(id);
//                     System.out.println(deleted ? "Deleted!" : "Failed!");
//                     break;

//                 case 4:
//                     adminService.viewAllLeaveRequests();
//                     break;

//                 case 5:
//                     adminService.viewAllBalances();
//                     break;

//                 case 6:
//                     logout = true;
//                     System.out.println("Logging out...");
//                     break;

//                 default:
//                     System.out.println("Invalid choice");
//             }
//         }
//     }
// }
package com.revworkforce.menus;

import com.revworkforce.services.AdminService;
import com.revworkforce.services.HolidayService;
import com.revworkforce.services.AnnouncementService;
import com.revworkforce.models.Employee;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;

public class AdminMenu {

    private static AdminService adminService = new AdminService();
    private static HolidayService holidayService = new HolidayService();
    private static AnnouncementService announcementService = new AnnouncementService();

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);
        boolean logout = false;

        while (!logout) {

            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. View All Employees");
            System.out.println("2. Add Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View All Leave Requests");
            System.out.println("5. View Leave Balances");
            System.out.println("6. Manage Holidays");
            System.out.println("7. Manage Announcements / Birthdays");
            System.out.println("8. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: // View Employees
                    List<Employee> employees = adminService.getAllEmployees();
                    for (Employee e : employees) {
                        System.out.println(e.getId() + " | " + e.getName() + " | " + e.getEmail());
                    }
                    break;

                case 2: // Add Employee
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    System.out.print("Manager ID: ");
                    int mid = sc.nextInt();
                    sc.nextLine();

                    boolean added = adminService.addEmployee(name, email, phone, pass, mid);
                    System.out.println(added ? "Employee Added!" : "Failed!");
                    break;

                case 3: // Delete Employee
                    System.out.print("Enter Employee ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    boolean deleted = adminService.deleteEmployee(id);
                    System.out.println(deleted ? "Deleted!" : "Failed!");
                    break;

                case 4: // View Leave Requests
                    adminService.viewAllLeaveRequests();
                    break;

                case 5: // View Leave Balances
                    adminService.viewAllBalances();
                    break;

                case 6: // Manage Holidays
                    System.out.println("\n--- Holiday Management ---");
                    System.out.println("1. View Holidays");
                    System.out.println("2. Add Holiday");
                    System.out.println("3. Delete Holiday");
                    System.out.print("Enter choice: ");
                    int hChoice = sc.nextInt();
                    sc.nextLine();

                    switch (hChoice) {
                        case 1: // View
                            List<String> holidays = holidayService.getAllHolidays();
                            holidays.forEach(System.out::println);
                            break;
                        case 2: // Add
                            System.out.print("Holiday Name: ");
                            String hName = sc.nextLine();
                            System.out.print("Description: ");
                            String hDesc = sc.nextLine();
                            System.out.print("Date (YYYY-MM-DD): ");
                            Date hDate = Date.valueOf(sc.nextLine());
                            boolean hAdded = holidayService.addHoliday(hName, hDesc, hDate);
                            System.out.println(hAdded ? "Holiday added!" : "Failed!");
                            break;
                        case 3: // Delete
                            System.out.print("Holiday ID to delete: ");
                            int hId = sc.nextInt();
                            sc.nextLine();
                            boolean hDeleted = holidayService.deleteHoliday(hId);
                            System.out.println(hDeleted ? "Holiday deleted!" : "Failed!");
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                    break;

                case 7: // Manage Announcements / Birthdays
                    System.out.println("\n--- Announcements / Birthdays ---");
                    System.out.println("1. View Announcements");
                    System.out.println("2. Add General Announcement");
                    System.out.println("3. Add Birthday Announcement");
                    System.out.print("Enter choice: ");
                    int aChoice = sc.nextInt();
                    sc.nextLine();

                    switch (aChoice) {
                        case 1: // View
                            List<String> announcements = announcementService.getAllAnnouncements();
                            announcements.forEach(System.out::println);
                            break;
                        case 2: // Add general
                            System.out.print("Message: ");
                            String msg = sc.nextLine();
                            boolean annAdded = announcementService.addAnnouncement("General", null, msg);
                            System.out.println(annAdded ? "Announcement added!" : "Failed!");
                            break;
                        case 3: // Add birthday
                            System.out.print("Employee ID for Birthday: ");
                            int empId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Message: ");
                            String bmsg = sc.nextLine();
                            boolean bAdded = announcementService.addAnnouncement("Birthday", empId, bmsg);
                            System.out.println(bAdded ? "Birthday added!" : "Failed!");
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                    break;

                case 8: // Logout
                    logout = true;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
