package model.Employee;

import model.Payment.PaymentEmployee;
import model.Syndicate.EmployeeSyndicate;

import java.io.Serializable;
import java.util.UUID;

public class Employee implements Serializable {
    private UUID id;
    private String name;
    private Double salary;
    private String address;
    private EmployeeSyndicate employeeSyndicate;
    private PaymentEmployee paymentEmployee;

    public Employee(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getSalary(), employee.getAddress());
    }
    public Employee(String name, Double salary, String address) {
        this(UUID.randomUUID(), name, salary, address);
    }

    public Employee(UUID id, String name, Double salary, String address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.employeeSyndicate = null;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmployeeSyndicate getEmployeeSyndicate() {
        return employeeSyndicate;
    }

    public void setEmployeeSyndicate(EmployeeSyndicate employeeSyndicate) {
        this.employeeSyndicate = employeeSyndicate;
    }

    public PaymentEmployee getPaymentEmployee() {
        return paymentEmployee;
    }

    public void setPaymentEmployee(PaymentEmployee paymentEmployee) {
        this.paymentEmployee = paymentEmployee;
    }

    public String syndicateString() {
        if (this.employeeSyndicate == null) {
            return "No";
        } else {
            return "Yes\n\t" + this.employeeSyndicate;
        }
    }

    public boolean isCommissioned() {
        return this.getClass().isAssignableFrom(Commissioned.class);
    }

    public boolean isSalaried() {
        return this.getClass().isAssignableFrom(Salaried.class);
    }

    public boolean isHourly() {
        return this.getClass().isAssignableFrom(Hourly.class);
    }

    @Override
    public String toString() {
        return "Employee" + ":\n\t" +
                "id: " + this.id + "\n\t" +
                "name: " + this.name + "\n\t" +
                "salary: " + this.salary + "\n\t" +
                "address: " + this.address + "\n\t" +
                "Syndicalized: " + this.syndicateString() +
                this.paymentEmployee;
    }
}
