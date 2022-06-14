package LanguageDetection.application.controllers;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.services.BlackListManagementService;
import org.apache.lucene.queryparser.classic.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(path = "/BlackList")
public class BlackListController {

    @Autowired
    BlackListManagementService blackListManagementService;


    /**
     * @return all BlackListItems already created
     * @throws ParseException thrown by QueryParser, it can occur when fail to parse a String that is ought to have a special format
     * @throws IOException    thrown by IndexReader class if some sort of I/O problem occurred
     */

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAllBlackListItems() throws ParseException, IOException {
        List<BlackListDTO> blackListItems = blackListManagementService.getAllBlackListItems();
        return new ResponseEntity<>(blackListItems.toString(), HttpStatus.OK);
    }
    /**
     * This method receives a NewCategoryInfoDTO object, that is automatically created from a
     * JSON by SpringFrameWork. The information is extracted from it and passed to an instance
     * of BlackListService that returns a BlackListDTO with the info to be passed to the user in
     * the ResponseEntity object.
     *
     * @param url receives a JSON file that is automatically transformed into a NewBlackListInfoDTO object
     * @throws ParseException thrown by QueryParser, it can occur when fail to parse a String that is ought to have a special format
     * @throws IOException    thrown by IndexReader class if some sort of I/O problem occurred
     */

    @PostMapping("")
    public ResponseEntity<Object> createAndSaveBlackListItem(@RequestBody NewBlackListInfoDTO url) {
        Optional<BlackListDTO> blackListDTO = blackListManagementService.createAndSaveBlackListItem(url);
        if (blackListDTO.isPresent()){
            return new ResponseEntity<>(blackListDTO.get().toString(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Unable to create, URL already exits in the Blacklist or is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param blackListInfoDTO
     * @return the deletion of a certain blackListItem that was previously created
     * @throws ParseException thrown by QueryParser, it can occur when fail to parse a String that is ought to have a special format
     * @throws IOException    thrown by IndexReader class if some sort of I/O problem occurred
     */
    @DeleteMapping("")
    public ResponseEntity<Object> deleteBlackListItem(@RequestBody NewBlackListInfoDTO blackListInfoDTO) throws ParseException, IOException {
        if (blackListManagementService.deleteBlackListItem(blackListInfoDTO)) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Black list item does not exist", HttpStatus.NOT_FOUND);
        }
    }
}