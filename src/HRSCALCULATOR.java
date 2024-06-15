import java.util.Scanner;

public class HRSCALCULATOR {

    private String employeeName;
    private String employeeID;
    private int totalHoursWorked;
    private double totalSalary;
    private Scanner scanner;

    public HRSCALCULATOR() {
        scanner = new Scanner(System.in);
    }

    public void collectEmployeeDetails() {
        System.out.print("Employee Name: ");
        employeeName = scanner.nextLine();

        System.out.print("Employee ID: ");
        employeeID = scanner.nextLine();
    }

    public void calculateWeeklySalary() {
        totalHoursWorked = 0;
        totalSalary = 0;

        for (int day = 1; day <= 7; day++) {
            System.out.println("Day " + day);

            System.out.print("Enter login time (HHMM): ");
            int loginTime = scanner.nextInt();

            System.out.print("Enter logout time (HHMM): ");
            int logoutTime = scanner.nextInt();

            int hoursWorked = calculateHoursWorked(loginTime, logoutTime);
            totalHoursWorked += hoursWorked;

            System.out.print("Enter rate per hour: ");
            double ratePerHour = scanner.nextDouble();

            double salary = calculateSalary(hoursWorked, ratePerHour);
            totalSalary += salary;

            System.out.println("Total hours worked: " + hoursWorked);
            System.out.println("Salary earned for Day " + day + ": P" + salary);
        }
    }

    public void displaySalaryDetails() {
        double grossWeeklySalary = totalSalary;

        double pagIbigDeduction = calculatePagIbigDeduction(grossWeeklySalary);
        double sssDeduction = calculateSSSDeduction(grossWeeklySalary);
        double philHealthDeduction = calculatePhilHealthDeduction(grossWeeklySalary);
        double withholdingTax = calculateWithholdingTax(grossWeeklySalary);

        double netWeeklySalary = grossWeeklySalary - pagIbigDeduction - sssDeduction - philHealthDeduction - withholdingTax;

        System.out.println("\nEmployee Name: " + employeeName);
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Total hours worked for the week: " + totalHoursWorked);
        System.out.println("Gross weekly salary: P" + grossWeeklySalary);
        System.out.println("Deductions:");
        System.out.println("  Pag-IBIG: P" + pagIbigDeduction);
        System.out.println("  SSS: P" + sssDeduction);
        System.out.println("  PhilHealth: P" + philHealthDeduction);
        System.out.println("  Withholding Tax: P" + withholdingTax);
        System.out.println("Net weekly salary after deductions: P" + netWeeklySalary);
    }

    private int calculateHoursWorked(int loginTime, int logoutTime) {
        int loginHours = loginTime / 100;
        int loginMinutes = loginTime % 100;

        int logoutHours = logoutTime / 100;
        int logoutMinutes = logoutTime % 100;

        int totalMinutesWorked = (logoutHours * 60 + logoutMinutes) - (loginHours * 60 + loginMinutes);
        return totalMinutesWorked / 60;
    }

    private double calculateSalary(int hoursWorked, double ratePerHour) {
        return hoursWorked * ratePerHour;
    }

    private double calculatePagIbigDeduction(double grossWeeklySalary) {
        double pagIbigRate = 0.02;
        return grossWeeklySalary * pagIbigRate;
    }

    private double calculateSSSDeduction(double grossWeeklySalary) {
        double sssRate = 0.03;
        return grossWeeklySalary * sssRate;
    }

    private double calculatePhilHealthDeduction(double grossWeeklySalary) {
        double philHealthRate = 0.03;
        return grossWeeklySalary * philHealthRate;
    }

    private double calculateWithholdingTax(double grossWeeklySalary) {
        double taxRate = 0.10;
        return grossWeeklySalary * taxRate;
    }

    public void closeScanner() {
        scanner.close();
    }

    public static void main(String[] args) {
        HRSCALCULATOR hrCalculator = new HRSCALCULATOR();
        hrCalculator.collectEmployeeDetails();
        hrCalculator.calculateWeeklySalary();
        hrCalculator.displaySalaryDetails();
        hrCalculator.closeScanner();
    }
}
