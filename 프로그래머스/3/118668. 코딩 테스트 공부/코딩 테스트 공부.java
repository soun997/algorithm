import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = alp;
        int maxCop = cop;
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        // dp[i][j]: alp가 i, cop가 j일 때 소요되는 최소시간
        int[][] dp = new int[301][301];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);    // alp 높이기
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);    // cop 높이기
                for (int[] problem : problems) {    // 문제 풀기
                    int reqAlp = problem[0];
                    int reqCop = problem[1];
                    int rwdAlp = problem[2];
                    int rwdCop = problem[3];
                    int cost = problem[4];
                    if (i >= reqAlp && j >= reqCop) {
                        int targetAlp = Math.min(i + rwdAlp, maxAlp);
                        int targetCop = Math.min(j + rwdCop, maxCop);
                        dp[targetAlp][targetCop] = Math.min(dp[targetAlp][targetCop], dp[i][j] + cost);   
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}