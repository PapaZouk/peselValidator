package pl.validator.services;

import org.springframework.stereotype.Service;
import pl.validator.enums.PeselGeneratorType;
import pl.validator.model.Gender;
import pl.validator.util.DateGenerator;

import java.time.LocalDate;

@Service
public class PeselGeneratorTwentieth implements PeselGenerator {

    private static final PeselValidator validator = new PeselValidatorImpl();

    @Override
    public String generatePesel(int yearStart, int yearEnd) {
        return generatePesel(1, 31, 1, 12, yearStart, yearEnd, Gender.UNKNOWN);
    }

    @Override
    public String generatePesel(int monthStart, int monthEnd, int yearStart, int yearEnd) {
        return generatePesel(1, 31, monthStart, monthEnd, yearStart, yearEnd, Gender.UNKNOWN);
    }

    @Override
    public String generatePesel(int dayStart, int dayEnd, int monthStart, int monthEnd, int yearStart, int yearEnd) {
        return generatePesel(dayStart, dayEnd, monthStart, monthEnd, yearStart, yearEnd, Gender.UNKNOWN);
    }

    @Override
    public String generatePeselWithRandomDateAndGender() {
        return generatePesel(1, 31, 1, 12, 0, 99, Gender.UNKNOWN);
    }

    @Override
    public String generatePeselWithRandomYearMonthAndGender(int dayStart, int dayEnd) {
        return generatePesel(dayStart, dayEnd, 1, 12, 0, 99, Gender.UNKNOWN);
    }

    @Override
    public String generatePeselWithRandomYearDayAndGender(int monthStart, int monthEnd) {
        return generatePesel(1, 31, monthStart, monthEnd, 0 ,99, Gender.UNKNOWN);
    }

    @Override
    public String generatePeselWithGenderAndRandomDate(Gender gender) {
        return generatePesel(1, 31, 1, 12, 0, 99, gender);
    }

    @Override
    public String generatePeselWithYearAndGender(int yearStart, int yearEnd, Gender gender) {
        return generatePesel(1, 31, 1, 12, yearStart, yearEnd, gender);
    }

    @Override
    public String generatePeselWithMonthAndGender(int monthStart, int monthEnd, Gender gender) {
        return generatePesel(1, 31, monthStart, monthEnd, 0, 99, gender);
    }

    @Override
    public String generatePeselWithDayAndGender(int dayStart, int dayEnd, Gender gender) {
        return generatePesel(dayStart, dayEnd, 1, 12, 0, 99, gender);
    }

    @Override
    public String generatePesel(int dayStart, int dayEnd, int monthStart, int monthEnd, int yearStart, int yearEnd, Gender gender) {
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
