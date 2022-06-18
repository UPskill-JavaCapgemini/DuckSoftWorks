package LanguageDetection.infrastructure.repositories.JPARepositories.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "addresses")
public class AddressJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String street;
    private String city;
    private String postalCode;

	//@Embedded
    //private Country country;
    // or
    //@ManyToOne()
    //@JoinColumn(name = "country_code", nullable=false)
    //because it is in a another aggregate, a foreign key constraint is not used
    private String countryCode; // this should be of type e.g. CountryId

    @ManyToOne()
    @JoinColumn(name = "person", nullable=false)
    //because it is in the same aggregate, a foreign key constraint is used
    private PersonJpa person;

    public AddressJpa(String street, String city, String postalCode, String countryCode, PersonJpa personJpa) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.countryCode = countryCode;

        this.person = personJpa;
    }
}
