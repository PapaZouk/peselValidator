package pl.validator.services;

import org.springframework.stereotype.Service;
import pl.validator.model.ValidationResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class JsonFileParserImpl implements JsonFileParser{
    @Override
    public ValidationResult parseJsonFile(Path path) {
        if (!path.toAbsolutePath().toFile().exists()) {
            System.err.println("Provided path does not exist!");
            throw new RuntimeException("Provided path does not exist!");
        } else {
            return readValidationResult(path);
        }
    }

    private ValidationResult readValidationResult(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = null;
            while (reader.ready()) {
                line = reader.readLine();
            }
            return buildValidationResult(line);
        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException("Unable to parse files: " + e.getMessage());
        }
    }

    private ValidationResult buildValidationResult(String line) {
        String[] lineSplit = line
                .replaceAll("\\{", "")
                .replaceAll("}", "")
                .replaceAll("\"", "")
                .split(",");
        TreeMap<String, String> map = Arrays.stream(lineSplit)
                .map(body -> body.split(":"))
                .collect(Collectors.toMap(
                        key -> key[0],
                        value -> value[1].trim(),
                        (s, s2) -> s2,
                        TreeMap::new
                ));

        return ValidationResult.Builder.builder()
                .pesel(map.get("pesel"))
                .isValid(Boolean.parseBoolean(map.get("validationStatus")))
                .checkSum(Integer.valueOf(map.get("checksum")))
                .dateOfBirth(LocalDate.parse(map.get("dateOfBirth")))
                .gender(map.get("gender"))
                .build();
    }
}
