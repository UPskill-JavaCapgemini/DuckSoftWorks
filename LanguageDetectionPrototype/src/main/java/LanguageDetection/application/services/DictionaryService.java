package LanguageDetection.application.services;

import lombok.Getter;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.apache.lucene.index.IndexOptions.DOCS_AND_FREQS;

@Service
public class DictionaryService {
Directory directory;
IndexWriterConfig config;
IndexWriter writer;
private static DictionaryService singleton = null;

    public DictionaryService() throws IOException {
        this.directory = FSDirectory.open(Paths.get("src/main/java/LanguageDetection/infrastructure/repositories/indexedFiles"));
        this.config = new IndexWriterConfig();
        this.writer = new IndexWriter(directory, config);
        config.setSimilarity(new ClassicSimilarity());
        writer.deleteAll();
        dictionaries();
    }

    protected void dictionaries() throws IOException {
        addDoc(writer, "ENGLISH", Paths.get("src/main/java/LanguageDetection/infrastructure/repositories/inputFiles/en-common.wl"));

        addDoc(writer, "PORTUGUESE", Paths.get("src/main/java/LanguageDetection/infrastructure/repositories/inputFiles/pt_PT.wl"));

        addDoc(writer, "SPANISH", Paths.get("src/main/java/LanguageDetection/infrastructure/repositories/inputFiles/es.wl"));
        writer.close();
    }

    protected static DictionaryService getInstance() throws IOException {
        if (singleton == null)
            singleton = new DictionaryService();

        return singleton;
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


}
