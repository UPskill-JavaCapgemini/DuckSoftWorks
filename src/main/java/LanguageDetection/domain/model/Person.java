package LanguageDetection.domain.model;

import LanguageDetection.application.DTO.AddressDTO;
import LanguageDetection.domain.model.ValueObjects.PersonId;
import LanguageDetection.domain.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString

public class Person {
    @Getter
    private final PersonId id;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String email;
    @Setter
    @Getter
    private Set<Role> roles = new HashSet<Role>();
    @Setter
    private List<Address> addresses = new ArrayList<Address>();

    public Person(long id, String firstName, String lastName, String email, String username, String password) {
        this.id = new PersonId(id);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Person(PersonId id, String firstName, String lastName, String email, String username, String password) {
        this.id = id;

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Person(PersonId id, String firstName, String lastName) {
        this.id = id;

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean addAddress(String street, String city, String postalCode, String countryCode) {
        Address newAddress = new Address(street, city, postalCode, countryCode);

        return addresses.add(newAddress);
    }

    public List<AddressDTO> getAddresses() {
        List<AddressDTO> addressesDTO = new ArrayList<AddressDTO>();
        for (Address address : addresses) {
            AddressDTO addressDTO = new AddressDTO(address.getStreet(), address.getCity(), address.getPostalCode(), address.getCountryCode());

            addressesDTO.add(addressDTO);
        }
        return addressesDTO;
    }
}
