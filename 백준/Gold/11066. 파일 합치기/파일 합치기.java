import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int[][] dp;
    static int[] totalSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            K = Integer.parseInt(br.readLine());
            dp = new int[K + 1][K + 1];
            totalSize = new int[K + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= K; i++) {
                int file = Integer.parseInt(st.nextToken());
                totalSize[i] = totalSize[i - 1] + file;
            }

            mergeFile();

            result.append(dp[1][K]).append("\n");
        }
        System.out.println(result);
    }

    static void mergeFile(){
        for (int k = 1; k < K; k++) {
            for (int i = 1; i + k <= K; i++) {
                dp[i][i + k] = Integer.MAX_VALUE;
                for (int j = i; j < i + k; j++) {
                    dp[i][i + k] = Math.min(dp[i][i + k], dp[i][j] + dp[j + 1][i + k] + (totalSize[i + k] - totalSize[i - 1]));
                }
            }
        }
    }
}