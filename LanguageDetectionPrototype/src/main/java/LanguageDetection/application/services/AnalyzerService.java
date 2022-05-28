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


    private static final int HITS_PER_PAGE = 100;


    public AnalyzerService() throws IOException {
        analyzer = new SimpleAnalyzer();
        reader = DirectoryReader.open(DictionaryService.getInstance().directory);
        searcher = new IndexSearcher(reader);
    }

    String analyze(String query) throws ParseException, IOException {
        IndexSearcher.setMaxClauseCount(Integer.MAX_VALUE);

        Query q = new QueryParser("dictionary", analyzer).parse(query);

        TopDocs docs = searcher.search(q, HITS_PER_PAGE);
        ScoreDoc[] hits = docs.scoreDocs;

        return searcher.doc(hits[0].doc).get("language");
    }
}
