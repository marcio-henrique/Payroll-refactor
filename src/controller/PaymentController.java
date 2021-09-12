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

    public static PaymentSchedule getPaymentSchedule(ScheduleStrategy scheduleStrategy, Integer day, Integer weekDay, ArrayList<PaymentSchedule> paymentSchedules) {
        for (PaymentSchedule paymentSchedule: paymentSchedules) {
            if (paymentSchedule.getScheduleStrategy().getClass().isAssignableFrom(scheduleStrategy.getClass()) &&
                    paymentSchedule.getWeekDay() == weekDay && paymentSchedule.getDay() == day) {
                return paymentSchedule;
            }
        }
        return null;
    }

    public void addPaymentSchedule (Scanner in, ArrayList<PaymentSchedule> paymentSchedules) {
        String schedule;
        String[] scheduleArray;
        System.out.println("Enter new Payment Schedule (ex: 'monthly $' -> last day of month, 'weekly 1 monday')");
        schedule = in.next();
        scheduleArray = schedule.split(" ");

        PaymentSchedule paymentSchedule = null;
        if (Objects.equals(scheduleArray[0], "monthly")) {
            int day;
            if (scheduleArray[1].charAt(0) == '$') {
                day = 0;
            } else {
                day = parseInt(scheduleArray[1]);
            }

            paymentSchedule = new PaymentSchedule(new MonthlySchedule(), day, null);

        } else if (Objects.equals(scheduleArray[0], "weekly")) {
            int frequency, weekDay;
            frequency = parseInt(scheduleArray[1]);
            weekDay = DayOfWeek.valueOf(scheduleArray[2].toUpperCase()).getValue();

            if (frequency == 1) {
                paymentSchedule = new PaymentSchedule(new WeeklySchedule(), null, weekDay);
            } else {
                paymentSchedule = new PaymentSchedule(new BiWeeklySchedule(), null, weekDay);
            }
        } else {
            System.out.println("incorrect type");
            return;
        }

        paymentSchedules.add(paymentSchedule);

        System.out.println("Successful added");
        System.out.println(paymentSchedule);

    }

    public void editEmployeePaymentSchedule (Scanner in, PaymentEmployee paymentEmployee, ArrayList<PaymentSchedule> paymentSchedules) {
        System.out.println(paymentSchedules);
        int type;
        System.out.println("Payment type (1 - monthly, 2 - weekly):");
        type = in.nextInt();

        PaymentSchedule paymentSchedule = null;
        if (type == 1) {
            Integer day;
            System.out.println("Payment day (0 for last month day)");
            day = in.nextInt();
            paymentSchedule = getPaymentSchedule(new MonthlySchedule(), day, null, paymentSchedules);

        } else if (type == 2) {
            int frequency;
            Integer weekDay;

            System.out.println("Frequency (1 - semanal, 2- bisemanal: ");
            frequency = in.nextInt();

            System.out.println("Weekday (1 - monday, ..., 5 - friday)");
            weekDay = in.nextInt();

            if (frequency == 1) {
                paymentSchedule = getPaymentSchedule(new WeeklySchedule(), null, weekDay, paymentSchedules);
            } else if (frequency == 2) {
                paymentSchedule = getPaymentSchedule(new BiWeeklySchedule(), null, weekDay, paymentSchedules);
            }
        } else {
            System.out.println("incorrect type, try again");
        }

        if (paymentSchedule == null) {
            System.out.println("Payment Schedule not found");
            return;
        }

        paymentEmployee.setPaymentSchedule(paymentSchedule);
        System.out.println("Successful edited");
        System.out.println(paymentSchedule);
    }









}
