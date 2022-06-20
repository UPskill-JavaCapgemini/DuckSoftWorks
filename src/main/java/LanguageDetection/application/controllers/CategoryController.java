package LanguageDetection.application.controllers;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.application.services.CategoryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/Category")
public class CategoryController {

    @Autowired
    CategoryManagementService categoryManagementService;

    public CategoryController(CategoryManagementService categoryManagementService) {
        this.categoryManagementService = categoryManagementService;
    }

    /**
     * @return all categories already created
     */
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ResponseBody
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryManagementService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * This method receives a NewCategoryInfoDTO object, that is automatically created from a
     * JSON by SpringFrameWork. The information is extracted from it and passed to an instance
     * of Category Service that returns a CategoryDTO with the info to be passed to the user in
     * the ResponseEntity object.
     *
     * @param category receives a JSON file that is automatically transformed into a NewCategoryInfoDTO object
     */
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createAndSaveCategory(@RequestBody NewCategoryInfoDTO category) {
        Optional<CategoryDTO> categoryDTO = categoryManagementService.createAndSaveCategory(category);
        if (categoryDTO.isPresent()) {
            return new ResponseEntity<>(categoryDTO.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Unable to create. Either already exists or invalid characters", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param category
     * @return the deletion of a certain category
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("")
    public ResponseEntity<Object> deleteCategory(@RequestBody NewCategoryInfoDTO category) {
        if (categoryManagementService.deleteCategory(category)) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to delete", HttpStatus.BAD_REQUEST);
        }
    }

}
