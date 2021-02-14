package ebi.ac.uk.embl.repository;

import ebi.ac.uk.embl.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/12/2021.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById(Long id);

    List<Person> findAll();

    List<Person> findPersonByFirstName(String firstName);

    List<Person> findPersonByLastName(String lastName);

    Person findByFirstNameAndLastNameAndAge(String firstName, String lastName, Integer age);

    Person findPersonByFirstNameAndLastNameAndAgeAndFavouriteColour(String firstName, String lastName, Integer age, String favouriteColour);

}