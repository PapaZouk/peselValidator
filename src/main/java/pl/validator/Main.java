package pl.validator;

import pl.validator.enums.PeselGeneratorType;
import pl.validator.model.ValidationResult;
import pl.validator.services.*;

public class Main {

    public static void main(String[] args) {

        PeselGenerator generator1 = PeselGeneratorFactory.build(PeselGeneratorType.TWENTIETH_CENTURY);
        PeselGenerator generator2 = PeselGeneratorFactory.build(PeselGeneratorType.TWENTY_FIRST_CENTURY);
        PeselGenerator generator3 = PeselGeneratorFactory.build(PeselGeneratorType.NINETEENTH_CENTURY);

        PersonParser parser = new PersonParserImpl();
        JsonParser jsonParser = new JsonParserImpl();

        JsonReadingService jsonReadingService = new JsonReadingServiceImpl();
        String path = "G:\\Java\\PESELvalidator\\src\\main\\java\\pl\\validator\\jsonFiles\\generatedJsonFile.json";
        ValidationResult validationResult = jsonReadingService.readJsonPesel(path);
        System.out.println(validationResult);
    }

}
