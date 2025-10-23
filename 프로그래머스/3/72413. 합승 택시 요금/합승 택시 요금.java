import java.util.*;

class Solution {
    
    final int INF = Integer.MAX_VALUE / 2;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int weight = fares[i][2];
            dist[from][to] = weight;
            dist[to][from] = weight;
        }
        for (int k = 1; k <= n; k++) {  // 경유지점
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        // for (int i = 0; i <= n; i++) {
        //     System.out.println(Arrays.toString(dist[i]));
        // }
        int answer = INF;
        for (int i = 1; i <= n; i++) {
            if (dist[s][i] == INF) {
                continue;
            }
            // s -> i까지 경유, 나머지는 따로
            answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return answer;
    }
}