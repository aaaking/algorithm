package datastructor;

import java.util.regex.Pattern;

class WordDictionary {

    Trie root = new Trie();

    public WordDictionary() {
    }

    public void addWord(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int i, Trie node) {
        if (i == word.length()) {
            return node != null && node.isEnd;
        }
        char c = word.charAt(i);
        if (Character.isLetter(c)) {
            int index = c - 'a';
            Trie child = node.children[index];
            if (child != null && dfs(word, i + 1, child)) {
                return true;
            }
        } else {
            for (Trie child : node.children) {
                if (child != null && dfs(word, i + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
