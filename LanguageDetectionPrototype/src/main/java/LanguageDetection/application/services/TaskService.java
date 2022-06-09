
package LanguageDetection.application.services;


import LanguageDetection.application.DTO.*;

import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewTaskInfoDTO;
import LanguageDetection.application.DTO.TaskStatusDTO;

import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
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
 * Represents the task service responsible for creating a task.
 *
 * @author DuckSoftWorks
 */
@Slf4j
@Service
public class TaskService {


    private static final long CONSTANT_TO_MINUTES = 60000L;
    /**
     * The domain DTO assembler for a task.
     */
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
     * Cleans up the input text by calling cleanUpInputText() and
     * analyzes it with the service analyzer.
     *
     * @param userInput the string containing the text within NewTaskInfoDTO.
     * @return TaskDTO assembled by taskDomainDTOAssembler.
     * @throws ParseException - Signals that an error has been reached unexpectedly in the QueryParse
     * @throws IOException    - thrown by IndexReader class if some sort of I/O problem occurred
     */

    public Optional<TaskStatusDTO> createAndSaveTask(NewTaskInfoDTO userInput) throws IOException, ExecutionException, InterruptedException, TimeoutException {

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

    public List<Task> findAllTasks() {

        List<Task> listAllTasks = taskRepository.findAllTasks();

        return listAllTasks;
    }

    public List<Task> findByStatusContaining(StatusDTO inputStatus) {
        Task.CurrentStatus status = Task.CurrentStatus.valueOf(inputStatus.getStatus());
        List<Task> listTasksByStatus = taskRepository.findByStatusContaining(status);

        return listTasksByStatus;
    }

    public List<Task> findByCategoryContaining(CategoryNameDTO catName) {
        Category category = new Category(catName.getCategoryName());
        List<Task> listTasksByCategory = taskRepository.findByCategoryContaining(category);

        return listTasksByCategory;
    }

    public List<Task> findByStatusContainingAndCategoryContaining(StatusDTO inputStatus, CategoryNameDTO inputCategory) {
        Task.CurrentStatus status = Task.CurrentStatus.valueOf(inputStatus.getStatus());
        Category category = new Category(inputCategory.getCategoryName());

        List<Task> listTasksByStatusAndByCategory = taskRepository.findByStatusAndByCategoryContaining(status, category);
        return listTasksByStatusAndByCategory;
    }

    protected Optional<Category> findPersistedCategory(NewTaskInfoDTO userInput) {
        Category inputCategory = new Category(userInput.getCategory());
        Optional<Category> category = categoryService.findById(inputCategory);
        return category;
    }

    protected void languageAnalysis(Task taskrepo) throws ExecutionException, InterruptedException, TimeoutException, MalformedURLException {
        LanguageDetectionService analyzerService = new LanguageDetectionService();
        analyzerService.setTaskRepo(taskrepo);
        analyzerService.setTaskRepository(taskRepository);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> returnedValues = executorService.submit(analyzerService);

        Timer timer = new Timer(Thread.currentThread().getName(), true);
        TimerTask interruptReturnedValues = timeOutAnalysis(returnedValues, taskrepo);
        timer.schedule(interruptReturnedValues, (taskrepo.getTimeOut().getTimeOut() * CONSTANT_TO_MINUTES));

        log.info("ID da thread: " + Thread.currentThread().getId() + "Nome da thread: " + Thread.currentThread().getName());

        executorService.shutdown();
    }

    protected TimerTask timeOutAnalysis(Future<String> future, Task taskrepo) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                future.cancel(true);
                System.out.println("Cancelled");
                if (future.isCancelled()) {
                    try {
                        Task canceledTask = new Task(taskrepo);
                        taskRepository.saveTask(canceledTask);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        return task;
    }

    public Optional<Task> cancelTaskAnalysis(NewCancelThreadDTO id) throws MalformedURLException {
        Optional<Task> task = taskRepository.findById(id.getId());
        if (task.get().getCurrentStatus().toString().equals(Task.CurrentStatus.Processing.toString())){
            Task canceledTask = new Task(task.get());
            taskRepository.saveTask(canceledTask);
            return task;
        }
        return Optional.empty();
    }
}

