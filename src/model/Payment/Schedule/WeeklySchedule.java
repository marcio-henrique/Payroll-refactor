package model.Payment.Schedule;

import util.DateUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WeeklySchedule implements ScheduleStrategy{

    @Override
    public int getMonthlyDiv() {
        return 4;
    }

    @Override
    public boolean isDateToPay(PaymentSchedule paymentSchedule, LocalDate lastDate, LocalDate date) {
        if (lastDate != null) {
            return (DateUtil.isSameWeekDay(date, paymentSchedule.getWeekDay()) &&
                    ChronoUnit.WEEKS.between(lastDate, date) >= 1);
        }
        return (DateUtil.isSameWeekDay(date, paymentSchedule.getWeekDay()));
    }

    @Override
    public String toString() {
        return "weekly";
    }
}
