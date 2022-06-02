package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.datamodel.TaskJpa;
import LanguageDetection.infrastructure.datamodel.assemblers.TaskDomainDataAssembler;
import LanguageDetection.infrastructure.repositories.JPARepositories.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {


	@Autowired
	TaskDomainDataAssembler taskAssembler;


	@Autowired
	TaskJpaRepository taskJpaRepository;


	public Task save( Task task ) {
		TaskJpa countryJpa = taskAssembler.toData(task);

		TaskJpa savedCountryJpa = taskJpaRepository.save( countryJpa );

		return taskAssembler.toDomain(savedCountryJpa);
	}
}