package pl.validator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class ValidationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idValidationResult;

    @Column
    private String pesel;
    @Column(name = "is_valid")
    private boolean isValid;
    @Column(name = "check_sum")
    private Integer checkSum;
    @DateTimeFormat
    @Column(name = "birthday")
    private LocalDate dateOfBirth;
    @Column
    private String gender;


    public ValidationResult(Builder builder) {
        this.pesel = builder.pesel;
        this.isValid = builder.isValid;
        this.checkSum = builder.checkSum;
        this.dateOfBirth = builder.dateOfBirth;
        this.gender = builder.gender;
    }


    public static class Builder {
        private String pesel;
        private boolean isValid;
        private Integer checkSum;
        private LocalDate dateOfBirth;
        private String gender;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder pesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public Builder isValid(boolean isValid) {
            this.isValid = isValid;
            return this;
        }

        public Builder checkSum(Integer checkSum) {
            this.checkSum = checkSum;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public ValidationResult build() {
            return new ValidationResult(this);
        }

    }
}
