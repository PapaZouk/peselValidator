package pl.validator.services;

import pl.validator.model.Person;
import pl.validator.model.ValidationResult;

public interface JsonParser {
    void parsePerson(Person person, String location, String fileName);

    void parseValidationResult(ValidationResult validationResult, String location, String fileName);
}
