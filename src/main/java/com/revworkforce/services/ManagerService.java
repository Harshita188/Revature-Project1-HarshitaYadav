// package com.revworkforce.services;
// import com.revworkforce.dao.LeaveRequestDAO;
// import com.revworkforce.dao.NotificationDAO;
// import com.revworkforce.models.LeaveRequest;
// import com.revworkforce.models.Notification;

// import java.util.List;

// public class ManagerService {

//     private LeaveRequestDAO leaveDAO = new LeaveRequestDAO();
//     private NotificationDAO notificationDAO = new NotificationDAO();

//     // Get all leave requests for my team
//     public List<LeaveRequest> getAllLeaveRequests(int managerId) {
//         return leaveDAO.getLeaveRequestsByManager(managerId);
//     }

//     public boolean approveLeave(int requestId, int employeeId, String comment) {
//         boolean success = leaveDAO.approveLeave(requestId, comment);
//         if (success) {
//             notificationDAO.sendNotification(new Notification(employeeId, "Your leave has been APPROVED by Manager."));
//         }
//         return success;
//     }

//     public boolean rejectLeave(int requestId, int employeeId, String comment) {
//         boolean success = leaveDAO.rejectLeave(requestId, comment);
//         if (success) {
//             notificationDAO.sendNotification(new Notification(employeeId, "Your leave has been REJECTED by Manager."));
//         }
//         return success;
//     }
// }
package com.revworkforce.services;

import com.revworkforce.dao.LeaveRequestDAO;
import com.revworkforce.dao.LeaveBalanceDAO;
import com.revworkforce.dao.NotificationDAO;
import com.revworkforce.models.LeaveRequest;
import com.revworkforce.models.Notification;

import java.util.List;

public class ManagerService {

    private LeaveRequestDAO leaveDAO = new LeaveRequestDAO();
    private LeaveBalanceDAO leaveBalanceDAO = new LeaveBalanceDAO();
    private NotificationDAO notificationDAO = new NotificationDAO();

    // Get all leave requests for my team
    public List<LeaveRequest> getAllLeaveRequests(int managerId) {
        return leaveDAO.getLeaveRequestsByManager(managerId);
    }

    // ✅ Approve Leave + Deduct Balance + Send Notification
    public boolean approveLeave(int requestId, int employeeId, String comment) {

        // 1. Get leave details (type & days)
        LeaveRequest lr = leaveDAO.getById(requestId);

        // 2. Approve leave in leave_requests table
        boolean success = leaveDAO.approveLeave(requestId, comment);

        if (success && lr != null) {

            // 3. Deduct leave from leave_balance table
            leaveBalanceDAO.deductLeave(
                    lr.getEmployeeId(),
                    lr.getLeaveType(),
                    lr.getDays()
            );

            // 4. Send notification to employee
            notificationDAO.sendNotification(
                    new Notification(employeeId, "Your leave has been APPROVED by Manager.")
            );
        }

        return success;
    }

    // ❌ Reject Leave + Send Notification
    public boolean rejectLeave(int requestId, int employeeId, String comment) {

        boolean success = leaveDAO.rejectLeave(requestId, comment);

        if (success) {
            notificationDAO.sendNotification(
                    new Notification(employeeId, "Your leave has been REJECTED by Manager.")
            );
        }

        return success;
    }
}