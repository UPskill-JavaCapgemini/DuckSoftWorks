package LanguageDetection.domain.DomainService;


import LanguageDetection.domain.ValueObjects.Language;
import LanguageDetection.domain.entities.Task;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public interface ILanguageDetector {

    Language analyze(Task task) throws ParseException, IOException;
}
