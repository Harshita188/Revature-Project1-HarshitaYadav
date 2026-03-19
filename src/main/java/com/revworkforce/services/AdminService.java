package com.revworkforce.services;

import com.revworkforce.dao.AdminDAO;
import com.revworkforce.models.Employee;
import java.util.List;

public class AdminService {

    private AdminDAO adminDAO = new AdminDAO();

    public List<Employee> getAllEmployees() {
        return adminDAO.getAllEmployees();
    }

    // public boolean addEmployee(String name, String email, String phone, String pass, int managerId) {
    //     return adminDAO.addEmployee(name, email, phone, pass, managerId);
    // }
    public boolean addEmployee(String name, String email, String phone, String pass, int managerId) {

    // id 0 pass kar do — DB auto increment karega
    Employee emp = new Employee(0, name, email, phone, managerId);

    return adminDAO.addEmployee(emp, pass);
}

    public boolean deleteEmployee(int id) {
        return adminDAO.deleteEmployee(id);
    }

    public void viewAllLeaveRequests() {
        adminDAO.viewAllLeaveRequests();
    }

    public void viewAllBalances() {
        adminDAO.viewAllBalances();
    }
}
