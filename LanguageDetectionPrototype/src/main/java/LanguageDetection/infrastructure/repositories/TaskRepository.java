package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ITask;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.repositories.JPARepositories.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


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


    public List<Task> findAllTasks() {
		Iterable<Task> listAllTasks= taskJpaRepository.findAll();

		return (List<Task>) listAllTasks;
    }

	public List<Task> findByStatusContaining(Task.CurrentStatus st) {
		Iterable<Task> listTasksByStatus = taskJpaRepository.findByCurrentStatusLike(st);

		return (List<Task>) listTasksByStatus;
	}

	public List<Task> findByCategoryContaining(Category catName) {
		Iterable<Task> listTasksByCategory = taskJpaRepository.findTaskByCategoryLike(catName);

		return (List<Task>) listTasksByCategory;
	}

	public List<Task> findByStatusAndByCategoryContaining(Task.CurrentStatus status, Category category) {
		Iterable<Task> listTasksByStatusAndByCategoryContaining = taskJpaRepository.findTaskByCategoryLikeAndCurrentStatusLike(category, status);

		return (List<Task>) listTasksByStatusAndByCategoryContaining;
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
