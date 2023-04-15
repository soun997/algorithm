import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K + 1];
            int[][] dp = new int[K + 1][K + 1];
            int[] totalSize = new int[K + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                totalSize[i] = totalSize[i - 1] + files[i];
            }

            for (int k = 1; k < K; k++) {
                for (int i = 1; i + k <= K; i++) {
                    dp[i][i + k] = Integer.MAX_VALUE;
                    for (int j = i; j < i + k; j++) {
                        dp[i][i + k] = Math.min(dp[i][i + k], dp[i][j] + dp[j + 1][i + k] + (totalSize[i + k] - totalSize[i - 1]));
                    }
                }
            }

            result.append(dp[1][K]).append("\n");
        }
        System.out.println(result);
    }
}