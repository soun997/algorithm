import java.util.*;

class Solution {
    private final char[] vowels = new char[] { 'A', 'E', 'I', 'O', 'U'};
    private final StringBuilder sb = new StringBuilder();
    private int cur = 0;
    private int answer = -1;
    
    public int solution(String word) {
        findOrder(0, word);
        return answer;
    }
    
    private void findOrder(int k, String word) {
        if (k == 5) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            sb.append(vowels[i]);
            cur++;
            if (word.equals(sb.toString())) {
                answer = cur;
                return;
            }
            findOrder(k + 1, word);
            sb.deleteCharAt(k);
        }
    }
}