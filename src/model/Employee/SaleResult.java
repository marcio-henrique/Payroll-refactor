package model.Employee;

import java.io.Serializable;
import java.time.LocalDate;

public class SaleResult implements Serializable {
    private LocalDate date;
    private Double value;

    public SaleResult(LocalDate date, Double value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SaleResult{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}
