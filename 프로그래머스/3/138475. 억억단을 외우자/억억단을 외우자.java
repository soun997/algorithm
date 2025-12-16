import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] counts = new int[e + 1];
        counts[1] = 1;
        for (int i = 2; i <= e; i++) {
            counts[i] += 2;
        }
        for (int i = 2; i <= e; i++) {
            for (int j = 2; i * j <= e; j++) {
                counts[i * j]++;
            }
        }
        int[] dp = new int[e + 1];  // dp[i] = 최솟값이 i일 때 가장 많이 등장한 수
        int max = -1;
        for (int k = e; k >= 1; k--) {  // 거꾸로 탐색
            // 해당 숫자 등장 횟수가 역대 최댓값보다 더 크거나 같다 -> 갱신
            if (counts[k] >= max) {
                dp[k] = k;
                max = counts[k];
            } else {
                dp[k] = dp[k + 1];   
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