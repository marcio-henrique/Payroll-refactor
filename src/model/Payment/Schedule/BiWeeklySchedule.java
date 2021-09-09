package model.Payment.Schedule;

import util.DateUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BiWeeklySchedule implements ScheduleStrategy{
    @Override
    public int getMonthlyDiv() {
        return 2;
    }

    @Override
    public boolean isDateToPay(PaymentSchedule paymentSchedule, LocalDate lastDate, LocalDate date) {
        if (lastDate != null) {
            return (DateUtil.isSameWeekDay(date, paymentSchedule.getWeekDay()) &&
                    ChronoUnit.WEEKS.between(lastDate, date) >= 2);
        }
        return (DateUtil.isSameWeekDay(date, paymentSchedule.getWeekDay()));
    }

    @Override
    public String toString() {
        return "bi-weekly";
    }
}
