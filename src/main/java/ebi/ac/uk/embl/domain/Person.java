package ebi.ac.uk.embl.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/12/2021.
 */
@Entity
@Table(name = "Person")
@Getter
@Setter
public class Person implements Serializable {
    @Id
    @NonNull
    @SequenceGenerator(name = "PERSON_SEQ", sequenceName = "PERSON_SEQ_ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PERSON_SEQ")
    private Long id;

    @NonNull
    @JsonProperty("first_name")
    private String firstName;

    @NonNull
    @JsonProperty("last_name")
    private String lastName;

    @NonNull
    private Integer age;

    @JsonProperty("favourite_colour")
    private String favouriteColour;

    @JsonProperty("nationality")
    private String nationality;

    // No-args constructor
    public Person() {
    }

    // Constructor with params
    public Person(String firstName,
                  String lastName,
                  Integer age,
                  String favouriteColour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
    }

    // Constructor with params
    public Person(String firstName,
                  String lastName,
                  Integer age,
                  String favouriteColour,
                  String nationality
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
        this.nationality = nationality;
    }

}
