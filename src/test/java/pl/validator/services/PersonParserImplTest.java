package pl.validator.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.validator.model.Sex;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PersonParserImplTest {

    private static PersonParserImpl parser;

    @BeforeEach
    void setUp() {
        parser = new PersonParserImpl();
    }

    @Test
    void getPerson() {
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should parse properly given PESEL to month value depending on Year value")
    void parseMonth(int expected, String pesel, int year) {
        // given, when
        int resultMonth = parser.parseMonth(pesel, year);
        // then
        Assertions.assertEquals(expected,resultMonth);

    }

    public static Stream<Arguments> parseMonth() {
        return Stream.of(
                Arguments.of(5,"42050664283", 1942),
                Arguments.of(10,"80102947395", 1980),
                Arguments.of(11,"46911625325", 1846),
                Arguments.of(3,"90232969866", 2090),
                Arguments.of(1,"10012086299", 1910),
                Arguments.of(9,"02892086926", 1802),
                Arguments.of(22,"18422646487", 2018),
                Arguments.of(11,"86110941387", 1986)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should parse properly given PESEL String to year")
    void parseGivenPESELStringToYear(int expected, String pesel) {
        // given, when
        int resultYear = parser.parseYear(pesel);
        // then
        Assertions.assertEquals(expected, resultYear);
    }

    public static Stream<Arguments> parseGivenPESELStringToYear() {
        return Stream.of(
                Arguments.of(1949, "49070836533"),
                Arguments.of(1996, "96040214572"),
                Arguments.of(1942, "42050664283"),
                Arguments.of(1980, "80102947395"),
                Arguments.of(1946, "46111625325"),
                Arguments.of(1990, "90032969866"),
                Arguments.of(1910, "10012086299"),
                Arguments.of(1902, "02092086926"),
                Arguments.of(1918, "18022646487"),
                Arguments.of(1986, "86110941387")

        );
    }

    @Test
    void getYearMillenium() {
    }

    @Test
    void getMonthNineteenthCentury() {
    }

    @Test
    void getMonthMillenium() {
    }

    @Test
    @DisplayName("Should return proper Sex enum type of gender")
    void getSex() {
        // given
        String value1 = "1";
        String value2 = "2";
        String value3 = "4";
        String value4 = "9";

        Sex expected1 = Sex.MALE;
        Sex expected2 = Sex.FEMALE;
        Sex expected3 = Sex.FEMALE;
        Sex expected4 = Sex.MALE;

        // when
        Sex result1 = parser.getSex(value1);
        Sex result2 = parser.getSex(value2);
        Sex result3 = parser.getSex(value3);
        Sex result4 = parser.getSex(value4);

        // then
        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);
        Assertions.assertEquals(expected3, result3);
        Assertions.assertEquals(expected4, result4);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should return int value of year of XX century")
    void getTwentyCenturyYear(int expected, String givenYear) {
        // given, when
        int resultYear = parser.getYearTwentyCentury(givenYear);
        // then
        Assertions.assertEquals(expected, resultYear);
    }

    public static Stream<Arguments> getTwentyCenturyYear() {
        return Stream.of(
                Arguments.of(1912, "12"),
                Arguments.of(1988, "88"),
                Arguments.of(1952, "52"),
                Arguments.of(1900, "00"),
                Arguments.of(1920, "20"),
                Arguments.of(1999, "99"),
                Arguments.of(1901, "01")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should return int value of year of XXI century")
    void getTwentyFirstCenturyYear(int expected, String givenYear) {
        // given, when
        int resultYear = parser.getYearTwentyFirstCentury(givenYear);
        // then
        Assertions.assertEquals(expected, expected);
    }

    public static Stream<Arguments> getTwentyFirstCenturyYear() {
        return Stream.of(
                Arguments.of(2099, "99"),
                Arguments.of(2000, "00"),
                Arguments.of(2023, "23"),
                Arguments.of(2012, "12"),
                Arguments.of(2007, "07"),
                Arguments.of(2050, "50"),
                Arguments.of(2001, "01")
        );
    }
    @ParameterizedTest
    @MethodSource
    @DisplayName("Should return int value of year of XIX century")
    void getNineteenthCenturyYear(int expected, String givenYear) {
        // given, when
        int resultYear = parser.getYearNineteenthCentury(givenYear);
        // then
        Assertions.assertEquals(expected, expected);
    }

    public static Stream<Arguments> getNineteenthCenturyYear() {
        return Stream.of(
                Arguments.of(1899, "99"),
                Arguments.of(1800, "00"),
                Arguments.of(1823, "23"),
                Arguments.of(1812, "12"),
                Arguments.of(1807, "07"),
                Arguments.of(1850, "50"),
                Arguments.of(1801, "01")
        );
    }

    @Test
    @DisplayName("Should throw exception when given argument is null or too long for TwentyFirstCentury year")
    void shouldFailGetTwentyFirstCenturyYear() {
        // given
        String input1 = "";
        String input2 = "912";
        String input3 = "null";

        String expectedMsg = "Provided year code is empty or is too long";

        // when
        Throwable exception1 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> parser.getYearTwentyFirstCentury(input1));
        Throwable exception2 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> parser.getYearTwentyFirstCentury(input2));
        Throwable exception3 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> parser.getYearTwentyFirstCentury(input3));

        // then
        Assertions.assertEquals(expectedMsg, exception1.getMessage());
        Assertions.assertEquals(expectedMsg, exception2.getMessage());
        Assertions.assertEquals(expectedMsg, exception3.getMessage());
    }
    @Test
    @DisplayName("Should throw exception when given argument is null or too long for NineteenthCentury year")
    void shouldFailGetNineteenthCenturyYear() {
        // given
        String input1 = "";
        String input2 = "912";
        String input3 = "null";

        String expectedMsg = "Provided year code is empty or is too long";

        // when
        Throwable exception1 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> parser.getYearNineteenthCentury(input1));
        Throwable exception2 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> parser.getYearNineteenthCentury(input2));
        Throwable exception3 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> parser.getYearNineteenthCentury(input3));

        // then
        Assertions.assertEquals(expectedMsg, exception1.getMessage());
        Assertions.assertEquals(expectedMsg, exception2.getMessage());
        Assertions.assertEquals(expectedMsg, exception3.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when given argument is null or too long for TwentyCentury year")
    void shouldFailGetTwentyCenturyYear() {
        // given
        String input1 = "";
        String input2 = "912";
        String input3 = "null";

        String expectedMsg = "Provided year code is empty or is too long";

        // when
        Throwable exception1 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> parser.getYearTwentyCentury(input1));
        Throwable exception2 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> parser.getYearTwentyCentury(input2));
        Throwable exception3 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> parser.getYearTwentyCentury(input3));

        // then
        Assertions.assertEquals(expectedMsg, exception1.getMessage());
        Assertions.assertEquals(expectedMsg, exception2.getMessage());
        Assertions.assertEquals(expectedMsg, exception3.getMessage());
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should parse properly string value of month to integer value")
    void shouldParseGivenStringToMonthValue(int expected, String monthValue) {
        // given, when
        int resultValue = parser.getMonth(monthValue);
        // then
        Assertions.assertEquals(expected, resultValue);

    }

    public static Stream<Arguments> shouldParseGivenStringToMonthValue() {
        return Stream.of(
                Arguments.of(1, "1"),
                Arguments.of(2, "2"),
                Arguments.of(3, "3"),
                Arguments.of(4, "4"),
                Arguments.of(5, "5"),
                Arguments.of(6, "6"),
                Arguments.of(7, "7"),
                Arguments.of(8, "8"),
                Arguments.of(9, "9"),
                Arguments.of(10, "10"),
                Arguments.of(11, "11"),
                Arguments.of(12, "12")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should parse properly string value of day code to integer")
    void shouldParseGivenStringToDayValue(int expected, String dayValue) {
        // given, when
        int resultDay = parser.getDay(dayValue);
        // then
        Assertions.assertEquals(expected, resultDay);
    }

    public static Stream<Arguments> shouldParseGivenStringToDayValue() {
        return Stream.of(
                Arguments.of(2, "02"),
                Arguments.of(19, "19"),
                Arguments.of(22, "22"),
                Arguments.of(10, "10"),
                Arguments.of(30, "30"),
                Arguments.of(25, "25"),
                Arguments.of(9, "9"),
                Arguments.of(1, "1"),
                Arguments.of(17, "17")
        );
    }
}