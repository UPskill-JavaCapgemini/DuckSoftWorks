package LanguageDetection.infrastructure.repositories;

import LanguageDetection.infrastructure.datamodel.assemblers.TaskDomainDataAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {


	@Autowired
	TaskDomainDataAssembler exampleAssembler;


}