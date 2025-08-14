import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;
        int[][] dp = new int[height + 1][height + 1];
        dp[1][1] = triangle[0][0];
        for (int i = 2; i <= height; i++) {
            for (int j = 1; j <= triangle[i - 1].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i - 1][j - 1];
            }
        }
        return Arrays.stream(dp[height]).max().getAsInt();
    }
}