import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], 0);
        }
        for (int[] result : results) {
            dist[result[0]][result[1]] = 1;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] == 1 && dist[k][j] == 1) { // i와 k, k와 j의 대전이 승부가 났다면
                        dist[i][j] = 1;
                    }
                }
            }            
        }
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }
                if (dist[i][j] == 1 || dist[j][i] == 1) {
                    count++;
                }
            }
            if (count == n - 1) {
                answer++;
            }
        }
        return answer;
    }
}