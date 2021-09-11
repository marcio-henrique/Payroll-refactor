package main;

import controller.EmployeeController;
import controller.PaymentController;
import model.Company.Company;
import model.Company.Memento.CaretakerCompany;
import model.Employee.Employee;

import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void menu(Company company) {
        EmployeeController employeeController = new EmployeeController();
        PaymentController paymentController = new PaymentController();

        CaretakerCompany caretaker = new CaretakerCompany(company);

        Scanner in = new Scanner(System.in);
        in.useDelimiter("\r?\n");

        int option;
        Menu.showOptions();

        try {
            while (true) {
                option = in.nextInt();

                assert company != null;

                if (option == 0) {
                    Menu.showOptions();

                } else if (option == 1) {
                    caretaker.doSomething();

                    System.out.println("\nADD EMPLOYEE");
                    employeeController.createEmployee(in, company.getEmployees(), company.getPaymentEmployees(), company.getPaymentSchedules());

                } else if (option == 2) {
                    caretaker.doSomething();

                    String employeeId;
                    System.out.println("\nEDIT EMPLOYEE");

                    System.out.println("Employee Id:");
                    employeeId = in.next();

                    employeeController.editEmployeeMenu(in, employeeId, company.getEmployees(), company.getPaymentSchedules());

                } else if (option == 3) {
                    caretaker.doSomething();

                    String employeeId;
                    System.out.println("\nREMOVE EMPLOYEE");
                    System.out.println("Employee Id:");
                    employeeId = in.next();

                    employeeController.deleteEmployee(employeeId, company.getEmployees(), company.getPaymentEmployees());
                } else if (option == 4) {
                    System.out.println("\nLIST EMPLOYEES");
                    employeeController.listEmployees(company.getEmployees());

                } else if (option == 5) {
                    caretaker.doSomething();

                    String employeeId;
                    System.out.println("\nADD TIME CARD");
                    System.out.println("Employee Id:");
                    employeeId = in.next();

                    employeeController.addTimeCard(in, employeeId, company.getEmployees());

                } else if (option == 6) {
                    caretaker.doSomething();

                    String employeeId;
                    System.out.println("\nADD SALE RESULT");
                    System.out.println("Employee Id:");
                    employeeId = in.next();

                    employeeController.addSaleResult(in, employeeId, company.getEmployees());

                } else if (option == 7) {
                    caretaker.doSomething();

                    String employeeId;
                    System.out.println("\nADD ADDITIONAL SERVICE TAX");
                    System.out.println("Employee Id:");
                    employeeId = in.next();

                    employeeController.addAdditionalServiceTax(in, employeeId, company.getEmployees());

                } else if (option == 8) {
                    caretaker.doSomething();

                    System.out.println("\nRUN PAYROLL");

                    paymentController.payRoll(in, company.getPaymentEmployees(), company.getPaymentHistories());

                } else if (option == 9) {
                    company = caretaker.undo();
                } else if (option == 10) {
                    company = caretaker.redo();

                } else if (option == 11) {
                    caretaker.doSomething();

                    String employeeId;
                    System.out.println("\nCHANGE EMPLOYEE PAYMENT SCHEDULE");
                    System.out.println("Employee Id:");
                    employeeId = in.next();

                    Employee employee = employeeController.searchEmployee(employeeId, company.getEmployees());
                    if (employee == null) {
                        System.out.println("Employee not found");
                    } else {
                        paymentController.editEmployeePaymentSchedule(in, employee.getPaymentEmployee(), company.getPaymentSchedules());
                    }
                } else if (option == 12) {
                    caretaker.doSomething();

                    System.out.println("ADD NEW PAYMENT SCHEDULE");
                    paymentController.addPaymentSchedule(in, company.getPaymentSchedules());

                } else if (option == 13) {
                    if (company.getPaymentHistories().isEmpty()) {
                        System.out.println("Empty Payment Histories");
                    } else {
                        PaymentController.printPaymentHistories(company.getPaymentHistories());
                    }

                } else if (option == 14) {
                    System.out.println("\nBye");
                    return;

                } else {
                    System.out.println("Incorrect Option, try again. To show all options, type 0");
                }

            }
        } catch (DateTimeException | IndexOutOfBoundsException err) {
            System.out.println("Invalid Date");
            menu(company);

        } catch (InputMismatchException | NumberFormatException err){
            System.out.println("Invalid Input");
            menu(company);
        }
    }

    private static void showOptions() {
        System.out.println("0 - Show options");
        System.out.println("1 - Add Employee");
        System.out.println("2 - Edit Employee");
        System.out.println("3 - Remove Employee");
        System.out.println("4 - List Employees");
        System.out.println("5 - Add Time Card for Hourly Employee");
        System.out.println("6 - Add Sale Result for Commissioned Employee");
        System.out.println("7 - Add Additional Service Tax for Syndicalist Employee");
        System.out.println("8 - Run Payroll");
        System.out.println("9 - Undo");
        System.out.println("10 - Redo");
        System.out.println("11 - Change Employee Payment Schedule");
        System.out.println("12 - Add New Payment Schedule");
        System.out.println("13 - List Payment Histories");
        System.out.println("14 - Exit");
    }
}
