package pl.validator.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {

    public static Stream<Arguments> givenFalseDateWhenIsValidThenAssertEquals() {
        return Stream.of(
                Arguments.of(2012, 21, 12),
                Arguments.of(1890, 5, 52),
                Arguments.of(4, 4, 96),
                Arguments.of(1992, 17, 41),
                Arguments.of(2026, 12, 94),
                Arguments.of(0, 0, 0),
                Arguments.of(0, 1, 0)
        );
    }

    public static Stream<Arguments> givenDateWhenIsValidThenAssertEquals() {
        return Stream.of(
                Arguments.of(2012, 11, 12),
                Arguments.of(1890, 5, 22),
                Arguments.of(1984, 4, 16),
                Arguments.of(1992, 7, 19),
                Arguments.of(2026, 12, 24),
                Arguments.of(1905, 10, 11),
                Arguments.of(1954, 3, 29)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should return TRUE if date is valid")
    void givenDateWhenIsValidThenAssertEquals(int year, int month, int dayOfMonth) {
        // given, when
        boolean expected = true;
        boolean result = DateValidator.isValid(year, month, dayOfMonth);
        // then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should return FALSE if date is not valid")
    void givenFalseDateWhenIsValidThenAssertEquals(int year, int month, int dayOfMonth) {
        // given, when
        boolean expected = false;
        boolean result = DateValidator.isValid(year, month, dayOfMonth);
        // then
        Assertions.assertEquals(expected, result);
    }
}