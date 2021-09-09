package model.Payment.Schedule;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

public class PaymentSchedule implements Serializable {
    private UUID id;
    private Integer day;    //to monthly type, 0 = last month day
    private Integer weekDay;    //for weekly type
    private ScheduleStrategy scheduleStrategy;

    public PaymentSchedule(ScheduleStrategy scheduleStrategy, Integer day, Integer weekDay) {
        this.scheduleStrategy = scheduleStrategy;
        this.day = day;
        this.weekDay = weekDay;
        this.id = UUID.randomUUID();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public ScheduleStrategy getScheduleStrategy() {
        return scheduleStrategy;
    }

    public boolean isDateToPay (LocalDate lastDate, LocalDate date) {
        return this.scheduleStrategy.isDateToPay(this, lastDate, date);
    }

    public int getMonthlyDiv () {
        return this.scheduleStrategy.getMonthlyDiv();
    }

    private String getPaymentDay() {
        if (day != null) {
            if (day == 0) {
                return "day payment = last month day";
            } else {
                return "day payment=" + day;
            }
        } else {
            return "week day payment=" + DayOfWeek.of(weekDay);
        }
    }
    @Override
    public String toString() {
        return "PaymentSchedule{" +
                "type=" + scheduleStrategy + ", " +
                getPaymentDay() +
                '}';
    }
}
