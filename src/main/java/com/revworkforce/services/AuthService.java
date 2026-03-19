// package com.revworkforce.services;

// import java.util.HashMap;
// import java.util.Map;

// public class AuthService {
//     private Map<Integer, String> users;

//     public AuthService() {
//         users = new HashMap<>();
//         // Dummy users for testing
//         users.put(101, "Employee");
//         users.put(201, "Manager");
//         users.put(301, "Admin");
//     }

//     public String login(int id, String password) {
//         // For now, password not checked (later implement real check)
//         return users.getOrDefault(id, null);
//     }
// }
package com.revworkforce.services;

import com.revworkforce.models.Employee;

public class AuthService {

    private EmployeeService employeeService = new EmployeeService();

    // Login returns role as String, null if fail
    public String login(int id, String password) {
        Employee emp = employeeService.login(id, password);
        if (emp != null) {
            return emp.getRole();  // Employee / Manager / Admin
        }
        return null;
    }
}