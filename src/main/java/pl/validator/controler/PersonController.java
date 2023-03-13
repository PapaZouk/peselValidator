package pl.validator.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.validator.model.Person;
import pl.validator.repository.PersonRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

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
}
