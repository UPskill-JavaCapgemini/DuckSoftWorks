package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.ITask;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.repositories.JPARepositories.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TaskRepository implements ITask {


	@Autowired
	TaskJpaRepository taskJpaRepository;


//	public Task save( Task task ) throws MalformedURLException {
//		TaskJpa taskJpa = taskAssembler.toData(task);
//
//		TaskJpa savedTaskJpa = taskJpaRepository.save( taskJpa );
//
//		return taskAssembler.toDomain(savedTaskJpa);
//	}

	@Override
	public Task saveTask(Task task) {
		return taskJpaRepository.save(task);
	}


//	@Override
//	public boolean deletedById(Long id) {
//		Optional<Task> opTask = taskJpaRepository.findById(id);
//		if (opTask.isPresent()) {
//			taskJpaRepository.deleteById(id);
//			return true;
//		} else {
//			return false;
//		}
//	}


}
