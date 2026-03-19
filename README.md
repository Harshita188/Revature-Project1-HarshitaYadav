# 🚀 RevWorkForce (P1 - Console-Based HRM Application)

## 📌 Project Overview

**RevWorkForce** is a console-based Human Resource Management (HRM) application designed to streamline core HR operations such as employee management, leave tracking, and performance evaluation.

This application supports **role-based access control** for:

* 👩‍💼 Employees
* 👨‍💼 Managers
* 🛠️ Admins

It follows a **modular architecture**, making it scalable and easily extendable to a **microservices-based web application** in future phases.

---

## 🎯 Key Features

### 👩‍💼 Employee Functionalities

* 🔐 Login using Employee ID & Password
* ✏️ Update personal profile (phone, address, emergency contact)
* 👨‍💼 View reporting manager details

#### 📅 Leave Management

* View leave balance (CL, SL, PL)
* Apply for leave (date, type, reason)
* Track leave status (Pending / Approved / Rejected)
* Cancel pending leave requests
* View company holiday calendar
* Get notifications for leave updates

#### 📈 Performance Management

* Create self-performance review:

  * Achievements
  * Goals
  * Self-rating
* Set yearly goals with priority & deadlines
* Track goal progress
* View manager feedback

#### 🎉 Additional Features

* View birthdays & work anniversaries
* Company announcements
* Employee directory

---

### 👨‍💼 Manager Functionalities

*(Includes all Employee features + additional controls)*

#### 📅 Leave Management

* View team members (reportees)
* Approve / Reject leave requests
* View team leave calendar
* Check team leave balances

#### 📈 Performance Management

* Review employee performance submissions
* Provide feedback & ratings
* Track team goal progress
* Generate performance reports

#### 👥 Team Management

* View team hierarchy
* Access employee profiles
* View attendance summary

---

### 🛠️ Admin Functionalities

#### 👨‍💻 Employee Management

* Add / Update / View employees
* Assign managers
* Activate / Deactivate accounts
* Search employees

#### 📅 Leave Management

* Configure leave policies
* Assign leave quotas
* Adjust balances manually
* Generate leave reports
* Set holiday calendar

#### ⚙️ System Configuration

* Manage departments & roles
* Configure review cycles
* View audit logs

---

## 🔐 Authentication & Security

* Secure login system
* Password change functionality
* Session handling with auto logout
* Password reset via admin/security

---

## 🔔 Notification System

* In-app notifications stored in database
* Types of notifications:

  * Leave approval/rejection
  * Performance feedback
  * Announcements
  * Birthdays & anniversaries

---

## 🏗️ Architecture

* Layered Architecture:

  * Presentation Layer (Console UI)
  * Service Layer (Business Logic)
  * DAO Layer (Database Interaction)
  * Database Layer (MySQL)

* Role-based modular design

---

## 🛠️ Tech Stack

* ☕ Java
* 🗄️ MySQL
* 🔗 JDBC
* 🧪 JUnit
* 📜 Log4J
* 🔧 Git & GitHub Actions

---

## 📊 Database Design

* ERD includes:

  * Employees
  * Leaves
  * Performance Reviews
  * Goals
  * Notifications

---

## ✅ Definition of Done

* Fully functional console-based application
* Role-based login system
* All modules working:

  * Employee
  * Manager
  * Admin
* Clean and structured codebase
* Database integrated with CRUD operations

---

## 🔮 Future Enhancements

* 🌐 Convert to Web Application (Spring Boot + React)
* ☁️ Deploy using Docker & Cloud (AWS)
* 🔐 JWT-based authentication
* 📊 Advanced analytics dashboard

---

## 📂 Project Structure

```
revworkforce/
│── src/
│   ├── controller/
│   ├── service/
│   ├── dao/
│   ├── model/
│   └── utils/
│
│── resources/
│── test/
│── README.md
```

---

## ▶️ How to Run

1. Clone the repository
2. Configure MySQL database
3. Update DB credentials in config file
4. Compile and run the project

```bash
javac Main.java
java Main
```

---

## 👩‍💻 Author

**Harshita Yadav**

---

## 🌟 Conclusion

RevWorkForce simplifies HR operations through a structured, role-based console application. It demonstrates strong fundamentals in **Java, database management, and system design**, making it a solid foundation for scalable enterprise applications.

---
