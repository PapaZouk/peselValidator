package pl.validator.services;

import pl.validator.enums.PeselGeneratorType;
import pl.validator.model.Sex;

import java.time.LocalDate;

public class PeselGeneratorTwentieth implements PeselGenerator {

    private static final PeselValidator validator = new PeselValidatorImpl();

    @Override
    public String generatePesel(int yearStart, int yearEnd) {
        return generatePesel(1, 31, 1, 12, yearStart, yearEnd, Sex.UNKNOWN);
    }

    @Override
    public String generatePesel(int monthStart, int monthEnd, int yearStart, int yearEnd) {
        return generatePesel(1, 31, monthStart, monthEnd, yearStart, yearEnd, Sex.UNKNOWN);
    }

    @Override
    public String generatePesel(int dayStart, int dayEnd, int monthStart, int monthEnd, int yearStart, int yearEnd) {
        return generatePesel(dayStart, dayEnd, monthStart, monthEnd, yearStart, yearEnd, Sex.UNKNOWN);
    }

    @Override
    public String generatePeselWithRandomDateAndGender() {
        return generatePesel(1, 31, 1, 12, 0, 99, Sex.UNKNOWN);
    }

    @Override
    public String generatePeselWithRandomYearMonthAndGender(int dayStart, int dayEnd) {
        return generatePesel(dayStart, dayEnd, 1, 12, 0, 99, Sex.UNKNOWN);
    }

    @Override
    public String generatePeselWithRandomYearDayAndGender(int monthStart, int monthEnd) {
        return generatePesel(1, 31, monthStart, monthEnd, 0 ,99, Sex.UNKNOWN);
    }

    @Override
    public String generatePeselWithGenderAndRandomDate(Sex sex) {
        return generatePesel(1, 31, 1, 12, 0, 99, sex);
    }

    @Override
    public String generatePeselWithYearAndGender(int yearStart, int yearEnd, Sex sex) {
        return generatePesel(1, 31, 1, 12, yearStart, yearEnd, sex);
    }

    @Override
    public String generatePeselWithMonthAndGender(int monthStart, int monthEnd, Sex sex) {
        return generatePesel(1, 31, monthStart, monthEnd, 0, 99, sex);
    }

    @Override
    public String generatePeselWithDayAndGender(int dayStart, int dayEnd, Sex sex) {
        return generatePesel(dayStart, dayEnd, 1, 12, 0, 99, sex);
    }

    @Override
    public String generatePesel(int dayStart, int dayEnd, int monthStart, int monthEnd, int yearStart, int yearEnd, Sex gender) {
        if (yearStart < 0 || yearEnd > 99) {
            throw new IllegalArgumentException("Illegal boundaries. Year start must be >= 0 and year end <= 99");
        }
        if (monthStart <= 0 || monthEnd > 12) {
            throw new IllegalArgumentException("Invalid month boundaries. Month start should be >= 1 and month end <= 12");
        }
        if (dayStart <= 0 || dayEnd > 31) {
            throw new IllegalArgumentException("Invalid day boundaries. Day start should be >= 1 and day end <= 31");
        }

        LocalDate date = DateGenerator.generateRandomDate(
                dayStart,
                dayEnd + 1,
                monthStart,
                monthEnd + 1,
                yearStart,
                yearEnd + 1,
                PeselGeneratorType.TWENTIETH_CENTURY);
        String PESEL = DateToPeselParser.parseDateToPesel(date, PeselGeneratorType.TWENTIETH_CENTURY, gender);

        if (!validator.validatePESEL(PESEL)) {
            generatePesel(monthStart, monthEnd, yearStart, yearEnd);
        }
        return PESEL;
    }


}
