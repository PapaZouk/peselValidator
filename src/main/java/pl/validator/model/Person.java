package pl.validator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_person", nullable = false)
    private Long idPerson;

    @Column
    private String pesel;
    @DateTimeFormat
    @Column(name = "dateOfBirth")
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public Person(String pesel, Integer year, Integer month, Integer day, Gender gender) {
        this.pesel = pesel;
        this.birthday = LocalDate.of(year, month, day);
        this.gender = gender;
    }

    public String getPesel() {
        return pesel;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getDayOfWeek() {
        return birthday.getDayOfWeek().name();
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return String.format("Person(birthday: %s, %s, sex: %-7s, PESEL: %s)",
                getDayOfWeek() ,getBirthday(), getGender(), getPesel());
    }
}
