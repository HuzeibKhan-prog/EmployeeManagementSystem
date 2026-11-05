# Employee Management System (EMS)

A Java-based web application developed to manage employee information efficiently. The system allows administrators to securely log in, add new employees, view employee records, update employee details, delete employees, search employees, and manage departments.

## 🚀 Features

- **Admin Login:** Secure admin login to access the system.
- **Dashboard:** Provides access to employee management operations.
- **Add Employee:** Add new employee details such as name, email, phone, designation, department, and salary.
- **View Employees:** Display all employee records in a structured table.
- **Edit Employee:** Retrieve and modify existing employee information.
- **Update Employee:** Save updated employee details in the database.
- **Delete Employee:** Remove employee records from the system.
- **Search Employee:** Search employees by their details.
- **Department Management:** Manage employee department information.
- **Logout:** End the admin session securely.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| JSP | Dynamic web pages |
| Servlets | Request and response handling |
| JDBC | Database connectivity |
| MySQL | Database management |
| HTML | Web page structure |
| CSS | Web page styling |
| Apache Tomcat | Web application server |
| NetBeans IDE | Development environment |

## 📂 Project Structure

```text
EmployeeManagementSystem
│
├── Web Pages
│   ├── index.html
│   ├── login.jsp
│   ├── dashboard.jsp
│   ├── add-employee.jsp
│   ├── employee-list.jsp
│   ├── edit-employee.jsp
│   └── style.css
│
├── Source Packages
│   ├── AdminLoginServlet.java
│   ├── LogoutServlet.java
│   ├── AddEmployeeServlet.java
│   ├── EditEmployeeServlet.java
│   ├── UpdateEmployeeServlet.java
│   ├── DeleteEmployeeServlet.java
│   ├── ViewEmployeeServlet.java
│   ├── SearchEmployeeServlet.java
│   ├── DepartmentServlet.java
│   │
│   └── db
│       └── DBConnection.java
│
└── Libraries
    └── MySQL Connector/J
```

## 🗄️ Database Configuration

The project uses MySQL as its database.

1. Open MySQL 
2. Create a database named `ems_db`.
3. Create the required employee and department tables.
4. Configure your database username and password in `DBConnection.java`.
5. Add MySQL Connector/J to the project libraries.

Example database creation:

```sql
CREATE DATABASE ems_db;
USE ems_db;
```

```sql
CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);
```

```sql
INSERT INTO admin (username, password) VALUES ('admin', 'admin123');
```

```sql
CREATE TABLE department (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(50) NOT NULL
);
```

```sql
INSERT INTO department (dept_name) VALUES ('Human Resources'), ('Engineering'), ('Sales'), ('Finance');
```

```sql
CREATE TABLE employees (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    designation VARCHAR(50),
    salary DOUBLE NOT NULL,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);
```
Entry Page

<img width="541" height="249" alt="image" src="https://github.com/user-attachments/assets/a5f214fb-0488-465b-9851-fe93c57c6cb9" />

Admin Login

<img width="373" height="282" alt="image" src="https://github.com/user-attachments/assets/fc283c95-7739-4804-9602-29a6722514a9" />

Dashboard

<img width="492" height="378" alt="image" src="https://github.com/user-attachments/assets/b387b6c9-16f5-4e9c-819b-371538629c56" />

Adding Employee

<img width="391" height="441" alt="image" src="https://github.com/user-attachments/assets/d70e9432-d14a-4bb1-885f-40ffa0cb1998" />

View All Employee

<img width="913" height="281" alt="image" src="https://github.com/user-attachments/assets/50473333-6abe-4599-95bd-d2764f321060" />

Department & Salary

<img width="906" height="309" alt="image" src="https://github.com/user-attachments/assets/51c71e76-9565-4c80-b477-5958db8d2548" />

## ⚙️ How to Run the Project

1. Clone or download this repository.
2. Open NetBeans IDE.
3. Import the project into NetBeans.
4. Install and configure Apache Tomcat.
5. Add MySQL Connector/J to the project libraries.
6. Create the MySQL database and required tables.
7. Update the database connection details in `DBConnection.java`.
8. Run the project on Apache Tomcat.
9. Open the application in your browser.

## 🎯 Learning Outcomes

- Understanding Java web application development.
- Working with JSP and Servlets.
- Implementing CRUD (Create, Read, Update, Delete) operations.
- Connecting Java applications with MySQL using JDBC.
- Understanding session management and login authentication.
- Handling employee and department records.
- Developing dynamic web pages using HTML and CSS.

## 📌 Future Enhancements

- Role-based access control.
- Improved dashboard design.
- Employee profile management.
- Advanced employee search and filtering.
- Improved application security.
- Salary and attendance management.
