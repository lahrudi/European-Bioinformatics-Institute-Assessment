package ebi.ac.uk.embl.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/12/2021.
 */
@Entity
@Table(name = "security_role")
@Getter
@Setter
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    @Id
    @NonNull
    @SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ_ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ROLE_SEQ")
    private Long id;

    @Column(name = "role_name")
    @NonNull
    private String roleName;

    @Column(name = "description")
    private String description;

    @Override
    public String getAuthority() {
        return roleName;
    }

}