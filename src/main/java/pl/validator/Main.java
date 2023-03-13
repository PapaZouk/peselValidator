package pl.validator;

import pl.validator.enums.PeselGeneratorType;
import pl.validator.model.Person;
import pl.validator.model.Sex;
import pl.validator.model.ValidationResult;
import pl.validator.services.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        PeselGenerator generator1 = PeselGeneratorFactory.build(PeselGeneratorType.TWENTIETH_CENTURY);
        PeselGenerator generator2 = PeselGeneratorFactory.build(PeselGeneratorType.TWENTY_FIRST_CENTURY);
        PeselGenerator generator3 = PeselGeneratorFactory.build(PeselGeneratorType.NINETEENTH_CENTURY);

        PersonParser parser = new PersonParserImpl();
        JsonParser jsonParser = new JsonParserImpl();

//        List<String> peselData = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            peselData.add(generator2.generatePesel(1,1,4,4,8,8,Sex.MALE));
//        }

//        peselData.forEach(pesel -> System.out.println(parser.getPerson(pesel).get()));
//        String pesel = generator1.generatePesel(9, 10);
//        Person person = parser.getPerson(pesel).get();
//
//        String jsonLocation = "G:\\StrefaKursow\\kursJSONXML\\walidatorPesel\\data";
//        jsonParser.parsePerson(person, jsonLocation);

        JsonReadingService jsonReadingService = new JsonReadingServiceImpl();
        String path = "G:\\Java\\PESELvalidator\\src\\main\\java\\pl\\validator\\jsonFiles\\generatedJsonFile.json";
        ValidationResult validationResult = jsonReadingService.readJsonPesel(path);
        System.out.println(validationResult);
    }

}
