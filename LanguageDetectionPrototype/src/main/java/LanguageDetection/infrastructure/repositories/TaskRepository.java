package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.example.Task;
import LanguageDetection.infrastructure.datamodel.TaskJpa;
import LanguageDetection.infrastructure.datamodel.assemblers.TaskDomainDataAssembler;
import LanguageDetection.infrastructure.repositories.jpa.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

	@Autowired
	TaskJpaRepository exampleJpaRepository;

	@Autowired
	TaskDomainDataAssembler exampleAssembler;


}