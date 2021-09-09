package model.Employee;

import java.io.Serializable;
import java.time.LocalDate;

public class TimeCard implements Serializable {
    private LocalDate date;
    private Double workedHours;

    public TimeCard(Double workedHours, LocalDate date) {
        this.workedHours = workedHours;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getWorkedHours() {
        return workedHours;
    }

    @Override
    public String toString() {
        return "TimeCard{" +
                "date=" + date +
                ", workedHours=" + workedHours +
                '}';
    }
}
