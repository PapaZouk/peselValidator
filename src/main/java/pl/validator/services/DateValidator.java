package pl.validator.services;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DateValidator {
    public static boolean isValid(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }
}
