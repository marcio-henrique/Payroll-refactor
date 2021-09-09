package model.Payment.Schedule;

import java.io.Serializable;
import java.time.LocalDate;

public interface ScheduleStrategy extends Serializable {
    int getMonthlyDiv();

    boolean isDateToPay(PaymentSchedule paymentSchedule, LocalDate lastDate, LocalDate date);


}
