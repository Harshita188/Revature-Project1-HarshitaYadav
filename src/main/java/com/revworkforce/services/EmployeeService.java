// package com.revworkforce.services;

// import com.revworkforce.models.Employee;
// import java.util.HashMap;
// import java.util.Map;

// public class EmployeeService {
//     private Map<Integer, Employee> employees;

//     public EmployeeService() {
//         employees = new HashMap<>();
//         // Dummy employees
//         employees.put(101, new Employee(101, "Harshita", "harshita@email.com", "9876543210", 201));
//         employees.put(102, new Employee(102, "Ravi", "ravi@email.com", "9123456780", 201));
//     }

//     public Employee getEmployee(int id) {
//         return employees.get(id);
//     }

//     public void updateProfile(Employee emp, String name, String email, String phone) {
//         emp.setName(name);
//         emp.setEmail(email);
//         emp.setPhone(phone);
//     }
// }
package com.revworkforce.services;

import com.revworkforce.dao.LeaveRequestDAO;
import com.revworkforce.dao.NotificationDAO;
import com.revworkforce.dao.UserDAO;
import com.revworkforce.models.Employee;
import com.revworkforce.models.LeaveRequest;
import com.revworkforce.models.Notification;

import java.util.List;

public class EmployeeService {

    private UserDAO userDAO = new UserDAO();
    private LeaveRequestDAO leaveDAO = new LeaveRequestDAO();
    private NotificationDAO notificationDAO = new NotificationDAO();
    private LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAO();


    public Employee login(int id, String password) {
        return userDAO.login(id, password);
    }

    public Employee getEmployee(int id) {
        return userDAO.getById(id);
    }

    public boolean updateProfile(Employee emp, String name, String email, String phone) {
        emp.setName(name);
        emp.setEmail(email);
        emp.setPhone(phone);
        return userDAO.updateProfile(emp);
    }

    // Apply leave
    public boolean applyLeave(Employee emp, String type, int days) {
        LeaveRequest lr = new LeaveRequest(emp.getId(), type, days);
        boolean applied = leaveDAO.applyLeave(lr);

        if (applied) {
            // Notify Manager
            int managerId = emp.getManagerId();
            if (managerId > 0) {
                notificationDAO.sendNotification(new Notification(managerId,
                        "Leave request from " + emp.getName() + " (" + type + ", " + days + " days)"));
            }
        }

        return applied;
    }

    // Fetch unread notifications
    public List<Notification> getNotifications(int empId) {
        return notificationDAO.getUnreadNotifications(empId);
    }

    public boolean markNotificationRead(int notificationId) {
        return notificationDAO.markAsRead(notificationId);
    }
    public void viewMyLeaves(int empId) {
    leaveRequestDAO.getLeavesByEmployee(empId);
}

}