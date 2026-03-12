
import java.util.*;

public class Java_week1_ques4 {

    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();
    private HashMap<String, List<String>> documentNgrams = new HashMap<>();
    private int n = 5;

    public void addDocument(String docId, String text) {

        List<String> ngrams = generateNgrams(text);
        documentNgrams.put(docId, ngrams);

        for (String gram : ngrams) {
            ngramIndex.putIfAbsent(gram, new HashSet<>());
            ngramIndex.get(gram).add(docId);
        }
    }

    public void analyzeDocument(String docId) {

        List<String> ngrams = documentNgrams.get(docId);
        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String gram : ngrams) {
            Set<String> docs = ngramIndex.getOrDefault(gram, new HashSet<>());
            for (String d : docs) {
                if (!d.equals(docId)) {
                    matchCount.put(d, matchCount.getOrDefault(d, 0) + 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : matchCount.entrySet()) {

            String otherDoc = entry.getKey();
            int matches = entry.getValue();
            double similarity = (matches * 100.0) / ngrams.size();

            System.out.println("Found " + matches + " matching n-grams with \"" 
                    + otherDoc + "\"");
            System.out.println("Similarity: " + similarity + "%");

            if (similarity > 50) {
                System.out.println("PLAGIARISM DETECTED");
            }
        }
    }

    private List<String> generateNgrams(String text) {

        String[] words = text.toLowerCase().split("\\s+");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder gram = new StringBuilder();
            for (int j = 0; j < n; j++) {
                gram.append(words[i + j]).append(" ");
            }
            grams.add(gram.toString().trim());
        }

        return grams;
    }

    public static void main(String[] args) {

        Java_week1_ques4 detector = new Java_week1_ques4();

        detector.addDocument("essay_089.txt",
                "machine learning is a powerful technology used in modern systems");

        detector.addDocument("essay_092.txt",
                "machine learning is a powerful technology used in modern systems for analysis");

        detector.addDocument("essay_123.txt",
                "machine learning is a powerful technology used in modern systems");

        detector.analyzeDocument("essay_123.txt");
    }
}
