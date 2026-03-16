/*
 * ==========================================================
 * Task 8 – Semi-Monthly Salary Computation
 * ==========================================================
 *
 * Program Description:
 * This program computes an employee’s semi-monthly salary
 * based on the total hours worked during the cutoff period
 * and the employee’s hourly rate.
 *
 * The program demonstrates:
 *  - Proper variable declarations and data types
 *  - Arithmetic operations (multiplication)
 *  - Conditional statements for validation
 *  - Clear and labeled output
 *  - Inline comments explaining logic
 *
 * Formula Used:
 *      Semi-Monthly Salary = Total Hours Worked × Hourly Rate
 *
 * Scenario:
 * MotorPH pays employees twice a month (15th and 30th).
 * This program computes the salary for one cutoff period.
 *
 */

import java.util.Scanner;

public class MotorPHSemiMonthlySalary {

    public static void main(String[] args) {

        // ===================== VARIABLE DECLARATION =====================

        Scanner scanner = new Scanner(System.in);

        String employeeName;       // Stores employee name
        double hourlyRate;         // Stores hourly pay
        double totalHoursWorked;   // Stores total hours worked in cutoff
        double semiMonthlySalary;  // Stores computed salary

        // ===================== USER INPUT =====================

        System.out.println("===== MOTORPH SEMI-MONTHLY SALARY SYSTEM =====");

        System.out.print("Enter Employee Name: ");
        employeeName = scanner.nextLine();

        System.out.print("Enter Hourly Rate: ");
        hourlyRate = scanner.nextDouble();

        System.out.print("Enter Total Hours Worked for Cutoff: ");
        totalHoursWorked = scanner.nextDouble();

        // ===================== VALIDATION USING CONDITIONAL =====================

        /*
         * Validate input values.
         * Salary cannot be computed if:
         *  - Hourly rate is zero or negative
         *  - Total hours worked is zero or negative
         */

        if (hourlyRate <= 0 || totalHoursWorked <= 0) {

            System.out.println("\nInvalid input detected.");
            System.out.println("Hourly rate and total hours must be greater than zero.");

        } else {

            // ===================== SALARY COMPUTATION =====================

            semiMonthlySalary = totalHoursWorked * hourlyRate;

            // ===================== DISPLAY RESULT =====================

            System.out.println("\n===== SEMI-MONTHLY SALARY REPORT =====");
            System.out.println("Employee Name: " + employeeName);
            System.out.printf("Hourly Rate: %.2f\n", hourlyRate);
            System.out.printf("Total Hours Worked: %.2f\n", totalHoursWorked);
            System.out.printf("Semi-Monthly Salary: %.2f\n", semiMonthlySalary);

            System.out.println("\nComputation verified successfully!");
        }

        scanner.close();
    }
}
