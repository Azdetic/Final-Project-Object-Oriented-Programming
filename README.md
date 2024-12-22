# 🚗 Parking Management System

## 📄 Overview

This is a Java program to manage parking. It helps with car entry, car exit, checking parking details, and giving discounts to members. It also has a login system for security.

This program was created by:

- 👤 **Yudho**
- 👤 **Wira**
- 👤 **Elian**

---

## ✨ Features

### 1. 🔐 **Login**

- You must log in to use the system.
- Default login details:
  - **Username:** `admin`
  - **Password:** `admin`
- You must enter your balance after logging in.

### 2. 🚘 **Car Entry**

- Add a car to the parking lot by entering:
  - Plate number
  - Car color
  - Car brand
- The system will save the time the car enters.
- The system will not allow duplicate plate numbers.
- If the parking lot is full, you cannot add more cars.

### 3. 🏁 **Car Exit**

- Parking fees are based on the time the car was parked:
  - Free if parked for less than 5 minutes.
  - IDR 2,000 for every 5 minutes after the first 5 minutes.
- Members get a 50% discount.
- Pay with or without a member discount.
- The system removes the car after payment.

### 4. 📋 **Parking Details**

- View a list of all parked cars:
  - Plate number
  - Car color and brand
  - Entry time
  - Parking fee and member discount fee

### 5. 🧑‍🤝‍🧑 **Member Management**

- Register new members using their phone number and name.
- Check if a phone number is linked to a member.
- View all members.
- Delete members.

---

## 📂 Main Files and What They Do

### 1. `Main.java`
- Starts the program.
- Opens the login menu.

### 2. `LoginMenu.java`
- Handles user login.
- Asks for your balance after logging in.

### 3. `DetailsMenu.java`
- Main menu where you can choose:
  - Car Entry
  - Car Exit
  - Parking Details
  - Member Management

### 4. `Car.java`
- Stores car details like plate number, color, brand, and entry time.

### 5. `CarInMenu.java`
- Manages car entry:
  - Checks for duplicate or invalid input.

### 6. `CarOutMenu.java`
- Manages car exit:
  - Calculates fees.
  - Handles payments with or without member discounts.

### 7. `DetailsParking.java`
- Keeps the list of parked cars.
- Checks if the parking lot is full.
- Calculates parking fees.

### 8. `MemberDataMenu.java`
- Manages members:
  - Register new members
  - Delete members
  - Check or view member details
