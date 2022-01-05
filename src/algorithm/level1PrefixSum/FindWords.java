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
        // save all the indexes of the same character with hashmap
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
        int strIndex = 0, wordIndex = 0;
        while (strIndex < str.length() && wordIndex < word.length()) {
            // record the last index in the string
            // error before
             strIndex = findNextIndex(word.charAt(wordIndex), strIndex, charToIndexes);
             if (strIndex == -1) {
                 break;
             }
             wordIndex += 1;
        }
        return wordIndex == word.length();
    }

    private int findNextIndex(char ch, int index, Map<Character, List<Integer>> charToIndexes) {
        if (!charToIndexes.containsKey(ch)) {
            return -1;
        }
        List<Integer> indexes = charToIndexes.get(ch);
        int left = 0, right = indexes.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (indexes.get(mid) <= index) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        // get the leftmost index in the indexes list
        if (indexes.get(left) >= index) {
            return indexes.get(left);
        }
        if (indexes.get(right) >= index) {
            return indexes.get(right);
        }
        return -1;
    }
}
