package pl.validator.services;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class JsonToFileServiceImpl implements JsonToFileService{

    @Override
    public void saveToFile(String jsonLine, String location, String fileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(getPath(location, fileName))) {
            writer.write(jsonLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Path getPath(String location, String fileName) {
        String file = String.format("/%s.json", fileName);
//        String fileName = "/generatedJsonFile.json";
        return Path.of(location + file);
    }
}
