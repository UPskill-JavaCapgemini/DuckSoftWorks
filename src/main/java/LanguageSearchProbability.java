import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenizerFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.apache.lucene.index.IndexOptions.DOCS_AND_FREQS;

/**
 * This application is one Proof of Concept  of Lucene library from Apache that
 * can identify 3 languages (at this stage): Portuguese, Spanish and English.
 * It is able to check from input on console or from txt file.
 *
 * @author - DuckSquad Team
 * @author - Daniel Machado
 * @author - Daniel Lima
 * @author - Inês Clavel
 * @author - João Figueiredo
 * @author - Thales Lemos
 */

public class LanguageSearchProbability {
    /**
     * This method identifies the probable languages of an input from console.
     *
     * @param args does not receive any argument
     * @throws IOException thrown by IndexReader class if some sort of I/O problem occurred
     * @throws ParseException thrown by QueryParser it can occur when fail to parse a String that is ought to have a special format
     */
    public static void main(String[] args) throws ParseException, IOException {

        //Lucene Analyzers are used to analyze text while indexing and searching documents
        Analyzer analyzer = new SimpleAnalyzer();

        /*Analyzer analyzer = CustomAnalyzer.builder()
                .withTokenizer(NGramTokenizerFactory.class, "minGramSize", "2", "maxGramSize", "3")
                .build();*/

        //A Directory provides an abstraction layer for storing a list of files that was analysed and to is meant to be searched
        //FSDirectory allow Lucene to choose the best implementation given your system environment

        Directory directory = FSDirectory.open(Paths.get("indexedFiles"));

        //Holds all the configuration that is used to create an IndexWriter

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setSimilarity(new ClassicSimilarity());

        //An IndexWriter creates and maintains an index.

        IndexWriter writer = new IndexWriter(directory, config);
        writer.deleteAll();
        //Documents are the unit of indexing and search. A Document is a set of fields. Each field has a name and a textual value.
        // A field may be stored with the document, in which case it is returned with search hits on the document.
        addDoc(writer, "english", Paths.get("inputFiles/en-common.wl"));
        addDoc(writer, "portuguese", Paths.get("inputFiles/pt_PT.wl"));
        addDoc(writer, "spanish", Paths.get("inputFiles/es.wl"));

        writer.close();

        System.out.println("Insert text:");
        Scanner leitor = new Scanner(System.in);

        String query = leitor.nextLine();
        //String query = new String(Files.readAllBytes(Paths.get("inputFiles/por.txt")));

        String cleanedString = cleanUpInputText(query);
        Query q = new QueryParser("dictionary", analyzer).parse(cleanedString);

        //Initiate the search
        int hitsPerPage = 100;
        //IndexReader is an abstract class, providing an interface for accessing a point-in-time view of an index.
        IndexReader reader = DirectoryReader.open(directory);
        //Implements search over a single IndexReader.
        IndexSearcher searcher = new IndexSearcher(reader);
        //Represents hits returned by IndexSearcher
        TopDocs docs = searcher.search(q, hitsPerPage);
        //Stores hits in TopDocs.
        ScoreDoc[] hits = docs.scoreDocs;

        Map<String, Float> map = new TreeMap<>();

        //result
        //Identifies how many hits were found
        System.out.println("Found in " + hits.length + " dictionaries.");

        Float valor = 0F;
        //Adds in Map the language and score for each language
        for (int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            Float score = hits[i].score;
            String lang = d.get("language");

            map.put(lang, score);
            valor = valor + score;
        }
        //New Map sorted by descendent score value for each language
        Map<String, Float> sortedMap = sortByValue(map);


        //Iterate Map to calculate the percentage for each key on map
        for (Map.Entry<String, Float> entry : sortedMap.entrySet()) {
            Float result = (entry.getValue() * 100) / valor;
            Float biggerProbability = 0F;
            String key = null;
            if (biggerProbability < result) {
                biggerProbability = result;
                key = entry.getKey();
            }
            System.out.printf("Probability of %s is %.2f %% \n", key, biggerProbability);

        }
    }

    /**
     * Sort a Map by descending order of each Value.
     *
     * @param unsortMap Map to be Sorted on descending order. It is required that key is a String and value a Float.
     * @return ordered map by descending order of value(Float)
     */

    private static Map<String, Float> sortByValue(Map<String, Float> unsortMap) {

        // 1. Convert Map to List
        List<Map.Entry<String, Float>> list =
                new LinkedList<Map.Entry<String, Float>>(unsortMap.entrySet());

        // 2. Order with Collections class in descending order
        Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop for re-inserting data into Map
        Map<String, Float> sortedMap = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
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

    /**
     * Adds Documents, that are necessary to index and search.
     * @param w one instance of IndexWriter
     * @param language Identification of Field that is not indexed
     * @param dictionary Path of a file that is tokenized and indexed
     * @throws IOException Thrown if Field cannot read the Path that was provided
     */
    private static void addDoc(IndexWriter w, String language, Path dictionary) throws IOException {
        Document doc = new Document();

        //A field that is indexed but not tokenized: the entire String value is indexed as a single token.
        doc.add(new StringField("language", language, Field.Store.YES));

        //A Field is a section of a document.
        //This Field is stored in the index, it is later returned with hits on the document.
        Field field = new Field("dictionary",  new String(Files.readAllBytes(dictionary)), TYPE_STORED);
        doc.add(field);
        w.addDocument(doc);
    }

    /**
     * Strips input of punctuation, numbers and multiple white spaces.
     * @param text - String input to be stripped
     * @return stripped String
     */
    private static String cleanUpInputText(String text) {
        return text.trim().toLowerCase(Locale.ROOT)
                .replaceAll("\\p{P}", "") //PUNCTUATION
                .replaceAll("\\p{N}", "") // NUMBERS
                .replaceAll("\\s+", " "); // MULTIPLE_WHITESPACE
    }
}
