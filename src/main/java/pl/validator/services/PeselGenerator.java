package pl.validator.services;

import pl.validator.model.Gender;

public interface PeselGenerator {

    String generatePesel(int yearStart, int yearEnd);

    String generatePesel(int monthStart, int monthEnd, int yearStart, int yearEnd);

    String generatePesel(int dayStart, int dayEnd, int monthStart, int monthEnd, int yearStart, int yearEnd);
    String generatePesel(int dayStart, int dayEnd, int monthStart, int monthEnd, int yearStart, int yearEnd, Gender gender);

    String generatePeselWithRandomDateAndGender();

    String generatePeselWithRandomYearMonthAndGender(int dayStart, int dayEnd);

    String generatePeselWithRandomYearDayAndGender(int monthStart, int monthEnd);

    String generatePeselWithGenderAndRandomDate(Gender gender);

    String generatePeselWithYearAndGender(int yearStart, int yearEnd, Gender gender);

    String generatePeselWithMonthAndGender(int monthStart, int monthEnd, Gender gender);

    String generatePeselWithDayAndGender(int dayStart, int dayEnd, Gender gender);

}
