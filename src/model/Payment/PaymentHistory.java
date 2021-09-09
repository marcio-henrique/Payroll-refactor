package model.Payment;

import java.io.Serializable;
import java.time.LocalDate;

public class PaymentHistory implements Serializable {
    private PaymentEmployee paymentEmployee;
    private LocalDate date;
    private Double value;

    public PaymentHistory(PaymentEmployee paymentEmployee, LocalDate date, Double value) {
        this.paymentEmployee = paymentEmployee;
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "PaymentHistory:\n\t" +
                "Employee id: " + paymentEmployee.getEmployee().getId() + "\n\t" +
                "date: " + date + "\n\t" +
                "value: " + value + "\n\t" +
                paymentEmployee.getPaymentMethod() +
                "\n";
    }
}
