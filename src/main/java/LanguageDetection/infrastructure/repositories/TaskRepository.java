package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ITaskRepository;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;

import LanguageDetection.infrastructure.repositories.JPARepositories.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TaskRepository implements ITaskRepository {


	@Autowired
	TaskJpaRepository taskJpaRepository;

	/**
	 * Handles the responsibility to call the repository and save one instance of Task
	 * @param task instance that needs to be saved on database
	 * @return task that was saved
	 */
	@Override
	public Task saveTask(Task task) {
		return taskJpaRepository.save(task);
	}

	/**
	 * Fetches all tasks that are stored in database
	 * @return List of all tasks present in database
	 */
    public List<Task> findAllTasks(long userId) {
		Iterable<Task> listAllTasks= taskJpaRepository.findAllByUserId(userId);

		return (List<Task>) listAllTasks;
    }

	/**
	 * Fetches all tasks that are stored in database with the status passed in argument
	 * @param st Task current status ENUM that wants to be searched in database
	 * @return List of all tasks present with the status passed
	 */
	public List<Task> findByStatusContaining(TaskStatus st, long userId) {
		Iterable<Task> listTasksByStatus = taskJpaRepository.findByCurrentStatusLikeAndUserId(st, userId);

		return (List<Task>) listTasksByStatus;
	}

	/**
	 * Fetches all tasks that are stored in database with the category name passed in argument
	 * @param category Category instance that wants to be searched in database
	 * @return List of all tasks present with the category passed
	 */
	public List<Task> findByCategoryNameContaining(Category category, long userId) {
		Iterable<Task> listTasksByCategory = taskJpaRepository.findTaskByCategoryLikeAndUserId(category, userId);
		return (List<Task>) listTasksByCategory;
	}

	/**
	 * Fetches all tasks that are stored in database with the category name and status passed in argument
	 * @param status Task current status ENUM that wants to be searched in database
	 * @param category Category instance that wants to be searched in database
	 * @return List of all tasks present with the category and status passed
	 */
	public List<Task> findByStatusAndByCategoryContaining(TaskStatus status, Category category, long userId) {
		Iterable<Task> listTasksByStatusAndByCategoryContaining = taskJpaRepository.findTaskByCategoryLikeAndCurrentStatusLikeAndUserId(category, status, userId);

		return (List<Task>) listTasksByStatusAndByCategoryContaining;
	}

	/**
	 * Fetches task on database with the corresponding id passed
	 * @param identity the id of a task
	 * @return task object if id is found in database
	 */
    public Optional<Task> findByTaskId(Long identity) {
		return taskJpaRepository.findById(identity);
    }

	@Override
	public boolean existsByUrlAndIsProcessing(InputUrl url, long userId) {
    	TaskStatus processing = TaskStatus.Processing;
		return taskJpaRepository.existsTaskByInputUrlAndCurrentStatusAndUserId(url,processing,userId);
	}
}
