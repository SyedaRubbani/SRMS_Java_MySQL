# Student Record Management System (SRMS)

A full Java desktop application built using **Java Swing** and **MySQL**, designed to manage student academic records through a user-friendly interface with persistent database integration.

---

## Features

- Add new student records
- View all students in a table (JTable)
- Update existing student data
- Delete student entries
- Search and sort students by course, GPA, or year
- MVC architecture for modular, clean code
- Secure JDBC with parameterized queries

---

## Tech Stack

| Layer         | Technology         |
|---------------|--------------------|
| UI            | Java Swing (JFrame, JPanel, JTable, JTextField, JButton, JComboBox) |
| Logic         | Java               |
| Persistence   | JDBC + MySQL       |
| Architecture  | MVC (Model-View-Controller) |

---

## Database: `university_db`

Create the following table in your MySQL server:

```sql
CREATE DATABASE IF NOT EXISTS university_db;
USE university_db;

CREATE TABLE students (
  roll_no INT PRIMARY KEY,
  name VARCHAR(100),
  course VARCHAR(100),
  year INT,
  gpa DECIMAL(3,2)
);
