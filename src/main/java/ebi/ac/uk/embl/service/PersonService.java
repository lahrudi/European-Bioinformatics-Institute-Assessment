package ebi.ac.uk.embl.service;

import ebi.ac.uk.embl.exception.ResourceNotFoundException;
import ebi.ac.uk.embl.domain.Person;
import ebi.ac.uk.embl.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/12/2021.
 */
@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    @Autowired
    private PersonRepository personRepository;


    private Optional<Person> findOptionalById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return (person.isPresent()) ? person : Optional.empty();
    }

    private Person findById(Long id) {
        Optional<Person> person = findOptionalById(id);
        return (person.isPresent()) ? person.get() : null;
    }


    private Optional<Person> findOptionalUniquePerson(String firstName, String lastName, Integer age, String favouriteColour) {
        Person person = personRepository.findPersonByFirstNameAndLastNameAndAgeAndFavouriteColour(firstName, lastName, age, favouriteColour);
        return (person != null) ? Optional.of(person) : Optional.empty();
    }


    public Person findUniquePerson(String firstName, String lastName, Integer age, String favouriteColour) {
        Optional<Person> person = findOptionalUniquePerson(firstName, lastName, age, favouriteColour);
        return (person.isPresent()) ? person.get() : null;

    }


    public List<Person> allPeople() {
        List<Person> people = personRepository.findAll();
        return people;
    }

    public boolean addPerson(Person person) {
        boolean saveResult = false;
        try {
            Person existPerson = findUniquePerson(person.getFirstName(), person.getLastName(), person.getAge(), person.getFavouriteColour());
            if (existPerson == null) {
                personRepository.save(person);
                saveResult = true;
            }
        } catch (Exception e) {
            // I can raise a specific exception and propagate it
        }
        return saveResult;
    }


    public boolean updatePerson(Person person) {
        boolean saveResult = false;
        try {
            Person existPerson = findById(person.getId());
            if (existPerson != null) {
                personRepository.save(person);
                saveResult = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveResult;
    }

    public boolean deletePerson(Person person) {
        boolean saveResult = false;
        try {
            Person existPerson = findById(person.getId());
            if (existPerson != null) {
                personRepository.delete(person);
                saveResult = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveResult;
    }

    public Person find(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}