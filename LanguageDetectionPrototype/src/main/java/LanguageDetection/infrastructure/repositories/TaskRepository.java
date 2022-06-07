package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.datamodel.TaskJpa;
import LanguageDetection.infrastructure.datamodel.DataAssemblers.TaskDomainDataAssembler;
import LanguageDetection.infrastructure.repositories.JPARepositories.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;

@Repository
public class TaskRepository {


	@Autowired
	TaskDomainDataAssembler taskAssembler;


	@Autowired
	TaskJpaRepository taskJpaRepository;


	public Task save( Task task ) throws MalformedURLException {
		TaskJpa taskJpa = taskAssembler.toData(task);

		TaskJpa savedTaskJpa = taskJpaRepository.save( taskJpa );

		return taskAssembler.toDomain(savedTaskJpa);
	}
}
