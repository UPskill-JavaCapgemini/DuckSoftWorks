package LanguageDetection.application.services;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class ServiceAnalyzer {

    private static final int HITS_PER_PAGE = 100;
    private static SimpleAnalyzer analyzer;
    private static Directory directory;
    private static IndexReader reader;

    public ServiceAnalyzer() throws IOException {
        this.analyzer = new SimpleAnalyzer();
        this.directory = DictionaryService.getInstance().directory;
    }

    //Documents are the unit of indexing and search. A Document is a set of fields. Each field has a name and a textual value.
    // A field may be stored with the document, in which case it is returned with search hits on the document.

    static String analyze(String query) throws ParseException, IOException {
        IndexSearcher.setMaxClauseCount(Integer.MAX_VALUE);
        reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        Query q = new QueryParser("dictionary", analyzer).parse(query);


        TopDocs docs = searcher.search(q, HITS_PER_PAGE);
        ScoreDoc[] hits = docs.scoreDocs;

        String language = searcher.doc(hits[0].doc).get("language");
        return language;
    }
}
