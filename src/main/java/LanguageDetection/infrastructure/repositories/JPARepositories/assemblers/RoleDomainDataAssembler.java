package LanguageDetection.infrastructure.repositories.JPARepositories.assemblers;

import LanguageDetection.domain.entities.Role;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.RoleJpa;
import org.springframework.stereotype.Service;

@Service
public class RoleDomainDataAssembler {

	public RoleJpa toData(Role role ) {
		return new RoleJpa(role.getId(),role.getName());
	}

	public Role toDomain( RoleJpa roleJpa) {
		return new Role( roleJpa.getId(), roleJpa.getName() );
	}
}