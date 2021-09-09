package model.Syndicate;

import java.io.Serializable;
import java.time.LocalDate;

public class AdditionalServiceTax implements Serializable {
    private LocalDate date;
    private Double value;

    public AdditionalServiceTax(LocalDate date, Double value) {
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
        return "\n\t\tAdditionalServiceTax{" +
                "value=" + value +
                ", date=" + date +
                '}';
    }
}
