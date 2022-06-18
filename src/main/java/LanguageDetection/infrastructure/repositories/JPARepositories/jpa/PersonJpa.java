package LanguageDetection.infrastructure.repositories.JPARepositories.jpa;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import LanguageDetection.domain.model.ValueObjects.PersonId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="persons")
public class PersonJpa {
    @Id
    @Embedded
    @Getter
    private PersonId id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    // if only one address is required, two alternatives are possible:
    //@Convert(converter = AddressConverter.class)
    //or
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "address_id", referencedColumnName = "id")
    //private AddressJpa address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressJpa> addresses;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleJpa> roles = new HashSet<>();

    public PersonJpa(long id, String firstName, String lastName, String email, String username, String password) {

        this.id = new PersonId(id);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;

        addresses = new ArrayList<AddressJpa>();
    }

    public PersonJpa(PersonId id, String firstName, String lastName, String email, String username, String password) {
        this.id = id;

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;

        addresses = new ArrayList<AddressJpa>();
    }

    public String toString() {
        return "Person {" +
                "id='" + id.toString() + '\'' +
                '}';
    }

    public Set<RoleJpa> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleJpa> roles) {
        this.roles = roles;
    }

}
