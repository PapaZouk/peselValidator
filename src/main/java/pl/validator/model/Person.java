package pl.validator.model;

import java.time.LocalDate;

public class Person {

    private final String pesel;
    private final LocalDate birthday;
    private final Sex sex;

    public Person(String pesel, Integer year, Integer month, Integer day, Sex sex) {
        this.pesel = pesel;
        this.birthday = LocalDate.of(year, month, day);
        this.sex = sex;
    }

    public String getPesel() {
        return pesel;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getDayOfWeek() {
        return birthday.getDayOfWeek().name();
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return String.format("Person(birthday: %s, %s, sex: %-7s, PESEL: %s)",
                getDayOfWeek() ,getBirthday(), getSex(), getPesel());
    }
}
