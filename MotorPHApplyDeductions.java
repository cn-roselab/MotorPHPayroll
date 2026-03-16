/*
 * ==============================================================
 * MotorPH Payroll System – Task 9 (Advanced Version)
 * ==============================================================
 *
 * Program Purpose:
 * This program computes an employee's semi-monthly gross salary,
 * applies government deductions (SSS, PhilHealth, Pag-IBIG, Income Tax),
 * and calculates the final net pay.
 *
 * Design Principles Used:
 * 1. Self-documenting variable and method names.
 * 2. Constants used instead of hard-coded values.
 * 3. Clear separation of responsibilities using reusable methods.
 * 4. Structured formatting for readability.
 * 5. Input validation to handle invalid salary values.
 *
 * Formula Overview:
 * Gross Salary = Hourly Rate × Total Hours Worked
 *
 * Net Pay = Gross Salary − (SSS + PhilHealth + Pag-IBIG + Income Tax)
 *
 * This implementation follows clean code practices
 * and demonstrates professional Java formatting standards.
 */

import java.util.Scanner;

public class MotorPHApplyDeductions {

    // ===================== CONSTANTS =====================

    // Government deduction rates defined as constants
    private static final double SSS_RATE = 0.05;
    private static final double PHILHEALTH_RATE = 0.03;
    private static final double PAGIBIG_RATE = 0.02;
    private static final double INCOME_TAX_RATE = 0.10;

    // ===================== DEDUCTION METHODS =====================

    /*
     * Computes the SSS deduction based on gross salary.
     */
    public static double computeSSSDeduction(double grossSalary) {
        return grossSalary * SSS_RATE;
    }

    /*
     * Computes the PhilHealth deduction based on gross salary.
     */
    public static double computePhilHealthDeduction(double grossSalary) {
        return grossSalary * PHILHEALTH_RATE;
    }

    /*
     * Computes the Pag-IBIG deduction based on gross salary.
     */
    public static double computePagIbigDeduction(double grossSalary) {
        return grossSalary * PAGIBIG_RATE;
    }

    /*
     * Computes the income tax deduction based on gross salary.
     */
    public static double computeIncomeTaxDeduction(double grossSalary) {
        return grossSalary * INCOME_TAX_RATE;
    }

    // ===================== NET PAY METHOD =====================

    /*
     * Computes total deductions and returns the employee's net pay.
     * This method centralizes deduction calls to avoid duplication.
     */
    public static double computeNetPay(double grossSalary) {

        double sssDeduction = computeSSSDeduction(grossSalary);
        double philHealthDeduction = computePhilHealthDeduction(grossSalary);
        double pagIbigDeduction = computePagIbigDeduction(grossSalary);
        double incomeTaxDeduction = computeIncomeTaxDeduction(grossSalary);

        double totalDeductions =
                sssDeduction +
                        philHealthDeduction +
                        pagIbigDeduction +
                        incomeTaxDeduction;

        return grossSalary - totalDeductions;
    }

    // ===================== MAIN PROGRAM =====================

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);

        System.out.println("===== MOTORPH PAYROLL SYSTEM =====");

        // Collect employee data
        System.out.print("Enter Employee Name: ");
        String employeeFullName = inputScanner.nextLine();

        System.out.print("Enter Hourly Rate: ");
        double hourlyRate = inputScanner.nextDouble();

        System.out.print("Enter Total Hours Worked: ");
        double totalHoursWorked = inputScanner.nextDouble();

        // Validate that salary inputs are positive
        if (hourlyRate <= 0 || totalHoursWorked <= 0) {
            System.out.println("Invalid input detected. Hourly rate and hours worked must be positive values.");
            inputScanner.close();
            return;
        }

        // Compute gross salary
        double grossSalary = hourlyRate * totalHoursWorked;

        // Compute individual deductions
        double sssDeduction = computeSSSDeduction(grossSalary);
        double philHealthDeduction = computePhilHealthDeduction(grossSalary);
        double pagIbigDeduction = computePagIbigDeduction(grossSalary);
        double incomeTaxDeduction = computeIncomeTaxDeduction(grossSalary);

        double totalDeductions =
                sssDeduction +
                        philHealthDeduction +
                        pagIbigDeduction +
                        incomeTaxDeduction;

        double netPay = computeNetPay(grossSalary);

        // ===================== DISPLAY SUMMARY =====================

        System.out.println("\n===== PAYROLL SUMMARY =====");
        System.out.println("Employee Name: " + employeeFullName);

        System.out.printf("Gross Salary: %.2f\n", grossSalary);

        System.out.println("\n--- Government Deductions ---");
        System.out.printf("SSS Deduction: %.2f\n", sssDeduction);
        System.out.printf("PhilHealth Deduction: %.2f\n", philHealthDeduction);
        System.out.printf("Pag-IBIG Deduction: %.2f\n", pagIbigDeduction);
        System.out.printf("Income Tax Deduction: %.2f\n", incomeTaxDeduction);

        System.out.printf("Total Deductions: %.2f\n", totalDeductions);
        System.out.printf("Net Pay: %.2f\n", netPay);

        inputScanner.close();
    }
}