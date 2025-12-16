import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] counts = new int[e + 1];
        counts[1] = 1;
        for (int i = 2; i<= e; i++) {
            counts[i] += 2;
        }
        for (int i = 2; i <= e; i++) {
            for (int j = 2; i * j <= e; j++) {
                counts[i * j]++;
            }
        }
        int[] dp = new int[e + 1];
        int maxNum = Integer.MAX_VALUE;
        int max = -1;
        for (int k = e; k >= 1; k--) {
            if (counts[k] >= max && maxNum >= k) {
                dp[k] = k;
                maxNum = k;
                max = counts[k];
            } else {
                if (k + 1 <= e) {
                    dp[k] = dp[k + 1];   
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = dp[starts[i]];
        }
        
        return answer;
    }
}