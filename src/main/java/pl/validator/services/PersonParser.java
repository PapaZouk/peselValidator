package pl.validator.services;

import pl.validator.model.Person;

import java.util.Optional;

public interface PersonParser {

    Optional<Person> getPerson(String pesel);

}
