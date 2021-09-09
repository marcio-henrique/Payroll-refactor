package model.Payment.Schedule;

import util.DateUtil;

import java.time.LocalDate;

public class MonthlySchedule implements ScheduleStrategy{
    @Override
    public int getMonthlyDiv() {
        return 1;
    }

    @Override
    public boolean isDateToPay(PaymentSchedule paymentSchedule, LocalDate lastDate, LocalDate date) {
        if (paymentSchedule.getDay() == 0)/*last month day*/{
            return DateUtil.isLastWorkDayOfMonth(date);

        } else return DateUtil.isSameDay(date, paymentSchedule.getWeekDay());
    }

    @Override
    public String toString() {
        return "monthly";
    }
}
