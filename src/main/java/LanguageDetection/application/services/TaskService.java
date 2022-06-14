
package LanguageDetection.application.services;

import LanguageDetection.application.DTO.*;

import LanguageDetection.application.DTO.NewTaskInfoDTO;
import LanguageDetection.application.DTO.TaskStatusDTO;

import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.ValueObjects.CategoryName;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ITaskRepository;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.domain.factory.TaskFactory;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
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
    TaskRepository taskRepo;

    @Autowired
    ITaskRepository iTaskRepository;

    @Autowired
    BlackListManagementService blackListManagementService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TaskFactory taskFactory;


    /**
     * Creates a new task with a NewTaskInfoDTO received by parameter.
     * Before task creation it is checked if URL from input is on BlackList or not.
     *
     * @param userInput the string containing the text within NewTaskInfoDTO.
     * @return TaskStatusDTO assembled by taskDomainDTOAssembler, with information of Status when created(Processing) or empty if url is on blacklist and unable to crate
     * @throws IOException thrown if URL is malformed
     */
    public Optional<TaskStatusDTO> createAndSaveTask(NewTaskInfoDTO userInput) throws IOException {

        InputUrl inputUrl = new InputUrl(userInput.getUrl());
        TimeOut timeOut= new TimeOut(userInput.getTimeOut());
        Category category = new Category(userInput.getCategory());

        Optional<Task> opCreatedTask = taskFactory.createTask(inputUrl,timeOut,category);

        if (opCreatedTask.isPresent())
        {
            Task savedTask = this.iTaskRepository.saveTask(opCreatedTask.get());
            languageAnalysis(savedTask);
            return Optional.of(taskDomainDTOAssembler.toDTO(savedTask));

        }
        return Optional.empty();
    }

    /**
     * Fetch all tasks found in database
     * @return List of TaskDTO with all information
     */
    public List<TaskDTO> getAllTasks() {
        List<Task> listAllTasks = iTaskRepository.findAllTasks();
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
        Task.TaskStatus status = Task.TaskStatus.valueOf(inputStatus.getStatus());
        List<Task> listTasksByStatus = iTaskRepository.findByStatusContaining(status);

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
    public List<TaskDTO> findByCategoryNameContaining(CategoryNameDTO catName) {
        CategoryName categoryName = new CategoryName(catName.getCategoryName());
        List<Task> listTasksByCategory = iTaskRepository.findByCategoryNameContaining(categoryName);

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
        Task.TaskStatus status = Task.TaskStatus.valueOf(inputStatus.getStatus());
        Category category = new Category(inputCategory.getCategoryName());

        List<Task> listTasksByStatusAndByCategory = iTaskRepository.findByStatusAndByCategoryContaining(status, category);

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
    protected Category findCategoryOrDefault(NewTaskInfoDTO userInput) {
        Category inputCategory = new Category(userInput.getCategory());
        Optional<Category> category = categoryService.findCategoryByName(inputCategory);
        if(category.isPresent()){
            return category.get();
        }
        //TODO
        return new Category("");
    }

    /**
     * Responsible for instantiate a new Thread for asynchronous language analysis.
     * @param task instance of object already created
     */
    private void languageAnalysis(Task task) {
        LanguageDetectionService analyzerService = new LanguageDetectionService();

        analyzerService.setTaskToBeAnalyzed(task);

        analyzerService.setTaskRepository(taskRepo);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(analyzerService);

        initializeTimer(task);

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
     * @param task task instance of what needs to be canceled.
     * @return Timer task
     */
    protected TimerTask timeOutAnalysis(Task task) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                task.updateStatus(Task.TaskStatus.Canceled);
                taskRepo.saveTask(task);
            }
        };
        return timerTask;
    }

    /**
     * Handles the cancellation process of a language analysis from user input
     * @param id if of a task that user wants to cancel
     * @return TaskDTO instance with all information if a task is canceled or empty if that id does not correspond to one task
     * @throws MalformedURLException thrown only if some sort of update done to URL on database.
     */
    public Optional<TaskDTO> cancelTaskAnalysis(NewCancelThreadDTO id) throws MalformedURLException {
        Optional<Task> optionalTask = iTaskRepository.findById(id.getId());
        if (optionalTask.isPresent() && optionalTask.get().isStatusProcessing()) {
            Task task = optionalTask.get();
            iTaskRepository.saveTask(task);
            return Optional.of(taskDomainDTOAssembler.toCompleteDTO(optionalTask.get()));
        }
        return Optional.empty();
    }
}

