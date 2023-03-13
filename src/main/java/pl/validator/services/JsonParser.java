package pl.validator.services;

import pl.validator.model.Person;

public interface JsonParser {
    void parsePerson(Person person, String location);

}
