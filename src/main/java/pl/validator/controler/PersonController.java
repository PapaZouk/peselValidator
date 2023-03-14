package pl.validator.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import pl.validator.enums.PeselGeneratorType;
import pl.validator.model.Person;
import pl.validator.repository.PersonRepository;
import pl.validator.services.PersonParser;
import pl.validator.services.PeselGenerator;
import pl.validator.services.PeselGeneratorFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    PersonParser personParser;

    @Autowired
    PeselGenerator peselGenerator;

    @GetMapping("/persons")
    public Optional<Person> getPersonById(@RequestParam(name = "personId") Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (!optionalPerson.isPresent()) {
            System.err.println("No person founded with id: " + id);
            return Optional.empty();
        } else {
            return optionalPerson;
        }
    }

    @GetMapping("/persons/listAll")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping("/persons")
    public void addPerson(@RequestBody Person person) {
        personRepository.save(person);
    }

    @PostMapping("/persons/addRandom")
    public void addRandomPeople(@RequestParam("quantity") Integer quantity, @RequestParam("type") int typeNumber) {
        if (typeNumber == 0) {
            peselGenerator = PeselGeneratorFactory.build(PeselGeneratorType.NINETEENTH_CENTURY);
        } else if (typeNumber == 1) {
            peselGenerator = PeselGeneratorFactory.build(PeselGeneratorType.TWENTIETH_CENTURY);
        } else if (typeNumber == 2) {
            peselGenerator = PeselGeneratorFactory.build(PeselGeneratorType.TWENTY_FIRST_CENTURY);
        }
        for (int i = 0; i < quantity; i++) {
            String pesel = peselGenerator.generatePeselWithRandomDateAndGender();
            Optional<Person> person = personParser.getPerson(pesel);
            person.ifPresent(personRepository::save);
        }
    }

    @DeleteMapping("/persons")
    public void deletePerson(@RequestParam("idPerson") Long id) {
        personRepository.deleteById(id);
    }
}
