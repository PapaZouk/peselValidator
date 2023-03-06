package pl.validator.services;

import pl.validator.model.Person;
import pl.validator.model.Sex;

import java.util.Optional;

public class PersonParserImpl implements PersonParser {

    @Override
    public Optional<Person> getPerson(String pesel) {

        int year = getMonth(pesel.substring(2, 4)) > 12
                ? (getMonth(pesel.substring(2, 4)) > 80 ? getYearNineteenthCentury(pesel.substring(0, 2)) : getYearMillenium(pesel.substring(0, 2)))
                : getYear(pesel.substring(0, 2));
        int month = year < 2000
                ? (year < 1900 ? getMonthNineteenthCentury(pesel.substring(2, 4)) : getMonth(pesel.substring(2, 4)))
                : getMonthMillenium(pesel.substring(2, 4));
        int day = getDay(pesel.substring(4, 6));
        Sex sex = getSex(pesel.substring(9, 10));

        return Optional.of(new Person(pesel, year, month, day, sex));
    }

    private int getYearNineteenthCentury(String yearCode) {
        return 1800 + Integer.parseInt(yearCode);
    }


    private int getYearMillenium(String yearCode) {
        return 2000 + Integer.parseInt(yearCode);
    }

    private int getMonthNineteenthCentury(String monthCode) {
        return Integer.parseInt(monthCode) - 80;
    }

    private int getMonthMillenium(String monthCode) {
        return Integer.parseInt(monthCode) - 20;
    }

    private Sex getSex(String sexCode) {
        return Integer.parseInt(sexCode) % 2 == 0 ? Sex.FEMALE : Sex.MALE;
    }

    private int getYear(String yearCode) {
        return 1900 + Integer.parseInt(yearCode);

    }

    private int getMonth(String monthCode) {
        return Integer.parseInt(monthCode);
    }

    private int getDay(String dayCode) {
        return Integer.parseInt(dayCode);
    }
}
