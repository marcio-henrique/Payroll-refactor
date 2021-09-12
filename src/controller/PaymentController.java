package controller;

import model.Employee.Commissioned;
import model.Employee.Employee;
import model.Employee.Hourly;
import model.Employee.Salaried;
import model.Payment.PaymentEmployee;
import model.Payment.PaymentHistory;
import model.Payment.Schedule.*;
import model.Syndicate.EmployeeSyndicate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PaymentController {

    public void payRoll (Scanner in, ArrayList<PaymentEmployee> paymentEmployees, ArrayList<PaymentHistory> paymentHistories) {
        LocalDate date = getPayDate(in);
        boolean payRounded = false;

        for (PaymentEmployee paymentEmployee : paymentEmployees) {
            if (paymentEmployee.isPayTime(date)) {
                payRounded = true;

                Double salary = 0.0;
                salary = this.getSalary(paymentEmployee.getEmployee(), paymentEmployee, paymentEmployee.getLastPayment(), salary);

                PaymentHistory newPayment = new PaymentHistory(paymentEmployee, date, salary);

                paymentEmployee.setPaymentHistory(newPayment);
                paymentHistories.add(newPayment);
                System.out.println(newPayment);
            }
        }

        this.printPayState(payRounded);
    }

    private void printPayState(boolean payRounded) {
        if (payRounded) {
            System.out.println("Finish");
        } else {
            System.out.println("Empty Payroll on the date");
        }
    }

    private LocalDate getPayDate(Scanner in) {
        System.out.println("date (format YYYY-MM-d, ex.: 2021-08-24):");
        String dateString = in.next();

        return LocalDate.parse(dateString);
    }

    private Double getSalary(Employee employee, PaymentEmployee paymentEmployee, PaymentHistory lastPayment, Double salary) {
        if (employee.isCommissioned()) {
            salary = this.getCommissionedSalary((Commissioned) employee, paymentEmployee, lastPayment, salary);
        } else if (employee.isSalaried()) {
            salary = employee.getSalary();
        } else if (employee.isHourly()) {
            salary = this.getHourlySalary((Hourly) employee, lastPayment, salary);
        }

        salary = this.getSyndicateDiscounts(paymentEmployee, lastPayment, employee.getEmployeeSyndicate(), salary);

        return salary;
    }

    private Double getSyndicateDiscounts(PaymentEmployee paymentEmployee, PaymentHistory lastPayment, EmployeeSyndicate employeeSyndicate, Double salary) {
        if (employeeSyndicate != null) {
            if (lastPayment == null) {
                salary -= employeeSyndicate.getAdditionalServiceTaxes();
            } else {
                salary -= employeeSyndicate.getAdditionalServiceTaxes(lastPayment.getDate());
            }

            salary -= employeeSyndicate.getMonthlyTax() / paymentEmployee.getPaymentSchedule().getMonthlyDiv();
        }
        return salary;
    }

    private Double getHourlySalary(Hourly hourly, PaymentHistory lastPayment, Double salary) {
        if (lastPayment == null) {
            salary += hourly.getFullSalary();
        } else {
            salary += hourly.getFullSalary(lastPayment.getDate());
        }

        return salary;
    }

    private Double getCommissionedSalary(Commissioned commissioned, PaymentEmployee paymentEmployee, PaymentHistory lastPayment, Double salary) {
        salary += commissioned.getSalary() / paymentEmployee.getPaymentSchedule().getMonthlyDiv();
        if (lastPayment == null) {
            salary += commissioned.getCommissionsValues();
        } else {
            salary += commissioned.getCommissionsValues(lastPayment.getDate());
        }

        return salary;
    }

    public static void printPaymentHistories (ArrayList<PaymentHistory> paymentHistories) {
        for (PaymentHistory paymentHistory: paymentHistories) {
            System.out.println(paymentHistory);
        }
    }
}
