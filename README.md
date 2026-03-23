# MotorPH Payroll System

---

## 📌 Project Description

The **MotorPH Payroll System** is a Java-based application designed to process employee information and attendance records to compute payroll accurately.

The system reads data from CSV files and calculates employee salary based on:

* Attendance records
* Working hours
* Employee details

This system implements **semi-monthly payroll processing** and applies **government deductions using a matrix-based approach**, ensuring accurate and realistic payroll computation.

Additionally, a separate **Semi-Monthly Salary Calculator module** is included to compute payroll for a single cutoff period, demonstrating fundamental payroll computation and validation logic.

---

## 📂 Files Included

* **MotorPHPayroll.java**
  Main program that handles payroll processing and user interaction.

* **MotorPHApplyDeductions.java**
  Contains the logic for computing government deductions.

* **MotorPHSemiMonthlySalary.java**
  Standalone program for computing semi-monthly salary with validation and deduction breakdown.

* **MotorPHemployees.csv**
  Contains employee information such as employee ID, name, birthday, and salary details.

* **MotorPHemployeeattendance.csv**
  Contains attendance records used to calculate total working hours.

---

## ⚙️ Features

### 🔹 Payroll System

* Reads employee and attendance data from CSV files
* Computes total working hours
* Calculates gross salary
* Applies government deductions using a matrix-based system
* Supports semi-monthly payroll (Cutoff 1 and Cutoff 2)
* Displays payroll summary for all employees in a structured format

### 🔹 Semi-Monthly Salary Calculator

* Accepts employee name, hourly rate, and total hours worked
* Validates user input
* Computes gross salary
* Applies government deductions (SSS, PhilHealth, Pag-IBIG, Tax)
* Displays net salary with breakdown

---

## 🛠️ Tools & Technologies Used

* **Java**
* **IntelliJ IDEA**
* **GitHub**

---

## ▶️ How to Run the Program

### 🔹 Payroll System

1. Open the project in **IntelliJ IDEA**
2. Ensure all CSV files are in the correct directory
3. Run `MotorPHPayroll.java`
4. Enter login credentials:

   * Username: `payroll_staff`
   * Password: `12345`

---

### 🔹 Semi-Monthly Calculator

1. Run `MotorPHSemiMonthlySalary.java`
2. Enter:

   * Employee Name
   * Hourly Rate
   * Total Hours Worked
3. View computed salary and deductions

---

## 📊 Sample Output

### 🔹 Semi-Monthly Calculator

```
===== SEMI-MONTHLY PAYROLL REPORT =====
Employee Name: Manuel Garcia
Gross Salary: 37800.00

--- Deductions ---
SSS: 1125.00
PhilHealth: 600.00
Pag-IBIG: 100.00
Tax: 3500.00

Net Salary: 32575.00
```

---

### 🔹 Payroll System (Summary)

```
CUTOFF 1 (1–15)
Emp #   Name               Hours   Gross Pay   Net Pay
...

CUTOFF 2 (16–END)
Emp #   Name               Hours   Gross Pay   Deductions   Net Pay
...
```

---

## 🚀 Key Improvements

* Replaced static arrays with dynamic data structures
* Enhanced user interface for better readability
* Implemented semi-monthly payroll processing
* Added detailed payslip generation
* Integrated deduction matrix for government contributions
* Included standalone semi-monthly calculator module
* Improved code modularity and structure

---

## 👩‍💻 Author

**Rosemarie Labiste**
