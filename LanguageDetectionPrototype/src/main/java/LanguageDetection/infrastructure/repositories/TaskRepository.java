package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.example.Task;
import LanguageDetection.infrastructure.datamodel.TaskJpa;
import LanguageDetection.infrastructure.datamodel.assemblers.TaskDomainDataAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {


	@Autowired
	TaskDomainDataAssembler exampleAssembler;


}