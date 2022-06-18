package LanguageDetection.infrastructure.repositories.JPARepositories.assemblers;

import LanguageDetection.domain.ValueObjects.PersonId;
import LanguageDetection.domain.entities.Group;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.GroupJpa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupDomainDataAssembler {

	public GroupJpa toData(Group group ) {
		GroupJpa groupJpa = new GroupJpa(group.getId(), group.getName());

		return groupJpa;
	}

	public Group toDomain( GroupJpa groupJpa ) {
		List<PersonId> adminsId = new ArrayList<PersonId>();

		Group group = new Group( groupJpa.getId(), groupJpa.getName(), adminsId );
		return group;
	}
}