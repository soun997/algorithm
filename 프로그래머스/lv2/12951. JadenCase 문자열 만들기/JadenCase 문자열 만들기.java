import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder result = new StringBuilder();
        String[] words = s.split("\\s+");
        String[] spaces = s.split("[A-Za-z0-9]+");
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i].substring(0, 1).toUpperCase();
            String other = words[i].substring(1, words[i].length()).toLowerCase();
            result.append(first).append(other).append(spaces[i + 1]);
        }
        String first = words[words.length - 1].substring(0, 1).toUpperCase();
        String other = words[words.length - 1].substring(1, words[words.length - 1].length()).toLowerCase();
        result.append(first).append(other);
        if (spaces.length - 1 == words.length) {
            result.append(spaces[spaces.length - 1]);
        }
        
        return result.toString();
    }
}