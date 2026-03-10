import java.util.*;

public class PlagiarismDetector {

    private Map<String, Set<String>> ngramIndex = new HashMap<>();
    private int N = 5;

    public void indexDocument(String docId, String text) {

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - N; i++) {

            String ngram = String.join(" ", Arrays.copyOfRange(words, i, i + N));

            ngramIndex
                    .computeIfAbsent(ngram, k -> new HashSet<>())
                    .add(docId);
        }
    }

    public double checkSimilarity(String text, String docId) {

        String[] words = text.split(" ");
        int matches = 0;
        int total = 0;

        for (int i = 0; i <= words.length - N; i++) {

            total++;

            String ngram = String.join(" ", Arrays.copyOfRange(words, i, i + N));

            if (ngramIndex.containsKey(ngram)
                    && ngramIndex.get(ngram).contains(docId)) {

                matches++;
            }
        }

        return (matches * 100.0) / total;
    }
}