package pl.validator.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.validator.enums.PeselGeneratorType;
import pl.validator.model.Gender;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateToValidationResultParserTest {

    @Test
    @DisplayName("Should parse given date to 11 digit PESEL number in form of a string")
    void parseGivenDateWhenParseDateToPeselThenExpectedLength() {
        // given
        LocalDate date = LocalDate.of(2012, 4, 16);
        int expectedPeselLength = 11;
        // when
        String result = DateToPeselParser.parseDateToPesel(date, PeselGeneratorType.TWENTY_FIRST_CENTURY);
        // then
        Assertions.assertEquals(expectedPeselLength, result.length());
    }

    @Test
    @DisplayName("Should return PESEL from nineteenth century at the beginning")
    void givenDateWhenParsingToPeselThenEqualDates() {
        // given
        LocalDate date = LocalDate.of(1850, 11, 12);
        String expectedDate = String.valueOf(date.getYear()).substring(2)
                + String.valueOf(date.getMonthValue() + 80)
                + String.valueOf(date.getDayOfMonth());
        // when
        String result = DateToPeselParser.parseDateToPesel(date, PeselGeneratorType.NINETEENTH_CENTURY, Gender.FEMALE);
        String resultDate = result.substring(0, 6);
        // then
        Assertions.assertEquals(expectedDate, resultDate);
    }

    @Test
    @DisplayName("Should return month value respectively to given century")
    void givenGeneratorTypeAndMonthThenReturnProperMonthValue() {
        // given
        PeselGeneratorType nineteenthCentury = PeselGeneratorType.NINETEENTH_CENTURY;
        PeselGeneratorType twentiethCentury = PeselGeneratorType.TWENTIETH_CENTURY;
        PeselGeneratorType twentyFirstCentury = PeselGeneratorType.TWENTY_FIRST_CENTURY;

        int monthValue = 9;
        int expectedValue1 = 89;
        int expectedValue2 = 9;
        int expectedValue3 = 29;
        // when
        int result1 = DateToPeselParser.checkCentury(nineteenthCentury, monthValue);
        int result2 = DateToPeselParser.checkCentury(twentiethCentury, monthValue);
        int result3 = DateToPeselParser.checkCentury(twentyFirstCentury, monthValue);
        // then
        Assertions.assertEquals(expectedValue1, result1);
        Assertions.assertEquals(expectedValue2, result2);
        Assertions.assertEquals(expectedValue3, result3);
    }

    @Test
    @DisplayName("Should return month value in form of a string with length equal 2")
    void givenMonthValueWhenGetMonthThenEqualLength() {
        // given
        int monthValue = 1;
        int expectedStringLength = 2; // 02
        // when
        int resultLength = DateToPeselParser.getMonth(monthValue).length();
        // then
        Assertions.assertEquals(expectedStringLength, resultLength);
    }

    @Test
    @DisplayName("Should return day in form of a string with length equal 2")
    void givenDayValueWhenGetDayThenEqualLength() {
        // given
        LocalDate date = LocalDate.of(1999, 10, 3);
        int expectedStringLength = 2; //03
        // when
        int resultLength = DateToPeselParser.getDay(date).length();
        // then
        Assertions.assertEquals(expectedStringLength, resultLength);
    }

    @Test
    @DisplayName("Should generate string of number with length value of 3")
    void generateSerialNum() {
        // given
        int expectedLengthOfString = 3;
        // when
        int resultLength = DateToPeselParser.generateSerialNum().length();
        // then
        Assertions.assertEquals(String.class, DateToPeselParser.generateSerialNum().getClass());
        Assertions.assertEquals(expectedLengthOfString, resultLength);
    }

    @Test
    @DisplayName("Should generate 1000 strings of number in range of 100 and 999")
    void generateStringOfNumbers() {
        // given
        int expectedMinValue = 0;
        int expectedMaxValue = 999;
        // when, then
        for (int i = 0; i < 1000; i++) {
            int resultNumber = Integer.parseInt(DateToPeselParser.generateSerialNum());
            Assertions.assertTrue(resultNumber >= expectedMinValue && resultNumber <= expectedMaxValue);
        }
    }

    @Test
    @DisplayName("Should generate random string value of odd number or even number with given gender type in range of 0 to 10")
    void generateSex() {
        // given
        Gender male = Gender.MALE;
        Gender female = Gender.FEMALE;
        Gender unknown = Gender.UNKNOWN;
        // when
        int maleNumberResult = Integer.parseInt(DateToPeselParser.generateSex(male));
        int femaleNumberResult = Integer.parseInt(DateToPeselParser.generateSex(female));
        int randomNumber = Integer.parseInt(DateToPeselParser.generateSex(unknown));
        // then
        assertEquals(1, maleNumberResult % 2);
        assertEquals(0, femaleNumberResult % 2);
        Assertions.assertEquals(String.class, DateToPeselParser.generateSex(unknown).getClass());
        Assertions.assertTrue(randomNumber >= 0 && randomNumber < 10);
    }

    @Test
    @DisplayName("Should return string with length value equal 1")
    void shouldGenerateSingleStringValueOfControlNumber() {
        // given
        String[] dataInput1 = new String[]{"05", "11", "22", "123", "3"};
        String[] dataInput2 = new String[]{"01", "09", "89", "737", "9"};

        int expectedLength = 1;

        // when
        int result1 = DateToPeselParser.generateControlNum(
                dataInput1[0], dataInput1[1], dataInput1[2], dataInput1[3], dataInput1[4]
        ).length();
        int result2 = DateToPeselParser.generateControlNum(
                dataInput2[0], dataInput2[1], dataInput2[2], dataInput2[3], dataInput2[4]
        ).length();
        // then
        Assertions.assertEquals(expectedLength, result1);
        Assertions.assertEquals(expectedLength, result2);
    }
}