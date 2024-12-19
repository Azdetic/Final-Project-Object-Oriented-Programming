# 🚗 Parking Management System

## 📄 Overview

This is a simple Java program to manage parking. It helps with car entry, car exit, checking parking details, and giving discounts to members. It also has a login system for security.

This program is the **final project** created by:

- 👤 **Yudho**
- 👤 **Wira**
- 👤 **Elian**

---

## ✨ Features

### 1. 🔐 **Login**

- Login required to use the system.
- Default login:
  - **Username:** `admin`
  - **Password:** `admin`

### 2. 🚘 **Car Entry**

- Add cars to the parking lot by entering:
  - Plate number
  - Car color
  - Car brand
- Records the time the car entered.

### 3. 🏁 **Car Exit**

- Calculate parking fees based on how long the car was parked.
- Discounts for members (50% off).
- Removes the car from the parking list after payment.

### 4. 📋 **Parking Details**

- See a list of parked cars, including:
  - Plate number
  - Car color and brand
  - Time the car entered
  - Current parking fee

### 5. 🧑‍🤝‍🧑 **Member Management**

- Add new members.
- View all members.
- Check if a person is a member.

---

## 📂 Main Files

### 1. `Main.java`

- 🚀 Starts the program.
- Opens the login screen.

### 2. `LoginMenu.java`

- 🔑 Handles login for users.
- If login is correct, opens the main menu.

### 3. `DetailsMenu.java`

- 🏠 The main menu for choosing options:
  - Car Entry
  - Car Exit
  - Parking Details
  - Member Management

### 4. `CarInMenu.java`

- 🚙 Handles adding cars to the parking lot.

### 5. `CarOutMenu.java`

- 🛑 Handles removing cars and calculating fees.

### 6. `DetailsParking.java`

- 🅿️ Keeps the list of parked cars.
- Calculates parking fees.

### 7. `MemberDataMenu.java`

- 📇 Adds and checks members.

---
