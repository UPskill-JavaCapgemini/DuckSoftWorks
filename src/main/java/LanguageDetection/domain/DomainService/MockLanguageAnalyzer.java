/*
package LanguageDetection.domain.DomainService;

import java.io.IOException;
import java.util.Locale;

public class MockLanguageAnalyzer extends LanguageAnalyzer{
    */
/**
     * This constructor creates a new instance of the SimpleAnalyse.
     * It uses the Directory to indicate where the IndexReader should act. For that,
     * the Directory comunicates to the DicrionaryService and uses one of its instances to
     * get the correct directory.
     * Then creates an IndexSearcher instance (searcher) that recieves a IndexReader
     * instance (reader) as a paramether.
     *
     * @throws IOException - thrown by IndexReader class if some sort of I/O problem occurred
     *//*

    public MockLanguageAnalyzer() throws IOException {
    }

   @Override
   public String cleanUpInputText(String text) {
       return text.trim().toLowerCase(Locale.ROOT)
               .replaceAll("[^a-zA-Z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u024F]", " ")
               .replaceAll("\\s+", " "); // MULTIPLE_WHITESPACE
   }
}
*/
