package controller;

import model.Payment.PaymentEmployee;
import model.Payment.Schedule.*;
import util.DateUtil;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ScheduleController {
    public static PaymentSchedule getPaymentSchedule(ScheduleStrategy scheduleStrategy, Integer day, Integer weekDay, ArrayList<PaymentSchedule> paymentSchedules) {
        for (PaymentSchedule paymentSchedule: paymentSchedules) {
            if (paymentSchedule.getScheduleStrategy().getClass().isAssignableFrom(scheduleStrategy.getClass()) &&
                    paymentSchedule.getWeekDay() == weekDay && paymentSchedule.getDay() == day) {
                return paymentSchedule;
            }
        }
        return null;
    }

    public static void addPaymentSchedule (Scanner in, ArrayList<PaymentSchedule> paymentSchedules) {
        String[] scheduleArray = getScheduleStringArray(in);

        PaymentSchedule paymentSchedule = null;
        if (Objects.equals(scheduleArray[0], "monthly")) {
            paymentSchedule = createMonthlySchedule(scheduleArray[1]);
        } else if (Objects.equals(scheduleArray[0], "weekly")) {
            createWeeklySchedule(scheduleArray[1], scheduleArray[2]);
        } else {
            System.out.println("incorrect type");
        }

        if (paymentSchedule == null) {
            return;
        }

        paymentSchedules.add(paymentSchedule);
        printSchedule(paymentSchedule);
    }

    private static void printSchedule(PaymentSchedule paymentSchedule) {
        System.out.println("Successful");
        System.out.println(paymentSchedule);
    }

    private static PaymentSchedule createWeeklySchedule(String frequencyString, String weekDayString) {
        int frequency = parseInt(frequencyString);
        int weekDay = DateUtil.string2weekDayValue(weekDayString);

        PaymentSchedule paymentSchedule = null;
        if (frequency == 1) {
            paymentSchedule = new PaymentSchedule(new WeeklySchedule(), null, weekDay);
        } else if (frequency == 2){
            paymentSchedule = new PaymentSchedule(new BiWeeklySchedule(), null, weekDay);
        }

        return paymentSchedule;
    }

    private static PaymentSchedule createMonthlySchedule(String dayString) {
        int day;
        if (dayString.charAt(0) == '$') {
            day = 0;
        } else {
            day = parseInt(dayString);

            if (day < 0 || day > 31) {
                return null;
            }
        }
        return new PaymentSchedule(new MonthlySchedule(), day, null);
    }

    private static String[] getScheduleStringArray(Scanner in) {
        String schedule;
        System.out.println("Enter new Payment Schedule (ex: 'monthly $' -> last day of month, 'weekly 1 monday')");
        schedule = in.next();

        return schedule.split(" ");
    }

    public static void editEmployeePaymentSchedule (Scanner in, PaymentEmployee paymentEmployee, ArrayList<PaymentSchedule> paymentSchedules) {
        System.out.println(paymentSchedules);
        int type = getScheduleType(in);

        PaymentSchedule paymentSchedule = null;
        if (type == 1) {
            paymentSchedule = getMonthlySchedule(in, paymentSchedules);

        } else if (type == 2) {
            paymentSchedule = getWeeklySchedule(in, paymentSchedules);
        } else {
            System.out.println("incorrect type");
        }

        if (paymentSchedule == null) {
            System.out.println("Payment Schedule not found");
            return;
        }

        paymentEmployee.setPaymentSchedule(paymentSchedule);
        printSchedule(paymentSchedule);
    }

    private static int getScheduleType(Scanner in) {
        System.out.println("Payment type (1 - monthly, 2 - weekly):");
        return in.nextInt();
    }

    private static PaymentSchedule getWeeklySchedule(Scanner in, ArrayList<PaymentSchedule> paymentSchedules) {
        int frequency = getFrequency(in);
        int weekDay = getWeekDay(in);

        PaymentSchedule paymentSchedule = null;
        if (frequency == 1) {
            paymentSchedule = getPaymentSchedule(new WeeklySchedule(), null, weekDay, paymentSchedules);
        } else if (frequency == 2) {
            paymentSchedule = getPaymentSchedule(new BiWeeklySchedule(), null, weekDay, paymentSchedules);
        }
        return paymentSchedule;
    }

    private static int getWeekDay(Scanner in) {
        System.out.println("Weekday (1 - monday, ..., 5 - friday)");
        return in.nextInt();
    }

    private static int getFrequency(Scanner in) {
        System.out.println("Frequency (1 - semanal, 2- bisemanal: ");
        return in.nextInt();
    }

    private static PaymentSchedule getMonthlySchedule(Scanner in, ArrayList<PaymentSchedule> paymentSchedules) {
        int day = getPaymentDay(in);
        return getPaymentSchedule(new MonthlySchedule(), day, null, paymentSchedules);
    }

    private static Integer getPaymentDay(Scanner in) {
        System.out.println("Payment day (0 for last month day)");
        return in.nextInt();
    }
}
