package model.Payment.Schedule;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class PaymentSchedule implements Serializable {
    private Integer day;    //to monthly type, 0 = last month day
    private Integer weekDay;    //for weekly type
    private ScheduleStrategy scheduleStrategy;

    public PaymentSchedule(ScheduleStrategy scheduleStrategy, Integer day, Integer weekDay) {
        this.scheduleStrategy = scheduleStrategy;
        this.day = day;
        this.weekDay = weekDay;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getWeekDay() {
        return weekDay;
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
