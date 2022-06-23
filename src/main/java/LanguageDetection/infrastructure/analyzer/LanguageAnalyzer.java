package LanguageDetection.infrastructure.analyzer;

import LanguageDetection.domain.model.ValueObjects.Language;
import LanguageDetection.domain.model.Task;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LanguageAnalyzer {

    private static final int MAX_TOKENS = 1200000;
    static org.apache.lucene.analysis.Analyzer analyzer;
    static IndexReader reader;
    static IndexSearcher searcher;

    /**
     * As the IndexSearcher creates and returns a searcher that can be used to execute arbitrary
     * Lucene queries, it also collects the resulting queries. Those queries results are translated
     * as hits.
     */
    private static final int HITS_PER_PAGE = 100;


    /**
     * This constructor creates a new instance of the SimpleAnalyse.
     * It uses the Directory to indicate where the IndexReader should act. For that,
     * the Directory comunicates to the DicrionaryService and uses one of its instances to
     * get the correct directory.
     * Then creates an IndexSearcher instance (searcher) that recieves a IndexReader
     * instance (reader) as a paramether.
     *
     * @throws IOException - thrown by IndexReader class if some sort of I/O problem occurred
     */
    public LanguageAnalyzer() throws IOException {
        analyzer = new SimpleAnalyzer();
        reader = DirectoryReader.open(Dictionary.getInstance().directory);
        searcher = new IndexSearcher(reader);
    }

    /**
     * This method serves the purpouse of identifying the language used in a string that is passed as a paramether.
     *
     * @param task - the task that is meant to be analyzed.
     * @return of a string of the most probable language found.
     * @throws IOException - thrown by IndexReader class if some sort of I/O problem occurred
     */

    public Language analyze(Task task) throws IOException, ParseException {
        IndexSearcher.setMaxClauseCount(MAX_TOKENS);

            Query q = new QueryParser("dictionary", analyzer).parse(task.getInputUrl().getTextOfUrl().getTextContent());
            TopDocs docs = searcher.search(q, HITS_PER_PAGE);
            ScoreDoc[] hits = docs.scoreDocs;
            return Language.valueOf(searcher.doc(hits[0].doc).get("language"));

    }






}
