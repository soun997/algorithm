import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] costs = new int[N][3];
        int[][] dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = 1000 * 1000;
        for (int c = 0; c < 3; c++) {

            // 초깃값 세팅
            for (int i = 0; i < 3; i++) {
                if (i == c) {
                    dp[0][i] = costs[0][i];
                }
                else {
                    dp[0][i] = 1000 * 1000;
                }
            }

            for (int i = 1; i < N; i++){
                dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            }

            for (int i = 0; i < 3; i++) {
                if (i == c){
                    continue;
                }
                min = Math.min(min, dp[N - 1][i]);
            }
        }
        System.out.println(min);
    }
}
