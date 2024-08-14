import java.util.*;

class Solution {
    
    Map<Character, List<Integer>> symbols = new LinkedHashMap<>();
    StringBuilder answer = new StringBuilder();
    
    public String solution(String sentence) {
        if (sentence.contains(" ")) {
            return "invalid";
        }
        if (isUpperCase(sentence)) {
            return sentence;
        }
        findPositionOfSymbol(sentence);
        
        int idx = 0;
        int lastStart = -1, lastEnd = -1;
        for (List<Integer> position : symbols.values()) {
            int count = position.size();
            int start = position.get(0);
            int end = position.get(count - 1);
            
            if (count == 1 || count >= 3) { // rule1
                if (gapIsNot2(position) || 
                    isOutOfBounds(sentence, start - 1, end + 1)) {
                    return "invalid";
                }
                if (start > lastStart && end < lastEnd) { // rule2 내부의 rule1
                    continue;
                }
                if (idx > start - 1) {
                    return "invalid";
                }
                appendAnswer(sentence, idx, start - 1);
                appendAnswer(sentence, start - 1, end + 2);
                idx = end + 2;
                lastStart = start;
                lastEnd = end;
            }
            if (count == 2) {   // rule2?
                int gap = end - start;
                if (gap < 2) {
                    return "invalid";   // 기호는 연속 불가
                }
                if (start > lastStart && end < lastEnd) { // rule2 내부의 rule1만 가능
                    if (gapIsNot2(position) ||
                       start - lastStart != 2 || lastEnd - end != 2) {
                        return "invalid";
                    }
                    continue;
                }
                if (idx > start + 1) {
                    return "invalid";
                }
                appendAnswer(sentence, idx, start + 1);
                appendAnswer(sentence, start + 1, end);
                idx = end + 1;
                lastStart = start;
                lastEnd = end;
            }
        }
        String postfix = sentence.substring(idx, sentence.length());
        return answer.append(postfix).toString().trim();
    }
    
    private boolean isUpperCase(String sentence) {
        for (char c : sentence.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }
    
    private void findPositionOfSymbol(String sentence) {
        for (int i = 0; i < sentence.length(); i++) {
            char symbol = sentence.charAt(i);
            if (Character.isLowerCase(symbol)) {    // 기호라면 map에 위치 저장
                if (!symbols.containsKey(symbol)) {
                    symbols.put(symbol, new ArrayList<>());
                }
                symbols.get(symbol).add(i);
            }
        }
    }
    
    private boolean isOutOfBounds(String sentence, int start, int end) {
        return start < 0 || end >= sentence.length();
    }
    
    private boolean gapIsNot2(List<Integer> position) {
        for (int i = 0; i < position.size() - 1; i++) {
            if (position.get(i + 1) - position.get(i) != 2) {
                return true;
            }
        }
        return false;
    }
    
    private void appendAnswer(String sentence, int start, int end) {
        String word = sentence.substring(start, end).replaceAll("[a-z]", "");
        if (!word.equals("")) {
            answer.append(word).append(" ");
        }
    }
}