package LanguageDetection.application.controllers;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.application.services.BlackListService;
import LanguageDetection.application.services.CategoryService;
import LanguageDetection.domain.entities.Category;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;



    @Controller
    @RestController
    @RequestMapping(path = "/Category")
    public class CategoryController {

        @Autowired
        CategoryService categoryService;

        public CategoryController(CategoryService categoryService) {
            this.categoryService = categoryService;
        }

        @GetMapping("")
        @ResponseBody
        public ResponseEntity<Object> findAll() throws ParseException, IOException {
            List<CategoryDTO> categories = categoryService.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }

        @PostMapping("")
        public ResponseEntity<Object> createCategory(@RequestBody NewCategoryInfoDTO category) throws ParseException, IOException {
            CategoryDTO categoryDTO = categoryService.createCategory(category);
            return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
        }

        @PostMapping("/Delete")
        public ResponseEntity<Object> deleteCategory(@RequestBody NewCategoryInfoDTO category) throws ParseException, IOException {
            CategoryDTO categoryDTO = categoryService.deleteCategory(category);
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        }

}
