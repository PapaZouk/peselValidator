package pl.validator.services;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonToFileServiceImpl implements JsonToFileService{

    @Override
    public void saveToFile(String jsonLine, String location) {
        try (BufferedWriter writer = Files.newBufferedWriter(getPath(location))) {
            writer.write(jsonLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Path getPath(String location) {
        String fileName = "/generatedJsonFile.json";
        return Path.of(location + fileName);
    }
}
