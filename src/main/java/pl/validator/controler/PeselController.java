package pl.validator.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.validator.model.Person;
import pl.validator.repository.PeselRepository;
import pl.validator.services.PeselGeneratorTwentieth;

import java.util.List;

@RestController
public class PeselController {

    @Autowired
    public PeselRepository repository;

    @Autowired
    public PeselGeneratorTwentieth generator;

    @GetMapping("/pesels")
    public void listPersons() {
        System.out.println("Hello!");
    }

    @PostMapping("/pesels")
    public void handleFileUpload(@RequestParam int yearStart, @RequestParam int yearEnd) {
        System.out.println("Adding new pesel");
        repository.addNewPesel(yearStart, yearEnd);
    }

    @DeleteMapping("/pesels")
    public void clearPesels(@RequestParam(name = "id_person") Long id) {
        System.out.println("Deleting person with id=" + id);
        repository.clearAllPesels(id);
    }
}
