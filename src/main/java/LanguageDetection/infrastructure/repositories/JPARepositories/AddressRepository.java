package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.infrastructure.repositories.JPARepositories.assemblers.PersonDomainDataAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository {

	@Autowired
	AddressJpaRepository addressJpaRepository;
	@Autowired
	PersonDomainDataAssembler addressAssembler;
}