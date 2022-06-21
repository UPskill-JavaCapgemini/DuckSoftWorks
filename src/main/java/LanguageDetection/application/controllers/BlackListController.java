package LanguageDetection.application.controllers;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.services.BlackListManagementService;

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
@RequestMapping(path = "/BlackList")
/**
 * Represents the REST Controller for BlackList related functionalities
 */
public class BlackListController {

    @Autowired
    BlackListManagementService blackListManagementService;


    /**
     * This method fetches information for all blackListItems persisted in the database and returns a list containing them
     *
     * @return all BlackListItems already created and persisted in the database
     */

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<List<BlackListDTO>> getAllBlackListItems() {
        List<BlackListDTO> blackListItems = blackListManagementService.getAllBlackListItems();
        return new ResponseEntity<>(blackListItems, HttpStatus.OK);
    }
    /**
     * This method attempts to create and save a blackListItem with the information provided by the admin.
     *
     * @param url the NewBlackListInfoDTO containing the information about the BlackListItem to be created and saved
     * @return a ResponseEntity that holds information of a blackListItem either being successfully created or not
     */

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createAndSaveBlackListItem(@RequestBody NewBlackListInfoDTO url) {
        Optional<BlackListDTO> blackListDTO = blackListManagementService.createAndSaveBlackListItem(url);
        if (blackListDTO.isPresent()){
            return new ResponseEntity<>(blackListDTO.get(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Unable to create, URL already exits in the Blacklist or is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method deletes a persisted BlackListItem if the item is persisted in the database.
     * @param blackListInfoDTO the NewBlackListInfoDTO containing the information about the BlackListItem to be deleted
     * @return a ResponseEntity that holds information of a blackListItem either being successfully deleted or not
     */
    @DeleteMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteBlackListItem(@RequestBody NewBlackListInfoDTO blackListInfoDTO) {
        if (blackListManagementService.deleteBlackListItem(blackListInfoDTO)) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Black list item does not exist", HttpStatus.NOT_FOUND);
        }
    }
}