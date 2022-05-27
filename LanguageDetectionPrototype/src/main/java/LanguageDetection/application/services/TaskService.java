package LanguageDetection.application.services;


import LanguageDetection.application.dtos.NewTaskInfoDTO;
import LanguageDetection.application.dtos.TaskDTO;
import LanguageDetection.application.dtos.assemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.ValueObjects.Language;
import LanguageDetection.domain.entities.example.Task;
import LanguageDetection.domain.factories.TaskFactory;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.apache.lucene.index.IndexOptions.DOCS_AND_FREQS;


@Service
public class TaskService {

    @Autowired
    TaskFactory taskFactory;

    @Autowired
    TaskDomainDTOAssembler taskDomainDTOAssembler;

    private SimpleAnalyzer analyzer;
    private Directory directory;
    private IndexWriter writer;
    private IndexWriterConfig config;
    private IndexReader reader;

    private final int HITS_PER_PAGE = 100;

    public TaskService() throws IOException {
        this.analyzer = new SimpleAnalyzer();
        DictionaryService.getInstance().dictionaries();
    }

    public TaskDTO createTask(NewTaskInfoDTO string) throws ParseException, IOException {
        String language = analyse(string.getText());
        //todo: add language in TaskFactory method (ENUM)
        Task task = taskFactory.createTask(string.getText(), Language.valueOf(language));
        TaskDTO taskDTO = taskDomainDTOAssembler.toDTO(task.getLang());
        return taskDTO;
    }



    //Documents are the unit of indexing and search. A Document is a set of fields. Each field has a name and a textual value.
    // A field may be stored with the document, in which case it is returned with search hits on the document.


    private String analyse(String query) throws ParseException, IOException {
        BooleanQuery.setMaxClauseCount(2147483647);
        reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        Query q = new QueryParser("dictionary", analyzer).parse(cleanUpInputText(query));


        TopDocs docs = searcher.search(q, HITS_PER_PAGE);
        ScoreDoc[] hits = docs.scoreDocs;

        String language = searcher.doc(hits[0].doc).get("language");
        return language;
    }






    private static String cleanUpInputText(String text) {
        return text.trim().toLowerCase(Locale.ROOT)
                .replaceAll("\\p{P}", "") //PUNCTUATION
                .replaceAll("\\p{N}", "") // NUMBERS
                .replaceAll("\\s+", " "); // MULTIPLE_WHITESPACE
    }
}


