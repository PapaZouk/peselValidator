package pl.validator.services;

import pl.validator.model.Sex;

public interface PeselGenerator {

    String generatePesel(int yearStart, int yearEnd);

    String generatePesel(int monthStart, int monthEnd, int yearStart, int yearEnd);

    String generatePesel(int dayStart, int dayEnd, int monthStart, int monthEnd, int yearStart, int yearEnd);

    String generatePeselWithRandomDateAndGender();

    String generatePeselWithRandomYearMonthAndGender(int dayStart, int dayEnd);

    String generatePeselWithRandomYearDayAndGender(int monthStart, int monthEnd);

    String generatePeselWithGenderAndRandomDate(Sex sex);

    String generatePeselWithGenderAndYear(Sex sex);

    String generatePeselWithGenderAndMonth(Sex sex);

    String generatePeselWithGenderDayMonthAndYear(Sex sex, int dayStart, int dayEnd, int monthStart, int MonthEnd, int yearStart, int yearEnd);

}
