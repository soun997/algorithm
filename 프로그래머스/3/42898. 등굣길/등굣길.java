import java.util.*;

class Solution {
    private static final int DIVIDER = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < puddles.length; i++) {
            if (puddles[i].length > 0) {
                int c = puddles[i][0] - 1;
                int r = puddles[i][1] - 1;
                dp[r][c] = 0;   
            }
        }
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (dp[i][0] == 0) {
                continue;
            }
            dp[i][0] = dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            if (dp[0][i] == 0) {
                continue;
            }
            dp[0][i] = dp[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] == 0) {
                    continue;
                }
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % DIVIDER;
            }
        }
        return dp[n - 1][m - 1];
    }
}