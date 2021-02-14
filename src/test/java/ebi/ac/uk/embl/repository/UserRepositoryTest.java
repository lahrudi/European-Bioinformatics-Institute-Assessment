package ebi.ac.uk.embl.repository;

import ebi.ac.uk.embl.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/14/2021.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testFindByUsername() {
        Optional<User> user = repository.findByUsername("admin");
        assertTrue(user.isPresent());

        user = repository.findByUsername("nobody");
        assertFalse(user.isPresent());
    }
}