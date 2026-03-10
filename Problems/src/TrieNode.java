import java.util.*;

class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;
    int frequency = 0;
}

public class AutocompleteSystem {

    TrieNode root = new TrieNode();

    public void insert(String word) {

        TrieNode node = root;

        for (char c : word.toCharArray()) {

            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isWord = true;
        node.frequency++;
    }

    public List<String> search(String prefix) {

        TrieNode node = root;

        for (char c : prefix.toCharArray()) {

            if (!node.children.containsKey(c))
                return new ArrayList<>();

            node = node.children.get(c);
        }

        List<String> results = new ArrayList<>();

        dfs(node, prefix, results);

        return results;
    }

    private void dfs(TrieNode node,
                     String word,
                     List<String> results) {

        if (node.isWord)
            results.add(word);

        for (char c : node.children.keySet()) {

            dfs(node.children.get(c),
                    word + c,
                    results);
        }
    }
}
