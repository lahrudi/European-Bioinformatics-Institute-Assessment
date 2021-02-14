package ebi.ac.uk.embl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/12/2021.
 */

@Entity
@Table(name = "security_user")
@Getter
@Setter
public class User implements Serializable {
    @Id
    @NonNull
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ_ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
    @Column(name = "id")
    private Long id;

    public User(String username, String password, Role role, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.roles = Arrays.asList(role);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "password")
    @NonNull
    @JsonIgnore
    private String password;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    /**
     * Default Constructor.
     */
    protected User() {
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))


    private List<Role> roles;

}
