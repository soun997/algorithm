import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        int[] quantities = new int[10_000_001];
        for (int size : tangerine) {
            quantities[size]++;
        }
        Arrays.sort(quantities);
        
        int answer = 0;
        for (int i = quantities.length - 1; i >= 0; i--) {
            k -= quantities[i];
            answer++;
            if (k <= 0) {
                break;
            }
        }
        return answer;
    }
}