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
import java.util.List;

@Controller
@RestController
@RequestMapping(path = "/BlackList")
public class BlackListController {

    @Autowired
    BlackListService blackListService;

    public BlackListController(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> findAll() throws ParseException, IOException {
        List<BlackListDTO> blackListItems = blackListService.findAll();
       return new ResponseEntity<>(blackListItems.toString(),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> createBlackListItem(@RequestBody NewBlackListInfoDTO url) throws ParseException, IOException {
        BlackListDTO blackListDTO = blackListService.createBlackListItem(url);
        return new ResponseEntity<>(blackListDTO.toString(), HttpStatus.CREATED);
    }

}
