package pl.validator.services;

import pl.validator.model.Person;

public class JsonParserImpl implements JsonParser {
    
    private static final PeselValidator validator = new PeselValidatorImpl();

    private static final JsonToFileService jsonToFileService = new JsonToFileServiceImpl();

    @Override
    public void parsePerson(Person person, String location) {
        jsonToFileService.saveToFile(prepareBody(person), location);
    }

    private String prepareBody(Person person) {
        StringBuilder body = new StringBuilder();
        boolean validationStatus = validator.validatePESEL(person.getPesel());
        int checkSum = validator.calculate(validator.getPeselNumbers(person.getPesel()));
        body.append("{");
        body.append("\"pesel\": \"" + person.getPesel() + "\",");
        body.append("\"validationStatus\": \"" + validationStatus + "\",");
        body.append("\"checksum\": \"" + checkSum + "\",");
        body.append("\"dateOfBirth\": \"" + person.getBirthday().toString() + "\",");
        body.append("\"gender\": \"" + person.getSex().name() + "\"");
        body.append("}");
        return body.toString();
    }
}
