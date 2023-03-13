package pl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.validator.repository.PeselRepository;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    PeselRepository repository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        repository.addNewPesel(9, 10);
    }
}
