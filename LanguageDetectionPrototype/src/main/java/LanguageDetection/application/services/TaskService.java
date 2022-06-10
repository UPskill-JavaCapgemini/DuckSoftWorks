
package LanguageDetection.application.services;


import LanguageDetection.application.DTO.*;

import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewTaskInfoDTO;
import LanguageDetection.application.DTO.TaskStatusDTO;

import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.net.MalformedURLException;
import java.util.*;

import java.util.concurrent.*;


/**
 * Represents the task service responsible for handling all methods which interacts with a task.
 *
 * @author DuckSoftWorks Team
 */
@Slf4j
@Service
public class TaskService {


    private static final long CONSTANT_TO_MINUTES = 60000L;

    @Autowired
    TaskDomainDTOAssembler taskDomainDTOAssembler;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    BlackListService blackListService;

    @Autowired
    CategoryService categoryService;


    /**
     * Creates a new task with a NewTaskInfoDTO received by parameter.
     * Before task creation it is checked if URL from input is on BlackList or not.
     *
     * @param userInput the string containing the text within NewTaskInfoDTO.
     * @return TaskStatusDTO assembled by taskDomainDTOAssembler, with information of Status when created(Processing) or empty if url is on blacklist and unable to crate
     * @throws IOException thrown if URL is malformed
     */
    public Optional<TaskStatusDTO> createAndSaveTask(NewTaskInfoDTO userInput) throws IOException {

        NewBlackListInfoDTO newBlackListInfoDTO = new NewBlackListInfoDTO(userInput.getUrl());
        if (!blackListService.isBlackListed(newBlackListInfoDTO)) {
            Optional<Category> persistedCategory = findPersistedCategory(userInput);
            if (persistedCategory.isPresent()) {
                Task task = new Task(userInput.getUrl(), userInput.getTimeOut(), persistedCategory.get());
                Task taskRepo = taskRepository.saveTask(task);
                languageAnalysis(taskRepo);
                return Optional.of(taskDomainDTOAssembler.toDTO(taskRepo));
            }
        }
        return Optional.empty();
    }

    /**
     * Fetch all tasks found in database
     * @return List of TaskDTO with all information
     */
    public List<TaskDTO> findAllTasks() {
        List<Task> listAllTasks = taskRepository.findAllTasks();
        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listAllTasks) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }

        return taskDTOList;
    }

    /**
     * Fetch all tasks found in database with the status passed in StatusDTO instance
     * @param inputStatus StatusDTO object with string of status that want to be searched
     * @return List of TaskDTO with all information of task that has status the same as String inside StatusDTO instance
     */
    public List<TaskDTO> findByStatusContaining(StatusDTO inputStatus) {
        Task.CurrentStatus status = Task.CurrentStatus.valueOf(inputStatus.getStatus());
        List<Task> listTasksByStatus = taskRepository.findByStatusContaining(status);

        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listTasksByStatus) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }
        return taskDTOList;
    }

    /**
     * Fetch all tasks found in database with the category passed in CategoryNameDTO instance
     * @param catName CategoryNameDTO object with string of category name that want to be searched
     * @return List of TaskDTO with all information of task that has category name the same as String inside CategoryNameDTO instance
     */
    public List<TaskDTO> findByCategoryContaining(CategoryNameDTO catName) {
        Category category = new Category(catName.getCategoryName());
        List<Task> listTasksByCategory = taskRepository.findByCategoryContaining(category);

        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listTasksByCategory) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }

        return taskDTOList;
    }

    /**
     * Fetch all tasks found in database with the category and status passed in CategoryNameDTO instance and StatusDTO instance
     * @param inputStatus StatusDTO object with string of status that want to be searched
     * @param inputCategory CategoryNameDTO object with string of category name that want to be searched
     * @return List of TaskDTO with all information of task that has category name the same as String inside CategoryNameDTO instance and
     * status the same as string inside StatusDTO instance
     */
    public List<TaskDTO> findByStatusContainingAndCategoryContaining(StatusDTO inputStatus, CategoryNameDTO inputCategory) {
        Task.CurrentStatus status = Task.CurrentStatus.valueOf(inputStatus.getStatus());
        Category category = new Category(inputCategory.getCategoryName());

        List<Task> listTasksByStatusAndByCategory = taskRepository.findByStatusAndByCategoryContaining(status, category);

        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listTasksByStatusAndByCategory) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }
        return taskDTOList;
    }

    /**
     * Fetches if a category passed by user input already exists on database.
     * @param userInput category name that as passed by from user input - instance of NewTaskInfoDTO
     * @return persisted category if already on database
     */
    protected Optional<Category> findPersistedCategory(NewTaskInfoDTO userInput) {
        Category inputCategory = new Category(userInput.getCategory());
        Optional<Category> category = categoryService.findById(inputCategory);
        return category;
    }

    /**
     * Responsible for instantiate a new Thread for asynchronous language analysis.
     * @param taskrepo instance of object already created
     */
    private void languageAnalysis(Task taskrepo) {
        LanguageDetectionService analyzerService = new LanguageDetectionService();
        analyzerService.setTaskRepo(taskrepo);
        analyzerService.setTaskRepository(taskRepository);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(analyzerService);

        initializeTimer(taskrepo);

        executorService.shutdown();
    }

    /**
     * Initializes a new timer to handle timeout for language analysis which was sent from user input.
     * @param taskrepo Task instance which was already created and has timeout limit.
     * @return timer
     */
    protected Timer initializeTimer(Task taskrepo) {
        Timer timer = new Timer(Thread.currentThread().getName(), true);
        TimerTask interruptReturnedValues = timeOutAnalysis(taskrepo);
        timer.schedule(interruptReturnedValues, (taskrepo.getTimeOut().getTimeOut() * CONSTANT_TO_MINUTES));
        return timer;
    }

    /**
     * Handles the canceling method after timelimit reaches.
     * @param taskrepo task instance of what needs to be canceled.
     * @return Timer task
     */
    protected TimerTask timeOutAnalysis(Task taskrepo) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    Task canceledTask = new Task(taskrepo);
                    taskRepository.saveTask(canceledTask);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        };
        return task;
    }

    /**
     * Handles the cancellation process of a language analysis from user input
     * @param id if of a task that user wants to cancel
     * @return TaskDTO instance with all information if a task is canceled or empty if that id does not correspond to one task
     * @throws MalformedURLException thrown only if some sort of update done to URL on database.
     */
    public Optional<TaskDTO> cancelTaskAnalysis(NewCancelThreadDTO id) throws MalformedURLException {
        Optional<Task> task = taskRepository.findById(id.getId());
        if (task.get().getCurrentStatus().toString().equals(Task.CurrentStatus.Processing.toString())) {
            Task canceledTask = new Task(task.get());
            taskRepository.saveTask(canceledTask);
            return Optional.of(taskDomainDTOAssembler.toCompleteDTO(task.get()));
        }
        return Optional.empty();
    }
}

