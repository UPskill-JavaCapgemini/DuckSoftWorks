package LanguageDetection.application.services;


import LanguageDetection.application.dtos.NewAnalysisInfoDTO;
import LanguageDetection.application.dtos.AnalysisDTO;
import LanguageDetection.application.dtos.assemblers.AnalysisDTOAssembler;
import LanguageDetection.domain.entities.example.Analyzer;
import LanguageDetection.domain.entities.example.Language;
import javassist.tools.Callback;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Callable;


/**
 * Represents the task service responsible for creating a task.
 *
 * @author DuckSoftWorks
 */
@Service
public class AnalysisService {


    @Autowired
    /**
     * The domain DTO assembler for a task.
     */
            AnalysisDTOAssembler analysisDTOAssembler;


    @Autowired
    /**
     * The Analyzer service.
     */
            Analyzer analyzer;


    /**
     * Creates a new task with a NewTaskInfoDTO received by parameter.
     * Cleans up the input text by calling cleanUpInputText() and
     * analyzes it with the service analyzer.
     *
     * @param string the string containing the text within NewTaskInfoDTO.
     * @return TaskDTO assembled by taskDomainDTOAssembler.
     * @throws ParseException - Signals that an error has been reached unexpectedly in the QueryParse
     * @throws IOException    - thrown by IndexReader class if some sort of I/O problem occurred
     */
    public AnalysisDTO analyzeLanguage(NewAnalysisInfoDTO string) throws ParseException, IOException {
        Language language = analyzer.analyze(string.getUrl());
        return analysisDTOAssembler.toDTO(language);
    }

}


