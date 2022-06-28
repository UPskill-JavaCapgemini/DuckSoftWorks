package LanguageDetection.infrastructure.analyzer;

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
public class Dictionary {
Directory directory;
IndexWriterConfig config;
IndexWriter writer;
private static Dictionary singleton = null;

    /**
     *This constructor creates an instance of DictionaryService.
     *It uses a Directory that is the base class for Directory implementations that store index files in the file system.
     *Uses a IndexWritterConfig that holds all the configuration that is used to create an IndexWriter of the Directory.
     *@throws IOException thrown if some sort of I/O problem occurred.
     */

    public Dictionary() throws IOException {
        this.directory = FSDirectory.open(Paths.get("src/main/java/LanguageDetection/infrastructure/indexedFiles"));
        this.config = new IndexWriterConfig();
        this.writer = new IndexWriter(directory, config);
        config.setSimilarity(new ClassicSimilarity());
        writer.deleteAll();
        dictionaries();
    }

    /**
     * Adds the files of dictionaires to the IndexWritter
     * @throws IOException if some sort of I/O problem occurred.
     */
    protected void dictionaries() throws IOException {
        addDoc(writer, "ENGLISH", Paths.get("src/main/java/LanguageDetection/infrastructure/inputFiles/en-common.wl"));

        addDoc(writer, "PORTUGUESE", Paths.get("src/main/java/LanguageDetection/infrastructure/inputFiles/pt_PT.wl"));

        addDoc(writer, "SPANISH", Paths.get("src/main/java/LanguageDetection/infrastructure/inputFiles/es.wl"));
        writer.close();
    }

    /**
     * Method that returns a unique instance of DictionaryService
     * @return a singleton DictionaryService instance
     * @throws IOException thrown if some sort of I/O problem occurred
     */
    protected static Dictionary getInstance() throws IOException {
        if (singleton == null)
            singleton = new Dictionary();

        return singleton;
    }

    /**
     * Sets the properties of a field.
     */
    public static final FieldType TYPE_STORED = new FieldType();

    static {
        TYPE_STORED.setIndexOptions(DOCS_AND_FREQS);
        TYPE_STORED.setTokenized(true);
        TYPE_STORED.setStored(true);
        TYPE_STORED.setStoreTermVectors(true);
        TYPE_STORED.setStoreTermVectorPositions(true);
        TYPE_STORED.freeze();
    }

    /**
     * Method for indexes documents and prepared them for later tokenization.
     * It uses a StringField that is indexed but not tokenized: the entire String value is indexed.
     * Creates a Field that is a section of a document and is stored in the index.
     * @param w creates and maintains an index
     * @param language defines the language of the dictionary
     * @param dictionary defines the path to the file
     * @throws IOException thrown if some sort of I/O problem occurred
     */
    private static void addDoc(IndexWriter w, String language, Path dictionary) throws IOException {
        Document doc = new Document();

        doc.add(new StringField("language", language, Field.Store.YES));

        Field field = new Field("dictionary", new String(Files.readAllBytes(dictionary)), TYPE_STORED);
        doc.add(field);
        w.addDocument(doc);
    }


}
