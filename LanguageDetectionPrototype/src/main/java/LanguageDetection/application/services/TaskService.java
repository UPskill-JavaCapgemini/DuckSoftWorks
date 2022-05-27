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
        this.directory = FSDirectory.open(Paths.get("src/main/java/LanguageDetection/infrastructure/repositories/indexedFiles"));
        this.config = new IndexWriterConfig();
        config.setSimilarity(new ClassicSimilarity());
        this.writer = new IndexWriter(directory, config);
        writer.deleteAll();
        dictionaries();
    }

    public TaskDTO createTask(NewTaskInfoDTO string) throws ParseException, IOException {
        String language = ServiceAnalyzer.analyze(string.getText());
        //todo: add language in TaskFactory method (ENUM)
        Task task = taskFactory.createTask(string.getText(), Language.valueOf(language));
        TaskDTO taskDTO = taskDomainDTOAssembler.toDTO(task.getLang());
        return taskDTO;
    }


    private void dictionaries() throws IOException {
        addDoc(writer, "ENGLISH", Paths.get("src/main/java/LanguageDetection/infrastructure/repositories/inputFiles/en-common.wl"));

        addDoc(writer, "PORTUGUESE", Paths.get("src/main/java/LanguageDetection/infrastructure/repositories/inputFiles/pt_PT.wl"));

        addDoc(writer, "SPANISH", Paths.get("src/main/java/LanguageDetection/infrastructure/repositories/inputFiles/es.wl"));
        writer.close();
    }


    //Describes the properties of a field.
    public static final FieldType TYPE_STORED = new FieldType();

    static {
        TYPE_STORED.setIndexOptions(DOCS_AND_FREQS);
        TYPE_STORED.setTokenized(true);
        TYPE_STORED.setStored(true);
        TYPE_STORED.setStoreTermVectors(true);
        TYPE_STORED.setStoreTermVectorPositions(true);
        TYPE_STORED.freeze();
    }

    private static void addDoc(IndexWriter w, String language, Path dictionary) throws IOException {
        Document doc = new Document();

        //A field that is indexed but not tokenized: the entire String value is indexed as a single token.
        doc.add(new StringField("language", language, Field.Store.YES));

        //A Field is a section of a document.
        //This Field is stored in the index, it is later returned with hits on the document.
        Field field = new Field("dictionary", new String(Files.readAllBytes(dictionary)), TYPE_STORED);
        doc.add(field);
        w.addDocument(doc);
    }


    private static String cleanUpInputText(String text) {
        return text.trim().toLowerCase(Locale.ROOT)
                .replaceAll("\\p{P}", "") //PUNCTUATION
                .replaceAll("\\p{N}", "") // NUMBERS
                .replaceAll("\\s+", " "); // MULTIPLE_WHITESPACE
    }
}


