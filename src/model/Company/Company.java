package model.Company;

import model.Employee.Employee;
import model.Payment.PaymentEmployee;
import model.Payment.PaymentHistory;
import model.Payment.Schedule.BiWeeklySchedule;
import model.Payment.Schedule.MonthlySchedule;
import model.Payment.Schedule.PaymentSchedule;
import model.Payment.Schedule.WeeklySchedule;

import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {
    private ArrayList<PaymentSchedule> paymentSchedules;
    private ArrayList<Employee> employees;
    private ArrayList<PaymentEmployee> paymentEmployees;
    private ArrayList<PaymentHistory> paymentHistories;

    public Company() {
        this.paymentSchedules = new ArrayList<PaymentSchedule>();
        this.employees = new ArrayList<Employee>();
        this.paymentEmployees = new ArrayList<PaymentEmployee>();
        this.paymentHistories = new ArrayList<PaymentHistory>();

        this.initializePaymentSchedules();
    }

    private void initializePaymentSchedules() {
        PaymentSchedule hourlySchedule = new PaymentSchedule(new WeeklySchedule(), null, 5);
        PaymentSchedule salariedSchedule = new PaymentSchedule(new MonthlySchedule(), 0, null);
        PaymentSchedule commissionedSchedule = new PaymentSchedule(new BiWeeklySchedule(), null, 5);

        paymentSchedules.add(hourlySchedule);
        paymentSchedules.add(salariedSchedule);
        paymentSchedules.add(commissionedSchedule);
    }

    public ArrayList<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<PaymentEmployee> getPaymentEmployees() {
        return paymentEmployees;
    }

    public ArrayList<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }
}
