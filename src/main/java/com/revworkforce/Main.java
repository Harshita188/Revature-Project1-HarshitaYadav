package com.revworkforce;

import com.revworkforce.menus.EmployeeMenu;
import com.revworkforce.menus.ManagerMenu;
import com.revworkforce.menus.AdminMenu;
import com.revworkforce.services.AuthService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();

        System.out.println("=== Welcome to Rev Workforce ===");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Password: ");
                    String pass = sc.nextLine();

                    String role = authService.login(id, pass);
                    if (role == null) {
                        System.out.println("Invalid ID or Password!");
                    } else {
                        System.out.println("Login Successful! Role: " + role);
                        switch (role) {
                            case "Employee":
                                EmployeeMenu.showMenu(id);
                                break;
                            case "Manager":
                                ManagerMenu.showMenu(id);
                                break;
                            // case "Admin":
                            // AdminMenu.showMenu(id);
                            // break;
                            case "Admin":
                                AdminMenu.showMenu();
                                break;

                        }
                    }
                    break;

                case 2:
                    exit = true;
                    System.out.println("Exiting... Bye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}