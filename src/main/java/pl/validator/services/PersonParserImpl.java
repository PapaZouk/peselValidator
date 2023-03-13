package pl.validator.services;

import org.springframework.stereotype.Service;
import pl.validator.model.Person;
import pl.validator.model.Gender;

import java.util.Optional;

@Service
public class PersonParserImpl implements PersonParser {

    @Override
    public Optional<Person> getPerson(String pesel) {

        int year = parseYear(pesel);
        int month = parseMonth(pesel, year);
        int day = getDay(pesel.substring(4, 6));
        Gender gender = getSex(pesel.substring(9, 10));

        return Optional.of(new Person(pesel, year, month, day, gender));
    }

    protected int parseYear(String pesel) {
        return getMonth(pesel.substring(2, 4)) > 12
                ? (getMonth(pesel.substring(2, 4)) > 80 ? getYearNineteenthCentury(pesel.substring(0, 2)) : getYearTwentyFirstCentury(pesel.substring(0, 2)))
                : getYearTwentyCentury(pesel.substring(0, 2));
    }

    protected int parseMonth(String pesel, int year) {
        return year < 2000
                ? (year < 1900 ? getMonthNineteenthCentury(pesel.substring(2, 4)) : getMonth(pesel.substring(2, 4)))
                : getMonthMillenium(pesel.substring(2, 4));
    }

    protected int getYearNineteenthCentury(String yearCode) {
        if (yearCode.isEmpty() || yearCode.length() > 2) {
            throw new IllegalArgumentException("Provided year code is empty or is too long");
        }
        return 1800 + Integer.parseInt(yearCode);
    }


    protected int getYearTwentyCentury(String yearCode) {
        if (yearCode.isEmpty() || yearCode.length() > 2) {
            throw new IllegalArgumentException("Provided year code is empty or is too long");
        }
        return 1900 + Integer.parseInt(yearCode);

    }

    protected int getYearTwentyFirstCentury(String yearCode) {
        if (yearCode.isEmpty() || yearCode.length() > 2) {
            throw new IllegalArgumentException("Provided year code is empty or is too long");
        }
        return 2000 + Integer.parseInt(yearCode);
    }

    protected int getMonthNineteenthCentury(String monthCode) {
        return Integer.parseInt(monthCode) - 80;
    }

    protected int getMonthMillenium(String monthCode) {
        return Integer.parseInt(monthCode) - 20;
    }

    protected Gender getSex(String sexCode) {
        return Integer.parseInt(sexCode) % 2 == 0 ? Gender.FEMALE : Gender.MALE;
    }

    protected int getMonth(String monthCode) {
        return Integer.parseInt(monthCode);
    }

    protected int getDay(String dayCode) {
        return Integer.parseInt(dayCode);
    }
}
