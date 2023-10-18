import java.util.*;

class Solution {
    
    public int[] solution(int n, int s) {
        
        if (s / n == 0) {
            return new int[]{-1};
        }
        int[] answer = new int[n];
        int quotient = s / n;
        int remain  = s % n;
        for (int i = 0; i < n - remain; i++) {
            answer[i] = quotient;
        }
        for (int i = 0; i < remain; i++) {
            answer[n - remain + i] = quotient + 1;
        }
        
        return answer;
    }
}