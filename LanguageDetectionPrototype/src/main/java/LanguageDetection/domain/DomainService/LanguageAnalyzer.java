package LanguageDetection.domain.DomainService;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;

@Service
public class LanguageAnalyzer implements ILanguageDetector{
    private static final int MAX_TOKENS = Integer.MAX_VALUE;
    static org.apache.lucene.analysis.Analyzer analyzer;
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
    public LanguageAnalyzer() throws IOException {
        analyzer = new SimpleAnalyzer();
        reader = DirectoryReader.open(Dictionary.getInstance().directory);
        searcher = new IndexSearcher(reader);
    }

    /**
     * This method serves the purpouse of identifying the language used in a string that is passed as a paramether.
     * @param query - the string that is analized.
     * @return of a string of the most probable language found.
     * @throws IOException - thrown by IndexReader class if some sort of I/O problem occurred
     */

    public String analyze(String query) throws IOException, ParseException {

        //maximum value of an Integer to allow the most hits possible for an IndexSearcher
        IndexSearcher.setMaxClauseCount(Integer.MAX_VALUE);

        String text = createNewText(query);

        String cleanedUp = cleanUpInputText(text);

        Query q = new QueryParser("dictionary", analyzer).parse(cleanedUp);

        TopDocs docs = searcher.search(q, HITS_PER_PAGE);
        ScoreDoc[] hits = docs.scoreDocs;

        return searcher.doc(hits[0].doc).get("language");
    }

    /**
     * Cleans up the string received via input.
     * Strips the string of multiple whitespaces through the use of a regex.
     *
     * @param text the string that is cleaned up with the regex.
     * @return the cleaned up text.
     */
    protected String cleanUpInputText(String text) {
        return text.trim().toLowerCase(Locale.ROOT)
                .replaceAll("[^a-zA-Z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u024F]", " ")
                //.replaceAll("\\p{P}", "") //PUNCTUATION
                //.replaceAll("\\p{N}", "") // NUMBERS
                .replaceAll("\\s+", " "); // MULTIPLE_WHITESPACE
    }

    private String parseURLtoString(URL url) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String text = "";

        int wordCount = 0;
        String temp;
        while ((temp = in.readLine()) != null && wordCount < MAX_TOKENS) {
            String[] words = temp.split(" ");

            for (String word : words) {
                if (wordCount < MAX_TOKENS) {
                    text += word + " ";
                    wordCount++;
                } else
                    break;
            }
        }
        in.close();
        return cleanUpInputText(text);
    }

    public String createNewText(String stringURL) throws IOException {
        URL url = new URL(stringURL);
        String textBody = parseURLtoString(url);
        return textBody;
    }
}
