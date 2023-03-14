package pl.validator.services;

import org.springframework.stereotype.Service;
import pl.validator.model.Person;
import pl.validator.model.ValidationResult;

@Service
public class JsonParserImpl implements JsonParser {
    
    private static final PeselValidator validator = new PeselValidatorImpl();

    private static final JsonToFileService jsonToFileService = new JsonToFileServiceImpl();

    @Override
    public void parsePerson(Person person, String location, String fileName) {
        jsonToFileService.saveToFile(prepareJsonPersonBody(person), location, fileName);
    }

    @Override
    public void parseValidationResult(ValidationResult validationResult, String location, String fileName) {
        jsonToFileService.saveToFile(prepareJsonValidationResultBody(validationResult), location, fileName);
    }

    private String prepareJsonValidationResultBody(ValidationResult validationResult) {
        StringBuilder body = new StringBuilder();
        body.append("{");
        body.append("\"pesel\": \"" + validationResult.getPesel() + "\",");
        body.append("\"checksum\": \"" + validationResult.getCheckSum() + "\",");
        body.append("\"dateOfBirth\": \"" + validationResult.getDateOfBirth() + "\",");
        body.append("\"gender\": \"" + validationResult.getGender() + "\",");
        body.append("\"valid\": \"" + validationResult.isValid() + "\"");
        body.append("}");
        return body.toString();
    }


    private String prepareJsonPersonBody(Person person) {
        StringBuilder body = new StringBuilder();
        boolean validationStatus = validator.validatePESEL(person.getPesel());
        int checkSum = validator.calculate(validator.getPeselNumbers(person.getPesel()));
        body.append("{");
        body.append("\"pesel\": \"" + person.getPesel() + "\",");
        body.append("\"validationStatus\": \"" + validationStatus + "\",");
        body.append("\"checksum\": \"" + checkSum + "\",");
        body.append("\"dateOfBirth\": \"" + person.getBirthday().toString() + "\",");
        body.append("\"gender\": \"" + person.getGender().name() + "\"");
        body.append("}");
        return body.toString();
    }


}
