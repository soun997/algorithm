import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        final int INF = 1001;

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int input = Integer.parseInt(st.nextToken());
                    if (input == 0){
                        dp[i][j] = INF;
                        continue;
                    }
                    dp[i][j] = input;
                }
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    if (i == k){
                        continue;
                    }
                    for (int j = 0; j < N; j++) {
                        if (i == j){
                            dp[i][j] = 0;
                            continue;
                        }
                        if (j == k){
                            continue;
                        }
                        dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < N; j++) {
                    sum += dp[i][j];
                    if (sum > min){
                        break;
                    }
                }
                min = Math.min(min, sum);
            }

            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
}