// package com.revworkforce.models;


// public class LeaveRequest {

//     private int employeeId;
//     private String leaveType;
//     private int days;
//     private String status; // Pending / Approved / Rejected

//     public LeaveRequest(int employeeId, String leaveType, int days) {
//         this.employeeId = employeeId;
//         this.leaveType = leaveType;
//         this.days = days;
//         this.status = "Pending";
//     }

//     public int getEmployeeId() { return employeeId; }
//     public String getLeaveType() { return leaveType; }
//     public int getDays() { return days; }
//     public String getStatus() { return status; }

//     public void approve() { this.status = "Approved"; }
//     public void reject() { this.status = "Rejected"; }
// }
package com.revworkforce.models;

public class LeaveRequest {

    private int requestId;
    private int employeeId;
    private String leaveType;
    private int days;
    private String status; // Pending / Approved / Rejected
    private String managerComment;
    private String employeeName; // optional

    public LeaveRequest(int employeeId, String leaveType, int days) {
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.days = days;
        this.status = "Pending";
    }

    // Getters
    public int getRequestId() { return requestId; }
    public int getEmployeeId() { return employeeId; }
    public String getLeaveType() { return leaveType; }
    public int getDays() { return days; }
    public String getStatus() { return status; }
    public String getManagerComment() { return managerComment; }
    public String getEmployeeName() { return employeeName; }

    // Setters
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public void setStatus(String status) { this.status = status; }
    public void setManagerComment(String managerComment) { this.managerComment = managerComment; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    // Convenience methods
    public void approve() { this.status = "Approved"; }
    public void reject() { this.status = "Rejected"; }
}