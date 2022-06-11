package LanguageDetection.application.controllers;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.application.DTO.NewTaskInfoDTO;
import LanguageDetection.application.DTO.TaskStatusDTO;
import LanguageDetection.application.services.CategoryService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


@Controller
@RestController
@RequestMapping(path = "/Category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * @return all categories already created
     * @throws ParseException thrown by QueryParser, it can occur when fail to parse a String that is ought to have a special format
     * @throws IOException    thrown by IndexReader class if some sort of I/O problem occurred
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAllCategory() throws ParseException, IOException {
        List<CategoryDTO> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories.toString(), HttpStatus.OK);
    }

    /**
     * This method receives a NewCategoryInfoDTO object, that is automatically created from a
     * JSON by SpringFrameWork. The information is extracted from it and passed to an instance
     * of Category Service that returns a CategoryDTO with the info to be passed to the user in
     * the ResponseEntity object.
     *
     * @param category receives a JSON file that is automatically transformed into a NewTaskInfoDTO object
     * @throws ParseException thrown by QueryParser, it can occur when fail to parse a String that is ought to have a special format
     * @throws IOException    thrown by IndexReader class if some sort of I/O problem occurred
     */
    @PostMapping("")
    public ResponseEntity<Object> createAndSaveCategory(@RequestBody NewCategoryInfoDTO category) throws ParseException, IOException {
        Optional<CategoryDTO> categoryDTO = categoryService.createAndSaveCategory(category);
        if (categoryDTO.isPresent()) {
            return new ResponseEntity<>(categoryDTO.toString(), HttpStatus.CREATED);
        }else {
                return new ResponseEntity("Unable to create, category already exists or invalid characters", HttpStatus.BAD_REQUEST);
            }
    }

    /**
     * @param category
     * @return the deletion of a certain category
     * @throws ParseException thrown by QueryParser, it can occur when fail to parse a String that is ought to have a special format
     * @throws IOException    thrown by IndexReader class if some sort of I/O problem occurred
     */
    @DeleteMapping("")
    public ResponseEntity<Object> deleteCategory(@RequestBody NewCategoryInfoDTO category) throws ParseException, IOException {
        if (categoryService.deleteCategory(category)) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to delete", HttpStatus.BAD_REQUEST);
        }
    }

}
