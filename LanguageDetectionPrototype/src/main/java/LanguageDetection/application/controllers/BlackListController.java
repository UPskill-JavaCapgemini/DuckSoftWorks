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

@Controller
@RestController
@RequestMapping(path = "/BlackList")
public class BlackListController {

    @Autowired
    BlackListService blackListService;

    public BlackListController(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createBlackListItem(@RequestBody NewBlackListInfoDTO url) throws ParseException, IOException {
        BlackListDTO blackListDTO = blackListService.createBlackListItem(url);
        return new ResponseEntity<>(blackListDTO, HttpStatus.CREATED);
    }
}
