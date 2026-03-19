// package com.revworkforce.models;
// import java.util.ArrayList;
// import java.util.List;

// public class Employee {
//     private int id;
//     private String name;
//     private String email;
//     private String phone;
//     private int managerId;

//     private int casualLeave;
//     private int sickLeave;
//     private int paidLeave;

//     private List<String> appliedLeaves;

//     public Employee(int id, String name, String email, String phone, int managerId) {
//         this.id = id;
//         this.name = name;
//         this.email = email;
//         this.phone = phone;
//         this.managerId = managerId;
//         this.casualLeave = 10;
//         this.sickLeave = 8;
//         this.paidLeave = 15;
//         this.appliedLeaves = new ArrayList<>();
//     }

//     // Getters and setters
//     public int getId() { return id; }
//     public String getName() { return name; }
//     public String getEmail() { return email; }
//     public String getPhone() { return phone; }
//     public int getManagerId() { return managerId; }

//     public void setName(String name) { this.name = name; }
//     public void setEmail(String email) { this.email = email; }
//     public void setPhone(String phone) { this.phone = phone; }

//     public int getCasualLeave() { return casualLeave; }
//     public int getSickLeave() { return sickLeave; }
//     public int getPaidLeave() { return paidLeave; }

//     public List<String> getAppliedLeaves() { return appliedLeaves; }

//     public void applyLeave(String leaveType, int days) {
//         appliedLeaves.add(leaveType + " leave for " + days + " days");
//         switch (leaveType) {
//             case "CL": casualLeave -= days; break;
//             case "SL": sickLeave -= days; break;
//             case "PL": paidLeave -= days; break;
//         }
//     }
// }
package com.revworkforce.models;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private int id;
    private String name;
    private String email;
    private String phone;
    private int managerId;
    private String role;

    private List<String> appliedLeaves = new ArrayList<>();

    public Employee(int id, String name, String email, String phone, int managerId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.managerId = managerId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public int getManagerId() { return managerId; }
    public String getRole() { return role; }
    public List<String> getAppliedLeaves() { return appliedLeaves; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setRole(String role) { this.role = role; }
}