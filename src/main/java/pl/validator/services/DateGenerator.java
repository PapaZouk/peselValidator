package pl.validator.services;

import pl.validator.enums.PeselGeneratorType;

import java.time.LocalDate;
import java.util.Random;

public class DateGenerator {

    public static final Random RANDOM = new Random();

    public static LocalDate generateRandomDate(int dayStart,
                                               int dayEnd,
                                               int monthStart,
                                               int monthEnd,
                                               int yearStart,
                                               int yearEnd,
                                               PeselGeneratorType generatorType) {
        int year = RANDOM.nextInt(yearStart, yearEnd) + getCentury(generatorType);
        int month = RANDOM.nextInt(monthStart, monthEnd);
        int day = RANDOM.nextInt(dayStart, dayEnd);

        if (DateValidator.isValid(year, month, day)) {
            return LocalDate.of(year, month, day);
        } else {
            System.err.println("Invalid date. Generating new date.");
        }
        return generateRandomDate(dayStart, dayEnd, monthStart, monthEnd, yearStart, yearEnd, generatorType);
    }

    public static LocalDate generateRandomDate(int monthStart,
                                               int monthEnd,
                                               int yearStart,
                                               int yearEnd,
                                               PeselGeneratorType generatorType) {
        return generateRandomDate(1, 31, monthStart, monthEnd, yearStart, yearEnd, generatorType);
    }

    public static LocalDate generateRandomDate(int yearStart,
                                               int yearEnd,
                                               PeselGeneratorType generatorType) {
        return generateRandomDate(1, 31, 1, 13, yearStart, yearEnd, generatorType);
    }

    private static int getCentury(PeselGeneratorType generatorType) {
        if (PeselGeneratorType.NINETEENTH_CENTURY.equals(generatorType)) {
            return 1800;
        } else if (PeselGeneratorType.TWENTIETH_CENTURY.equals(generatorType)) {
            return 1900;
        } else if (PeselGeneratorType.TWENTY_FIRST_CENTURY.equals(generatorType)) {
            return 2000;
        } else {
            throw new IllegalArgumentException("PESEL generation type not found");
        }
    }
}
