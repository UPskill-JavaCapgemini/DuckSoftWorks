
package LanguageDetection.application.services;



import LanguageDetection.application.DTO.*;

import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewTaskInfoDTO;
import LanguageDetection.application.DTO.TaskDTO;

import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.DomainService.AnalyzerService;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;



/**
 * Represents the task service responsible for creating a task.
 *
 * @author DuckSoftWorks
 */

@Service
public class TaskService {


    @Autowired

/**
 * The domain DTO assembler for a task.
 */

            TaskDomainDTOAssembler taskDomainDTOAssembler;


    @Autowired

/**
 * The Analyzer service.
 */

            AnalyzerService analyzerService;

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

    public Optional<TaskDTO> createTask(NewTaskInfoDTO userInput) throws ParseException, IOException {

        NewBlackListInfoDTO newBlackListInfoDTO = new NewBlackListInfoDTO(userInput.getUrl());

        if(blackListService.isBlackListed(newBlackListInfoDTO))
            return Optional.empty();

        else
        {
            Optional<Category> persistedCategory = findPersistedCategory(userInput);

            if (persistedCategory.isPresent()) {
                Task task = new Task(userInput.getUrl(), userInput.getTimeOut(), persistedCategory.get());
                Task taskRepo = taskRepository.saveTask(task);
                return Optional.of(taskDomainDTOAssembler.toDTO(taskRepo));
            }
            return Optional.empty();
        }
    }

    public List<Task> findAllTasks() {

        List<Task> listAllTasks = taskRepository.findAllTasks();

        List<NewTaskInfoDTO> setNewTaskInfoDTO = new ArrayList<NewTaskInfoDTO>();

        return listAllTasks;

    }


    public List<Task> findByStatusContaining (StatusDTO st) {
        List<Task> listTasksByStatus = taskRepository.findByStatusContaining(Task.CurrentStatus.valueOf(st.getStatus()));


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

   /* public TaskDTO findByCategory(NewTaskInfoDTO userInput) throws ParseException, IOException{

    }*/

    protected Optional<Category> findPersistedCategory(NewTaskInfoDTO userInput) {
        Category inputCategory = new Category(userInput.getCategory());
        Optional<Category> category = categoryService.findById(inputCategory);
        return category;
    }

}

