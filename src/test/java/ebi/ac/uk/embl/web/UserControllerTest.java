package ebi.ac.uk.embl.web;

import ebi.ac.uk.embl.domain.Role;
import ebi.ac.uk.embl.domain.User;
import ebi.ac.uk.embl.dto.LoginDto;
import ebi.ac.uk.embl.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/13/2021.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private LoginDto signupDto = new LoginDto("larry", "1234", "larry", "miller");
    private User user = new User(signupDto.getUsername(), signupDto.getPassword(), new Role(),
            signupDto.getFirstName(), signupDto.getLastName());

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtRequestHelper jwtRequestHelper;

    @MockBean
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void signIn() {
        passwordEncoder.encode("Aa135642");
        restTemplate.postForEntity("/users/signin", new LoginDto("admin", "Aa135642"), Void.class);
        verify(this.service).signin("admin", "Aa135642");
    }

    @Test
    public void signUp() {
        when(service.signup(signupDto.getUsername(), signupDto.getPassword(), signupDto.getFirstName(),
                signupDto.getLastName())).thenReturn(Optional.of(user));

        ResponseEntity<User> response = restTemplate.exchange("/users/signup",
                POST,
                new HttpEntity(signupDto, jwtRequestHelper.withRole("ROLE_ADMIN")),
                User.class);

        assertThat(response.getStatusCode().value(), is(201));
        assertThat(response.getBody().getUsername(), is(user.getUsername()));
        assertThat(response.getBody().getFirstName(), is(user.getFirstName()));
        assertThat(response.getBody().getLastName(), is(user.getLastName()));
        assertThat(response.getBody().getRoles().size(), is(user.getRoles().size()));
    }

    @Test
    public void signUpUnauthorized() {

        ResponseEntity<User> response = restTemplate.exchange("/users/signup",
                POST,
                new HttpEntity(signupDto, jwtRequestHelper.withRole("ROLE_SR")),
                User.class);

        assertThat(response.getStatusCode().value(), is(403));
    }

    @Test
    public void getAllUsers() {
        when(service.getAll()).thenReturn(Arrays.asList(user));

        ResponseEntity<List<User>> response = restTemplate.exchange("/users",
                GET,
                new HttpEntity(jwtRequestHelper.withRole("ROLE_ADMIN")),
                new ParameterizedTypeReference<List<User>>() {
                });

        assertThat(response.getStatusCode().value(), is(200));
        assertThat(response.getBody().get(0).getUsername(), is(user.getUsername()));
    }
}