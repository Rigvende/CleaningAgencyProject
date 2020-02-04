package by.patrusova.project.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateValidator {

    private DateValidator(){}

    public static boolean isValidOrderDate(Date date) {
        Date currentDate = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, -1);
        Date minDate = calendar.getTime();
        return ((date.compareTo(currentDate) <= 0)
            && (date.after(minDate)));
    }

    public static boolean isValidDeadlineDate(Date date) {
        Date currentDate = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, 1);
        Date maxDate = calendar.getTime();
        return ((date.compareTo(currentDate) >= 0)
            && (date.before(maxDate)));
    }
}