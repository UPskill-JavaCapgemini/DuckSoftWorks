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
@CrossOrigin(origins = "http://localhost:5500/", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping(path = "/Category")

/**
 * Represents the REST Controller for Category related functionalities
 */

public class CategoryController {

    @Autowired
    CategoryManagementService categoryManagementService;

    /**
     * This method fetches information for all categories persisted in the database and returns a list containing them
     *
     * @return a ResponseEntity that holds information of a list with all existent Category entries
     */
    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ResponseBody
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryManagementService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * This method attempts to create and save a category with the information provided by the admin.
     *
     * @param category the NewCategoryInfoDTO containing the information about the Category to be created and saved
     * @return a ResponseEntity that holds information of a Category either being successfully created or not
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
     * This method deletes a persisted Category if the item is persisted in the database.
     *
     * @param category the NewCategoryInfoDTO containing the information about the Category to be deleted
     * @return  a ResponseEntity that holds information of a Category either being successfully deleted or not
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
