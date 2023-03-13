package pl.validator.services;

import pl.validator.model.ValidationResult;

public interface JsonReadingService {
    ValidationResult readJsonPesel(String path);
}
