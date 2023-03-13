package pl.validator.services;

import pl.validator.model.ValidationResult;

import java.nio.file.Path;

public interface JsonFileParser {
    ValidationResult parseJsonFile(Path path);
}
