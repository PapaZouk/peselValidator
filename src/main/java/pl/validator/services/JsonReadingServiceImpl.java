package pl.validator.services;

import pl.validator.model.ValidationResult;

import java.nio.file.Path;

public class JsonReadingServiceImpl implements JsonReadingService{

    private JsonFileParser jsonFileParser;

    public JsonReadingServiceImpl() {
        this.jsonFileParser = new JsonFileParserImpl();
    }

    @Override
    public ValidationResult readJsonPesel(String path) {
        return jsonFileParser.parseJsonFile(Path.of(path));
    }

}
