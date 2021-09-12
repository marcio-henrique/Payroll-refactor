package main;

import controller.EmployeeController;
import controller.PaymentController;
import controller.ScheduleController;
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

                switch (option) {
                    case 0:
                        Menu.showOptions();
                        break;
                    case 1:
                        Menu.addEmployeeOption(caretaker, company, employeeController, in);
                        break;
                    case 2:
                        Menu.editEmployeeOption(caretaker, company, employeeController, in);
                        break;
                    case 3:
                        Menu.removeEmployeeOption(caretaker, company, employeeController, in);
                        break;
                    case 4:
                        Menu.listEmployeesOption(company, employeeController);
                        break;
                    case 5:
                        Menu.addTimeCardOption(caretaker, company, employeeController, in);
                        break;
                    case 6:
                        Menu.addSaleResultOption(caretaker, company, employeeController, in);
                        break;
                    case 7:
                        Menu.addAdditionalServiceTaxOption(caretaker, company, employeeController, in);
                        break;
                    case 8:
                        Menu.runPayrollOption(caretaker, company, paymentController, in);
                        break;
                    case 9:
                        company = Menu.undoOption(caretaker, company);
                        break;
                    case 10:
                        company = Menu.redoOption(caretaker, company);
                        break;
                    case 11:
                        Menu.changeEmployeePaymentScheduleOption(caretaker, company, employeeController, paymentController, in);
                        break;
                    case 12:
                        Menu.addPaymentScheduleOption(caretaker, company, paymentController, in);
                        break;
                    case 13:
                        Menu.listPaymentHistoriesOption(company);
                        break;
                    case 14:
                        System.out.println("\nBye");
                        return;
                    default:
                        System.out.println("Incorrect Option, try again. To show all options, type 0");
                        break;
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

    private static void addEmployeeOption(CaretakerCompany caretaker, Company company, EmployeeController employeeController, Scanner in) {
        caretaker.doSomething();
        System.out.println("\nADD EMPLOYEE");
        employeeController.createEmployee(in, company.getEmployees(), company.getPaymentEmployees(), company.getPaymentSchedules());
    }

    private static void editEmployeeOption(CaretakerCompany caretaker, Company company, EmployeeController employeeController, Scanner in) {
        caretaker.doSomething();

        String employeeId;
        System.out.println("\nEDIT EMPLOYEE");

        System.out.println("Employee Id:");
        employeeId = in.next();

        employeeController.editEmployeeMenu(in, employeeId, company.getEmployees(), company.getPaymentSchedules());
    }

    private static void removeEmployeeOption(CaretakerCompany caretaker, Company company, EmployeeController employeeController, Scanner in) {
        caretaker.doSomething();

        String employeeId;
        System.out.println("\nREMOVE EMPLOYEE");
        System.out.println("Employee Id:");
        employeeId = in.next();

        employeeController.deleteEmployee(employeeId, company.getEmployees(), company.getPaymentEmployees());
    }

    private static void listEmployeesOption(Company company, EmployeeController employeeController) {
        System.out.println("\nLIST EMPLOYEES");
        employeeController.listEmployees(company.getEmployees());
    }

    private static void addTimeCardOption(CaretakerCompany caretaker, Company company, EmployeeController employeeController, Scanner in) {
        caretaker.doSomething();

        String employeeId;
        System.out.println("\nADD TIME CARD");
        System.out.println("Employee Id:");
        employeeId = in.next();

        employeeController.addTimeCard(in, employeeId, company.getEmployees());
    }

    private static void addSaleResultOption(CaretakerCompany caretaker, Company company, EmployeeController employeeController, Scanner in) {
        caretaker.doSomething();

        String employeeId;
        System.out.println("\nADD SALE RESULT");
        System.out.println("Employee Id:");
        employeeId = in.next();

        employeeController.addSaleResult(in, employeeId, company.getEmployees());
    }

    private static void addAdditionalServiceTaxOption(CaretakerCompany caretaker, Company company, EmployeeController employeeController, Scanner in) {
        caretaker.doSomething();

        String employeeId;
        System.out.println("\nADD ADDITIONAL SERVICE TAX");
        System.out.println("Employee Id:");
        employeeId = in.next();

        employeeController.addAdditionalServiceTax(in, employeeId, company.getEmployees());
    }

    private static void runPayrollOption(CaretakerCompany caretaker, Company company, PaymentController paymentController, Scanner in) {
        caretaker.doSomething();

        System.out.println("\nRUN PAYROLL");

        paymentController.payRoll(in, company.getPaymentEmployees(), company.getPaymentHistories());
    }

    private static Company undoOption(CaretakerCompany caretaker, Company company) {
        company = caretaker.undo();
        System.out.println("Done");

        return company;
    }

    private static Company redoOption(CaretakerCompany caretaker, Company company) {
        company = caretaker.redo();
        System.out.println("Done");

        return company;
    }

    private static void changeEmployeePaymentScheduleOption(CaretakerCompany caretaker, Company company, EmployeeController employeeController, PaymentController paymentController, Scanner in) {
        caretaker.doSomething();

        String employeeId;
        System.out.println("\nCHANGE EMPLOYEE PAYMENT SCHEDULE");
        System.out.println("Employee Id:");
        employeeId = in.next();

        Employee employee = employeeController.searchEmployee(employeeId, company.getEmployees());
        if (employee == null) {
            System.out.println("Employee not found");
        } else {
            ScheduleController.editEmployeePaymentSchedule(in, employee.getPaymentEmployee(), company.getPaymentSchedules());
        }
    }

    private static void addPaymentScheduleOption(CaretakerCompany caretaker, Company company, PaymentController paymentController, Scanner in) {
        caretaker.doSomething();

        System.out.println("ADD NEW PAYMENT SCHEDULE");
        ScheduleController.addPaymentSchedule(in, company.getPaymentSchedules());
    }

    private static void listPaymentHistoriesOption(Company company) {
        if (company.getPaymentHistories().isEmpty()) {
            System.out.println("Empty Payment Histories");
        } else {
            PaymentController.printPaymentHistories(company.getPaymentHistories());
        }
    }






}
