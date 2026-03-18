# 💖 Digital Address Book (Java + MySQL)

A beautiful desktop application built using **Java Swing** and **MySQL**, designed to manage contacts with a stylish **pink & gold luxury UI**.

---

## ✨ Features

* ➕ Add new contacts
* 📋 View all contacts in a table
* 🔍 Search contacts by name or phone
* ✏️ Update/Edit contact details
* ❌ Delete contacts
* 🎨 Modern UI with:

  * Pink gradient background
  * Gold themed buttons
  * Card-style layout
  * Hover effects

---

## 🛠️ Technologies Used

* Java (Swing for UI)
* MySQL (Database)
* XAMPP (MySQL Server)
* JDBC (MySQL Connector J)

---

## 📁 Project Structure

```
DigitalAddressBook/
│
├── src/
│   ├── MainUI.java
│   ├── AddContactUI.java
│   ├── ViewContactsUI.java
│   ├── DBConnection.java
│
├── lib/
│   └── mysql-connector-j-9.5.0.jar
│
├── DigitalAddressBook.jar
└── manifest.txt
```

---

## ⚙️ Setup Instructions

### 1️⃣ Install XAMPP

* Download and install XAMPP
* Start:

  * Apache
  * MySQL

---

### 2️⃣ Create Database

Open phpMyAdmin and run:

```sql
CREATE DATABASE address_book;

USE address_book;

CREATE TABLE contacts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(255),
    category VARCHAR(50)
);
```

---

### 3️⃣ Configure Database Connection

Open `DBConnection.java` and ensure:

```java
String url = "jdbc:mysql://localhost:3306/address_book";
String user = "root";
String password = "";
```

---

### 4️⃣ Compile & Run (Command Line)

```bash
javac -cp ".;lib/mysql-connector-j-9.5.0.jar" -d . src/*.java
java -cp ".;lib/mysql-connector-j-9.5.0.jar" MainUI
```

---

### 5️⃣ Run using JAR

```bash
java -jar DigitalAddressBook.jar
```

---

## ⚠️ Important Notes

* MySQL (XAMPP) must be **running** before starting the application
* Without MySQL, only UI will load — database operations will not work
* Ensure MySQL Connector `.jar` is inside the `lib` folder

---

## 🎯 Future Improvements

* Add icons for buttons
* Export contacts to file
* Use SQLite for standalone version
* Add animations and transitions
* Dark mode support

---

## 👩‍💻 Author

**Puvisha Sundar**
Student Developer 💻✨

---

## 💡 Project Description

This project demonstrates a complete **CRUD-based desktop application** using Java and MySQL, with a focus on both **functionality and UI design**.

---
