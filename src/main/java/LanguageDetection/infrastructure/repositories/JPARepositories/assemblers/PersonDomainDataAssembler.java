package LanguageDetection.infrastructure.repositories.JPARepositories.assemblers;

import LanguageDetection.application.DTO.AddressDTO;
import LanguageDetection.domain.entities.Person;
import LanguageDetection.domain.entities.Role;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.AddressJpa;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.PersonJpa;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.RoleJpa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonDomainDataAssembler {

	public PersonJpa toData(Person person ) {
		PersonJpa personJpa = new PersonJpa( person.getId(), person.getFirstName(), person.getLastName(), person.getEmail(), person.getUsername(), person.getPassword());

		List<AddressDTO> addressesDTO = person.getAddresses();
		ArrayList<AddressJpa> addressesJpa = new ArrayList<AddressJpa>();

		for( AddressDTO address : addressesDTO ) {
			AddressJpa addressJpa = new AddressJpa(address.getStreet(), address.getCity(), address.getPostalCode(), address.getCountryCode(), personJpa);
			addressesJpa.add(addressJpa);
		}
		personJpa.setAddresses(addressesJpa);

		Set<Role> rolesDTO = person.getRoles();
		Set<RoleJpa> rolesJpa = new HashSet<RoleJpa>();

		for( Role role : rolesDTO ) {
			RoleJpa roleJpa = new RoleJpa(role.getId(),role.getName());
			rolesJpa.add(roleJpa);
		}
		personJpa.setRoles(rolesJpa);


		return personJpa;
	}

	public Person toDomain( PersonJpa personJpa ) {
		Person person = new Person( personJpa.getId(), personJpa.getFirstName(), personJpa.getLastName(), personJpa.getEmail(), personJpa.getUsername(), personJpa.getPassword() );

		Set<Role> roles = new HashSet<Role>();

		for( RoleJpa roleJpa : personJpa.getRoles()) {
			Role role = new Role( roleJpa.getId(), roleJpa.getName());
			roles.add(role);
		}
		person.setRoles(roles);
		return person;
	}
}