package LanguageDetection.application.controllers;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.services.BlackListService;
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
    BlackListService blackListService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAllBlackListItems() throws ParseException, IOException {
        List<BlackListDTO> blackListItems = blackListService.getAllBlackListItems();
        return new ResponseEntity<>(blackListItems.toString(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> createAndSaveBlackListItem(@RequestBody NewBlackListInfoDTO url) throws MalformedURLException {
        Optional<BlackListDTO> blackListDTO = blackListService.createAndSaveBlackListItem(url);
        if (blackListDTO.isPresent()){
            return new ResponseEntity<>(blackListDTO.toString(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Unable to create, URL already exits in the Blacklist or is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteBlackListItem(@RequestBody NewBlackListInfoDTO blackListInfoDTO) throws ParseException, IOException {
        if (blackListService.deleteBlackListItem(blackListInfoDTO)) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Black list item does not exist", HttpStatus.NOT_FOUND);
        }
    }
}