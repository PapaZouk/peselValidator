package pl.validator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.validator.model.ValidationResult;

import java.util.List;
import java.util.Optional;

public interface ValidationResultRepository extends JpaRepository<ValidationResult, Long> {

    Optional<ValidationResult> findById(Long id);

    @Override
    List<ValidationResult> findAll();
}
