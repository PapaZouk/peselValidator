package pl.validator.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.validator.enums.PeselGeneratorType;

import java.time.LocalDate;

class DateGeneratorTest {


    @Test
    @DisplayName("Should generate random XX century date")
    void generateXXCenturyDate() {
        // given
        LocalDate expectedYear = LocalDate.of(1900, 1, 1);
        // when
        LocalDate result = DateGenerator.generateRandomDate(0, 1, PeselGeneratorType.TWENTIETH_CENTURY);
        //then
        Assertions.assertTrue(result.isAfter(expectedYear));
    }

    @Test
    @DisplayName("Should generate random XIX century date")
    void generateXXICenturyDate() {
        // given
        LocalDate expectedCentury = LocalDate.of(2000, 1, 1);
        // when
        LocalDate result1 = DateGenerator.generateRandomDate(0, 1, PeselGeneratorType.TWENTY_FIRST_CENTURY);
        // then
        Assertions.assertTrue(result1.isAfter(expectedCentury));
    }

    @Test
    void generateXIXCenturyDate() {
        // given
        LocalDate expectedYear = LocalDate.of(1800, 1, 1);
        // when
        LocalDate result = DateGenerator.generateRandomDate(0, 1, PeselGeneratorType.NINETEENTH_CENTURY);
        // then
        Assertions.assertTrue(result.isAfter(expectedYear));
    }

    @Test
    @DisplayName("Should generate expected year with given year")
    void generateExpectedYear() {
        // given
        LocalDate expectedYear1 = LocalDate.of(1812, 1, 1);
        LocalDate expectedYear2 = LocalDate.of(2005, 1, 1);
        // when
        LocalDate result1 = DateGenerator.generateRandomDate(12, 13, PeselGeneratorType.NINETEENTH_CENTURY);
        LocalDate result2 = DateGenerator.generateRandomDate(5, 6, PeselGeneratorType.TWENTY_FIRST_CENTURY);
        // then
        Assertions.assertEquals(expectedYear1.getYear(), result1.getYear());
        Assertions.assertEquals(expectedYear2.getYear(), result2.getYear());
    }

    @Test
    @DisplayName("Should generate random date with give month and year for XX century")
    void generateRandomDayWithGivenExpectedDay() {
        // given
        LocalDate expectedDate = LocalDate.of(1991, 1, 11);
        // when
        LocalDate resultDate = DateGenerator.generateRandomDate(
                1, 2, 91, 92,
                PeselGeneratorType.TWENTIETH_CENTURY
        );
        // then
        Assertions.assertEquals(expectedDate.getMonthValue(), resultDate.getMonthValue());
        Assertions.assertEquals(expectedDate.getYear(), resultDate.getYear());
    }

    @Test
    @DisplayName("Should generate random date with expected given month and year for XXI century")
    void generateRandomDateWithGivenYearAndMonth() {
        // given
        LocalDate expectedDate = LocalDate.of(2010, 10, 1);
        // when
        LocalDate result = DateGenerator.generateRandomDate(
                10, 11, 10, 11,
                PeselGeneratorType.TWENTY_FIRST_CENTURY
        );
        // then
        Assertions.assertEquals(expectedDate.getYear(), result.getYear());
        Assertions.assertEquals(expectedDate.getMonthValue(), result.getMonthValue());
    }

    @Test
    @DisplayName("Should generate date with given day, month and year for XX century")
    void generateRandomDateWithGivenDayMonthYear() {
        //given
        LocalDate expectedDate = LocalDate.of(1995, 5, 25);
        // when
        LocalDate result = DateGenerator.generateRandomDate(
                25, 26, 5, 6, 95, 96,
                PeselGeneratorType.TWENTIETH_CENTURY
        );
        // then
        Assertions.assertEquals(expectedDate.getYear(), result.getYear());
        Assertions.assertEquals(expectedDate.getMonthValue(), result.getMonthValue());
        Assertions.assertEquals(expectedDate.getDayOfMonth(), result.getDayOfMonth());
    }

    @Test
    @DisplayName("Should return correct century beginning year")
    void generateGivenCentury() {
        // given
        int nineteenthCentury = 1800;
        int twentiethCentury = 1900;
        int twentyFirstCentury = 2000;
        // when
        int firstResult = DateGenerator.getCentury(PeselGeneratorType.NINETEENTH_CENTURY);
        int secondResult = DateGenerator.getCentury(PeselGeneratorType.TWENTIETH_CENTURY);
        int thirdResult = DateGenerator.getCentury(PeselGeneratorType.TWENTY_FIRST_CENTURY);
        // when
        Assertions.assertEquals(nineteenthCentury, firstResult);
        Assertions.assertEquals(twentiethCentury, secondResult);
        Assertions.assertEquals(twentyFirstCentury, thirdResult);
    }

    @Test
    @DisplayName("Should fail generating century")
    void givenWrongCenturyWhenGeneratingCenturyThenThrowException() {
        // given
        String expectedMsg = "PESEL generation type not found";
        // when
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> DateGenerator.getCentury(null)
        );
        // then
        Assertions.assertEquals(expectedMsg, exception.getMessage());
    }

}