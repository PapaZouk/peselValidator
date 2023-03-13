package pl.validator.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import pl.validator.model.Person;
import pl.validator.services.PersonParserImpl;
import pl.validator.services.PeselGeneratorTwentieth;

import java.util.List;

@Repository
public class PeselRepository  {

    @Autowired
    private EntityManager manager;

    @Autowired
    private PeselGeneratorTwentieth factory;

    @Autowired
    private PersonParserImpl personParser;


    @Transactional
    public void addNewPesel(@RequestParam(name = "beginning") int yearStart, @RequestParam("end") int yearEnd) {
        String generatedPesel = factory.generatePesel(yearStart, yearEnd);
        Person person = personParser.getPerson(generatedPesel).get();
        manager.persist(person);
    }

    @Transactional
    public void clearAllPesels(@RequestParam(name = "id_person") Long id) {
        Person person = manager.find(Person.class, id);
        manager.remove(person);
    }

    @Transactional
    public List findAll() {
        System.out.println("Calling findAll");
        Long id = 1L;
        System.out.println(manager.find(Person.class, id));
        return manager.createQuery("SELECT pesel FROM person").getResultList();
    }
}
