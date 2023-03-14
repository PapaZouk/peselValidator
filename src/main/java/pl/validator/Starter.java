package pl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.validator.model.Gender;
import pl.validator.model.Person;
import pl.validator.model.ValidationResult;
import pl.validator.repository.PersonRepository;
import pl.validator.repository.PeselRepository;
import pl.validator.repository.ValidationResultRepository;
import pl.validator.services.JsonToFileServiceImpl;

import java.time.LocalDate;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    PeselRepository peselRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ValidationResultRepository validationResultRepository;

    @Autowired
    JsonToFileServiceImpl jsonToFileService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        peselRepository.addNewPesel(9, 10);
        personRepository.save(new Person("88040105936", 1988, 04, 01, Gender.MALE));
        ValidationResult validationResult = ValidationResult.Builder.builder()
                .pesel("29312531234")
                .isValid(false)
                .checkSum(12)
                .dateOfBirth(LocalDate.of(2029, 11, 25))
                .gender("MALE")
                .build();
        validationResultRepository.save(validationResult);

    }
}
