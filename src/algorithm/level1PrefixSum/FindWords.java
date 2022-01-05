package algorithm.level1PrefixSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindWords {
    /**
     * @param str:  the string
     * @param dict: the dictionary
     * @return: return words which  are subsequences of the string
     */
    public List<String> findWords(String str, List<String> dict) {
        List<String> results = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return results;
        }
        if (dict == null || dict.size() == 0) {
            return results;
        }
        Map<Character, List<Integer>> charToIndexes = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (!charToIndexes.containsKey(str.charAt(i))) {
                charToIndexes.put(str.charAt(i), new ArrayList<>());
            }
            charToIndexes.get(str.charAt(i)).add(i);
        }
        for (String word : dict) {
            if (isSequence(word, str, charToIndexes)) {
                results.add(word);
            }
        }
        return results;
    }

    private boolean isSequence(String word, String str, Map<Character, List<Integer>> charToIndexes) {

        return false;
    }
}
