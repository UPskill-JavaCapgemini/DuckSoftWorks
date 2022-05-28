package LanguageDetection.application.services;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AnalyzerService {
    static Analyzer analyzer;
    static IndexReader reader;
    static IndexSearcher searcher;

    /**
     *        As the IndexSearcher creates and returns a searcher that can be used to execute arbitrary
     *        Lucene queries, it also collects the resulting queries. Those queries results are translated
     *        as hits.
     */
    private static final int HITS_PER_PAGE = 100;


    /**
     *This constructor creates a new instance of the SimpleAnalyse.
     *It uses the Directory to indicate where the IndexReader should act. For that,
     *the Directory comunicates to the DicrionaryService and uses one of its instances to
     *get the correct directory.
     *Then creates an IndexSearcher instance (searcher) that recieves a IndexReader
     *instance (reader) as a paramether.
     * @throws IOException - thrown by IndexReader class if some sort of I/O problem occurred
     */
    public AnalyzerService() throws IOException {
        analyzer = new SimpleAnalyzer();
        reader = DirectoryReader.open(DictionaryService.getInstance().directory);
        searcher = new IndexSearcher(reader);
    }

    /**
     * This method serves the purpouse of identifying the language used in a string that is passed as a paramether.
     * @param query - the string that is analized.
     * @return of a string of the most probable language found.
     * @throws ParseException - Signals that an error has been reached unexpectedly in the QueryParse
     * when a problem occurs when parsing.
     * @throws IOException - thrown by IndexReader class if some sort of I/O problem occurred
     */

    public static String analyze(String query) throws ParseException, IOException {

        //maximum value of an Integer to allow the most hits possible for an IndexSearcher
        IndexSearcher.setMaxClauseCount(Integer.MAX_VALUE);

        Query q = new QueryParser("dictionary", analyzer).parse(query);

        TopDocs docs = searcher.search(q, HITS_PER_PAGE);
        ScoreDoc[] hits = docs.scoreDocs;

        return searcher.doc(hits[0].doc).get("language");
    }
}
