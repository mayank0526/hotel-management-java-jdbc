# 🏨 Hotel Reservation System (Java + MySQL)

A simple console-based Hotel Reservation System built using **Java**, **JDBC**, and **MySQL**. The project allows users to manage hotel room bookings via terminal input, making it a great beginner-friendly project to understand Java-Database integration.

---

## 🚀 Features

- 📌 Add New Reservations
- 🔍 View All Reservations
- 🆔 Get Room Number by Reservation ID and Guest Name
- ✏️ Update Existing Reservations
- ❌ Delete Reservations
- ✅ Check if Reservation Exists
- 📦 JDBC Integration with MySQL

---

## 🛠️ Tech Stack

- Java (JDK 17+)
- JDBC
- MySQL (Localhost)
- Git (for version control)

---

## 🧾 Database Schema (Table: `reservation`)

```sql
CREATE TABLE reservation (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(100),
    room_number INT,
    contact_number VARCHAR(15),
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

<img width="263" height="242" alt="image" src="https://github.com/user-attachments/assets/5b3718a0-fa0d-42cf-8bde-fef62ed59430" />

