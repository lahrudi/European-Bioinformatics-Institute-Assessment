package ebi.ac.uk.embl.service;

import ebi.ac.uk.embl.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/14/2021.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {
    @Autowired
    private UserService service;

    @Test
    public void signUp() {
        Optional<User> user = service.signup("dummyUsername", "dummyPassword", "john", "doe");
        assertThat(user.get().getPassword(), not("dummyPassword"));
        System.out.println("Encoded Password = " + user.get().getPassword());
    }
}