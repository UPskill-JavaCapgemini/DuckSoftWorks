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

public class LanguageSearchProbability {
    public static void main(String[] args) throws IOException, ParseException {

        //Classe que analisa o ficheiro e extrai as keywords (tokenizer)
        //StandardAnalyzer é um tipo de analyser com filtro standard

        Analyzer analyzer = new SimpleAnalyzer();

        /*Analyzer analyzer = CustomAnalyzer.builder()
                .withTokenizer(NGramTokenizerFactory.class, "minGramSize", "2", "maxGramSize", "3")
                .build();*/

        //Classe que é usada para guardar os indices criados pelo analyser
        //Neste caso estão a ser guardados localmente porque está preparado para ficheiros de grande
        //dimensão, mas poderia ser apenas carregado na memória RAM com a Classe RAMDirectory

        Directory directory = FSDirectory.open(Paths.get("indexedFiles"));
        //Classe que configura como será indexado o documento

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setSimilarity(new ClassicSimilarity());
        //Classe responsável pela criação dos indices - Esta classe só "percebe" objetos da classe Document

        IndexWriter writer = new IndexWriter(directory, config);
        writer.deleteAll();
        //Adição de documentos para indexação - Neste caso, os dicionários
        addDoc(writer, "english", Paths.get("inputFiles/en-common.wl"));
        addDoc(writer, "portuguese", Paths.get("inputFiles/pt_PT.wl"));
        addDoc(writer, "spanish", Paths.get("inputFiles/es.wl"));

        writer.close();

        System.out.println("Insert text:");
        Scanner leitor = new Scanner(System.in);

        //query - Leitor para verificação. Ou usar a linha que está comentada e passar um ficheiro .txt
        String query = leitor.nextLine();
        //String query = new String(Files.readAllBytes(Paths.get("inputFiles/por.txt")));

        String cleanedString = cleanUpInputText(query);
        //Responsavel pela identificacao daquilo que será procurado
        Query q = new QueryParser("dictionary", analyzer).parse(cleanedString);

        //search
        int hitsPerPage = 100;
        //Classe responsável pela leitura dos indices(index)
        IndexReader reader = DirectoryReader.open(directory);
        //Classe responsavel pela procura dos indices
        IndexSearcher searcher = new IndexSearcher(reader);
        //Classe responsavel por receber hits
        TopDocs docs = searcher.search(q, hitsPerPage);
        //docs.scoreDocs recebe o score dos hits em por cada documento que encontrou
        ScoreDoc[] hits = docs.scoreDocs;

        Map<String, Float> map = new TreeMap<>();

        //results
        //Indica em quantos documentos foram encontradas as palavras
        System.out.println("Found in " + hits.length + " dictionaries.");

        Float valor = 0F;
        //Adiciona num Map as linguagens e o score de cada linguagem
        for (int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            Float score = hits[i].score;
            String lang = d.get("language");

            map.put(lang, score);
            valor = valor + score;
        }
        //Novo Map ordenado por valores de score
        Map<String, Float> sortedMap = sortByValue(map);


        //Mapa iterado para calcular a percentagem para cada língua
        for (Map.Entry<String, Float> entry : sortedMap.entrySet()) {
            Float resultado = (entry.getValue() * 100) / valor;
            Float maiorProbabilidade = 0F;
            String key = null;
            if (maiorProbabilidade < resultado) {
                maiorProbabilidade = resultado;
                key = entry.getKey();
            }
            System.out.printf("Probability of %s is %.2f %% \n", key, maiorProbabilidade);

        }
    }

    //Método de ordenação do mapa por valor(Float)
    private static Map<String, Float> sortByValue(Map<String, Float> unsortMap) {

        // 1. Converter Map para Lista do Map
        List<Map.Entry<String, Float>> list =
                new LinkedList<Map.Entry<String, Float>>(unsortMap.entrySet());

        // 2. Ordenação com a classe Collections
        Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop para inserção do Map num novo ordenado
        Map<String, Float> sortedMap = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }


        return sortedMap;
    }

    //Necessário depois do Lucene 8 (?) para indicar de que forma os documentos sao guardados
    public static final FieldType TYPE_STORED = new FieldType();

    static {
        TYPE_STORED.setIndexOptions(DOCS_AND_FREQS);
        TYPE_STORED.setTokenized(true);
        TYPE_STORED.setStored(true);
        TYPE_STORED.setStoreTermVectors(true);
        TYPE_STORED.setStoreTermVectorPositions(true);
        TYPE_STORED.freeze();

    }

    //Método de adicionar Documentos
    private static void addDoc(IndexWriter w, String language, Path dictionary) throws IOException {
        Document doc = new Document();

        // Apenas uma indicação para a forma como o documento é guardado - não é tokenizado nem criado vetores
        doc.add(new StringField("language", language, Field.Store.YES));

        //Adição do conteudo dos ficheiros para indexar e tokenizar - Por ter "TYPE_STORED"
        Field field = new Field("dictionary",  new String(Files.readAllBytes(dictionary)), TYPE_STORED);
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
