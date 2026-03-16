import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.NumberFormat;
import java.util.*;

public class MotorPHPayroll {

    static final String EMPLOYEE_FILE = "MotorPHemployees.csv";
    static final String ATTENDANCE_FILE = "MotorPHemployeeattendance.csv";

    static String[] empId = new String[200];
    static String[] fullName = new String[200];
    static String[] birthday = new String[200];
    static double[] hourlyRate = new double[200];

    static int employeeCount = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        if (!login(sc)) {
            System.out.println("Incorrect username and/or password.");
            return;
        }

        loadEmployees();

        System.out.print("Enter username again to determine role: ");
        String role = sc.next();

        if (role.equals("employee")) {
            employeeMenu(sc);
        } else if (role.equals("payroll_staff")) {
            payrollMenu(sc);
        }

        sc.close();
    }

    // LOGIN
    public static boolean login(Scanner sc) {

        System.out.print("Enter username: ");
        String username = sc.next();

        System.out.print("Enter password: ");
        String password = sc.next();

        return (username.equals("employee") || username.equals("payroll_staff"))
                && password.equals("12345");
    }

    // LOAD EMPLOYEES
    public static void loadEmployees() {

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader(EMPLOYEE_FILE));

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                empId[employeeCount] = data[0];
                fullName[employeeCount] = data[2] + " " + data[1];
                birthday[employeeCount] = data[3];
                hourlyRate[employeeCount] =
                        Double.parseDouble(data[data.length - 1]);

                employeeCount++;
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error loading employee file.");
        }
    }

    // EMPLOYEE VIEW
    public static void employeeMenu(Scanner sc) {

        System.out.print("Enter employee number: ");
        String id = sc.next();

        int index = findEmployee(id);

        if (index == -1) {
            System.out.println("Employee number does not exist.");
        } else {

            System.out.println("Employee #: " + empId[index]);
            System.out.println("Name: " + fullName[index]);
            System.out.println("Birthday: " + birthday[index]);
            System.out.printf("Hourly Rate: ₱%.2f%n", hourlyRate[index]);
        }
    }

    // PAYROLL MENU
    public static void payrollMenu(Scanner sc) {

        System.out.println("1. One employee");
        System.out.println("2. All employees");
        int option = sc.nextInt();

        System.out.print("Enter month (6-12): ");
        int month = sc.nextInt();

        System.out.println("\n===============================");
        System.out.println("PAY PERIOD: " + Month.of(month));
        System.out.println("===============================");

        if (option == 1) {

            System.out.print("Enter employee number: ");
            String id = sc.next();

            int index = findEmployee(id);

            if (index == -1) {
                System.out.println("Employee not found.");
            } else {
                processEmployeeDetailed(index, month);
            }

        } else {

            System.out.println("\n----- CUTOFF 1 (1-15) -----");
            printHeader();

            for (int i = 0; i < employeeCount; i++) {
                processSummary(i, month, 1);
            }

            System.out.println("\n----- CUTOFF 2 (16-30/31) -----");
            printHeader();

            for (int i = 0; i < employeeCount; i++) {
                processSummary(i, month, 2);
            }
        }
    }

    // FIND EMPLOYEE
    public static int findEmployee(String id) {

        for (int i = 0; i < employeeCount; i++) {

            if (empId[i].equals(id))
                return i;
        }

        return -1;
    }

    // ONE EMPLOYEE DETAILED
    public static void processEmployeeDetailed(int index, int month) {

        NumberFormat php =
                NumberFormat.getCurrencyInstance(new Locale("en", "PH"));

        System.out.println("\nEmployee #: " + empId[index]);
        System.out.println("Name: " + fullName[index]);
        System.out.println("Birthday: " + birthday[index]);
        System.out.println("Hourly Rate: " + php.format(hourlyRate[index]));

        // FIRST CUTOFF
        double hours1 =
                computeHours(empId[index], month, 1, 15);

        double gross1 = hours1 * hourlyRate[index];

        System.out.println("\n--- Cutoff 1 (1-15) ---");
        System.out.printf("Hours Worked: %.2f%n", hours1);
        System.out.println("Gross Salary: " + php.format(gross1));
        System.out.println("Net Salary: " + php.format(gross1));

        // SECOND CUTOFF
        double hours2 =
                computeHours(empId[index], month, 16, 31);

        double gross2 = hours2 * hourlyRate[index];

        double sss = computeSSS(gross2);
        double philhealth = computePhilHealth(gross2);
        double pagibig = computePagibig(gross2);
        double tax = computeTax(gross2);

        double totalDeduction =
                sss + philhealth + pagibig + tax;

        double net2 = gross2 - totalDeduction;

        System.out.println("\n--- Cutoff 2 (16-30/31) ---");
        System.out.printf("Hours Worked: %.2f%n", hours2);
        System.out.println("Gross Salary: " + php.format(gross2));
        System.out.println("SSS: " + php.format(sss));
        System.out.println("PhilHealth: " + php.format(philhealth));
        System.out.println("Pag-IBIG: " + php.format(pagibig));
        System.out.println("Tax: " + php.format(tax));
        System.out.println("Total Deduction: " + php.format(totalDeduction));
        System.out.println("Net Salary: " + php.format(net2));
    }

    // SUMMARY TABLE
    public static void processSummary(int index, int month, int cutoff) {

        NumberFormat php =
                NumberFormat.getCurrencyInstance(new Locale("en", "PH"));

        int start = (cutoff == 1) ? 1 : 16;
        int end = (cutoff == 1) ? 15 : 31;

        double hours =
                computeHours(empId[index], month, start, end);

        double gross = hours * hourlyRate[index];

        double deduction = 0;
        double net = gross;

        if (cutoff == 2) {

            deduction =
                    computeSSS(gross)
                            + computePhilHealth(gross)
                            + computePagibig(gross)
                            + computeTax(gross);

            net = gross - deduction;
        }

        System.out.printf(
                "%-8s %-30s %-12s %-10.2f %-15s %-15s %-15s%n",
                empId[index],
                fullName[index],
                birthday[index],
                hours,
                php.format(gross),
                php.format(deduction),
                php.format(net)
        );
    }

    // HOURS CALCULATION
    public static double computeHours(
            String id, int month, int startDay, int endDay) {

        double total = 0;

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader(ATTENDANCE_FILE));

            String line;
            br.readLine();

            DateTimeFormatter dateFormat =
                    DateTimeFormatter.ofPattern("M/d/yyyy");

            DateTimeFormatter timeFormat =
                    DateTimeFormatter.ofPattern("H:mm");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (!data[0].equals(id))
                    continue;

                LocalDate date =
                        LocalDate.parse(data[3], dateFormat);

                if (date.getMonthValue() != month)
                    continue;

                int day = date.getDayOfMonth();

                if (day < startDay || day > endDay)
                    continue;

                LocalTime in =
                        LocalTime.parse(data[4], timeFormat);

                LocalTime out =
                        LocalTime.parse(data[5], timeFormat);

                // Clamp to official work hours
                if (in.isBefore(LocalTime.of(8, 0)))
                    in = LocalTime.of(8, 0);

                if (out.isAfter(LocalTime.of(17, 0)))
                    out = LocalTime.of(17, 0);

                double hours =
                        ChronoUnit.MINUTES
                                .between(in, out) / 60.0;

                // LUNCH DEDUCTION
                if (hours > 0) {

                    hours = hours - 1;

                    if (hours < 0)
                        hours = 0;

                    total += hours;
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading attendance file.");
        }

        return total;
    }

    // DEDUCTIONS
    public static double computeSSS(double gross) {
        return gross * 0.045;
    }

    public static double computePhilHealth(double gross) {
        return gross * 0.02;
    }

    public static double computePagibig(double gross) {
        return 100;
    }

    public static double computeTax(double gross) {

        if (gross <= 20833)
            return 0;

        return gross * 0.10;
    }

    // HEADER
    public static void printHeader() {

        System.out.printf(
                "%-8s %-30s %-12s %-10s %-15s %-15s %-15s%n",
                "Emp #",
                "Name",
                "Birthday",
                "Hours",
                "Gross",
                "Deductions",
                "Net"
        );
    }
}