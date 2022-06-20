package LanguageDetection.domain.DomainService;


import LanguageDetection.domain.model.ValueObjects.Language;
import LanguageDetection.domain.model.Task;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public interface ILanguageDetector {


    void languageAnalysis(Task task);
}
