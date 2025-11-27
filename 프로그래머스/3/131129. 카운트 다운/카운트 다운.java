import java.util.*;

class Solution {
    public int[] solution(int target) {
        // dp[i][0] = 최소 다트 수
        // dp[i][1] = 최대 싱글/불 개수
        int[][] dp = new int[target + 1][2];
        
        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        
        // 한 번에 낼 수 있는 모든 점수 생성
        for (int i = 1; i <= target; i++) {
            // 불
            if (i >= 50 && dp[i - 50][0] != Integer.MAX_VALUE) {
                int newDarts = dp[i - 50][0] + 1;
                int newSingle = dp[i - 50][1] + 1;
                if (dp[i][0] > newDarts || (dp[i][0] == newDarts && dp[i][1] < newSingle)) {
                    dp[i][0] = newDarts;
                    dp[i][1] = newSingle;
                }
            }
            
            // 싱글
            for (int j = 1; j <= 20; j++) {
                if (i >= j && dp[i - j][0] != Integer.MAX_VALUE) {
                    int newDarts = dp[i - j][0] + 1;
                    int newSingle = dp[i - j][1] + 1;
                    if (dp[i][0] > newDarts || (dp[i][0] == newDarts && dp[i][1] < newSingle)) {
                        dp[i][0] = newDarts;
                        dp[i][1] = newSingle;
                    }
                }
            }
            
            // 더블
            for (int j = 1; j <= 20; j++) {
                int score = j * 2;
                if (i >= score && dp[i - score][0] != Integer.MAX_VALUE) {
                    int newDarts = dp[i - score][0] + 1;
                    int newSingle = dp[i - score][1];
                    if (dp[i][0] > newDarts || (dp[i][0] == newDarts && dp[i][1] < newSingle)) {
                        dp[i][0] = newDarts;
                        dp[i][1] = newSingle;
                    }
                }
            }
            
            // 트리플
            for (int j = 1; j <= 20; j++) {
                int score = j * 3;
                if (i >= score && dp[i - score][0] != Integer.MAX_VALUE) {
                    int newDarts = dp[i - score][0] + 1;
                    int newSingle = dp[i - score][1];
                    if (dp[i][0] > newDarts || (dp[i][0] == newDarts && dp[i][1] < newSingle)) {
                        dp[i][0] = newDarts;
                        dp[i][1] = newSingle;
                    }
                }
            }
        }
        
        return dp[target];
    }
}