package by.patrusova.project.validator;

import java.time.LocalDate;

public class DateValidator {

    private DateValidator(){}

    public static boolean isValidOrderDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minDate = currentDate.minusMonths(1);
        return ((date.compareTo(currentDate) <= 0)
            && (date.isAfter(minDate)));
    }

    public static boolean isValidDeadlineDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate maxDate = currentDate.plusMonths(1);
        return ((date.compareTo(currentDate) >= 0)
            && (date.isBefore(maxDate)));
    }
}