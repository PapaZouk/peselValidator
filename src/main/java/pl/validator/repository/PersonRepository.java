package pl.validator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.validator.model.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById(Long id);

    void deleteById(Long id);
}
