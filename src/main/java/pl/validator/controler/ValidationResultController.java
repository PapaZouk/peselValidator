package pl.validator.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.validator.model.ValidationResult;
import pl.validator.repository.ValidationResultRepository;
import pl.validator.services.JsonParserImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ValidationResultController {

    private final ValidationResultRepository validationResultRepository;

    @Autowired
    private final JsonParserImpl jsonParser;

    @PostMapping("/validations")
    public ValidationResult addValidationResult(@RequestBody ValidationResult validationResult) {
        return validationResultRepository.save(validationResult);
    }

    @GetMapping("/validations/getValidation")
    public Optional<ValidationResult> getValidationResult(@RequestParam("id") Long idValidationResult) {
        Optional<ValidationResult> validationResult = validationResultRepository.findById(idValidationResult);
        String location = "G:\\Java\\PESELvalidator\\src\\main\\java\\pl\\validator\\jsonFiles";
        jsonParser.parseValidationResult(validationResult.get(), location, validationResult.get().getPesel());
        return validationResult;
    }

    @GetMapping("/validations")
    public List<ValidationResult> getAllValidationResults() {
        return validationResultRepository.findAll();
    }

    @DeleteMapping("/validations")
    void deleteValidationResult(@RequestParam(name = "id") Long id) {
        validationResultRepository.deleteById(id);
    }
}
