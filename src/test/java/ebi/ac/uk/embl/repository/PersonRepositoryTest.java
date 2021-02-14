package ebi.ac.uk.embl.repository;

import ebi.ac.uk.embl.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/14/2021.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository repository;

    @Test
    public void testFindByUsername() {
        List<Person> user = repository.findAll();
        assertFalse(user.isEmpty());
    }
}