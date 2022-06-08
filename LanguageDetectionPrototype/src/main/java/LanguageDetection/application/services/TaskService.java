
package LanguageDetection.application.services;


import LanguageDetection.application.DTO.*;

import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewTaskInfoDTO;
import LanguageDetection.application.DTO.TaskDTO;

import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.DomainService.ILanguageDetector;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    @Autowired
    ILanguageDetector languageDetector;


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

    public Optional<TaskDTO> createTask(NewTaskInfoDTO userInput) throws ParseException, IOException, ExecutionException, InterruptedException, TimeoutException {

        NewBlackListInfoDTO newBlackListInfoDTO = new NewBlackListInfoDTO(userInput.getUrl());

        if (!blackListService.isBlackListed(newBlackListInfoDTO)) {
            Optional<Category> persistedCategory = findPersistedCategory(userInput);

            if (persistedCategory.isPresent()) {
                Task task = new Task(userInput.getUrl(), userInput.getTimeOut(), persistedCategory.get());
                Task taskRepo = taskRepository.saveTask(task);
                newThread(taskRepo, (long) userInput.getTimeOut());
                return Optional.of(taskDomainDTOAssembler.toDTO(taskRepo));
            }
        }
        return Optional.empty();
    }

    public List<Task> findAllTasks() {

        List<Task> listAllTasks = taskRepository.findAllTasks();

        List<NewTaskInfoDTO> setNewTaskInfoDTO = new ArrayList<NewTaskInfoDTO>();

        return listAllTasks;

    }

    public List<Task> findByStatusContaining(StatusDTO st) {
        Task.CurrentStatus status = Task.CurrentStatus.valueOf(st.getStatus());
        List<Task> listTasksByStatus = taskRepository.findByStatusContaining(status);


        return listTasksByStatus;
    }

    public List<Task> findByCategoryContaining(CategoryNameDTO catName) {
        Category category = new Category(catName.getCategoryName());
        List<Task> listTasksByCategory = taskRepository.findByCategoryContaining(category);


        return listTasksByCategory;
    }

    public List<Task> findByStatusContainingAndCategoryContaining(StatusDTO status, CategoryNameDTO categoryNameDTO) {
        Task.CurrentStatus status1 = Task.CurrentStatus.valueOf(status.getStatus());
        Category category1 = new Category(categoryNameDTO.getCategoryName());

        List<Task> listTasksByStatusAndByCategory = taskRepository.findByStatusAndByCategoryContaining(status1, category1);
        return listTasksByStatusAndByCategory;
    }

    protected Optional<Category> findPersistedCategory(NewTaskInfoDTO userInput) {
        Category inputCategory = new Category(userInput.getCategory());
        Optional<Category> category = categoryService.findById(inputCategory);
        return category;
    }

    @Async
    public void newThread(Task taskrepo, Long userinput) throws ExecutionException, InterruptedException, TimeoutException {
        LanguageDetectionService analyzerService = new LanguageDetectionService();
        analyzerService.setUrl(taskrepo.getUrl().getUrl());

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> returnedValues = executorService.submit(analyzerService);
        Timer timer = new Timer(Thread.currentThread().getName(),true);
        TimerTask interruptReturnedValues = timeOutAnalysis(returnedValues);
        timer.schedule(interruptReturnedValues,2000);

        log.info("ID da thread: " + Thread.currentThread().getId() + "Nome da thread: " + Thread.currentThread().getName());

        executorService.shutdown();
    }

    protected TimerTask timeOutAnalysis(Future<String> future){

      TimerTask task =  new TimerTask() {
           @Override
           public void run() {
               boolean cancelledFuture =  (future.cancel(true));
               System.out.println("Cancelled");
           }
       };

       return task;
    }
}

