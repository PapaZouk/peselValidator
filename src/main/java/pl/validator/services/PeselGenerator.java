package pl.validator.services;

import pl.validator.model.Sex;

public interface PeselGenerator {

    String generatePesel(int yearStart, int yearEnd);

    String generatePesel(int monthStart, int monthEnd, int yearStart, int yearEnd);

    String generatePesel(int dayStart, int dayEnd, int monthStart, int monthEnd, int yearStart, int yearEnd);
    String generatePesel(int dayStart, int dayEnd, int monthStart, int monthEnd, int yearStart, int yearEnd, Sex gender);

    String generatePeselWithRandomDateAndGender();

    String generatePeselWithRandomYearMonthAndGender(int dayStart, int dayEnd);

    String generatePeselWithRandomYearDayAndGender(int monthStart, int monthEnd);

    String generatePeselWithGenderAndRandomDate(Sex sex);

    String generatePeselWithYearAndGender(int yearStart, int yearEnd, Sex sex);

    String generatePeselWithMonthAndGender(int monthStart, int monthEnd, Sex sex);

    String generatePeselWithDayAndGender(int dayStart, int dayEnd, Sex sex);

}
