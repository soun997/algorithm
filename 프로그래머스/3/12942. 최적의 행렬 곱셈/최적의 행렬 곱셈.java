import java.util.*;

class Solution {
    public int solution(int[][] matrixSizes) {
        int n = matrixSizes.length;
        int[][] dp = new int[n][n]; // dp[i][j]: i~j 구간의 최소 연산 횟수
        for (int len = 1; len < n; len++) { // 구간의 크기
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                // i~j 구간을 k를 기준으로 분할
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k+1][j] 
                        + matrixSizes[i][0] * matrixSizes[k][1] * matrixSizes[j][1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n-1];
    }
}

/*
행렬 연산 -> 순서에 따라 곱하기 연산 횟수 바뀜
5x3
3x10
10x6
---
5x3x10 = 150
결과: 5x10

5x10x6 = 300

그러므로 150 + 300 = 400
---
3x10x6 = 180
결과: 3x6

5x3x6 = 90

그러므로 180 + 90 = 270

---
결과 행렬이 작아야 이득임

PQ를 사용한다.
axb일 때
1) a가 작은 순으로 정렬
2) b가 작은 순으로 정렬
*/