package pl.validator.services;

public interface JsonToFileService {
    void saveToFile(String jsonLine, String location, String fileName);
}
