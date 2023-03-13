package pl.validator.model;

import java.time.LocalDate;

public class ValidationResult {
    private String pesel;
    private boolean isValid;
    private Integer checkSum;
    private LocalDate dateOfBirth;
    private String gender;

    @Override
    public String toString() {
        return "ValidationResult{" +
                "pesel='" + pesel + '\'' +
                ", isValid=" + isValid +
                ", checkSum=" + checkSum +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                '}';
    }

    public ValidationResult (Builder builder) {
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

        public static Builder builder() {
            return new Builder();
        }
        private Builder() {}

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
