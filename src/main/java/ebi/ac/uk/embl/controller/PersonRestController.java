package ebi.ac.uk.embl.controller;

import ebi.ac.uk.embl.domain.Person;
import ebi.ac.uk.embl.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/13/2021.
 */
@RestController
@RequestMapping("/api")
public class PersonRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRestController.class);
    private PersonService personService;

    @Autowired
    PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/persons")
    List<Person> all() {
        return personService.findAll();
    }

    @GetMapping("/person/{id}")
    Person one(@PathVariable Long id) {
        return personService.find(id);
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> addPerson(@RequestBody Map<String, ArrayList<Person>> person) {
        HttpHeaders responseHeaders = setResponseHeaders();

        if (person.get("person") != null) {
            for (Person newPerson : person.get("person")) {
                if (personService.addPerson(newPerson)) {
                    // you can count or return the list of added person
                    System.out.println("Added a new person");
                }
            }
        }
        return new ResponseEntity<>(returnSuccessfullyMessage(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatePerson", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<String> updatePerson(@RequestBody Map<String, ArrayList<Person>> persons) {
        HttpHeaders responseHeaders = setResponseHeaders();

        if (persons.get("person") != null) {
            for (Person person : persons.get("person")) {
                if (personService.updatePerson(person)) {
                    // you can count or return the list of updated person
                    System.out.println("Updated person".concat(person.getId().toString()));
                }
                ;
            }
        }

        return new ResponseEntity<>(returnSuccessfullyMessage(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<String> deletePerson(@RequestBody Map<String, ArrayList<Person>> persons) {
        HttpHeaders responseHeaders = setResponseHeaders();

        if (persons.get("person") != null) {
            for (Person deletePerson : persons.get("person")) {
                if (personService.deletePerson(deletePerson)) {
                    System.out.println("Deleted person");
                }
            }
        }

        return new ResponseEntity<>(returnSuccessfullyMessage(), responseHeaders, HttpStatus.OK);
    }

    private HttpHeaders setResponseHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        return responseHeaders;
    }

    private String returnSuccessfullyMessage() {
        String result = new StringBuilder("{\"message\":\"Successful!").append("\"}").toString();
        return result;
    }

}


