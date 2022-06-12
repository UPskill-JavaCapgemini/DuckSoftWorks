package LanguageDetection.domain.DomainService;


import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public interface ILanguageDetector {

    String analyze(String query) throws ParseException, IOException;
}
