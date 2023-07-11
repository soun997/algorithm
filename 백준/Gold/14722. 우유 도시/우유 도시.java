import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] milkCity;
    static int[][] dp;
    static int[][] drank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        milkCity = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                milkCity[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (milkCity[0][0] == 0) {
            dp[0][0] = 1;
        } else {
            milkCity[0][0] = -1;
        }

        // 테두리 먼저 채우기
        for (int i = 1; i < N; i++) {
            if (milkCity[i][0] == (milkCity[i - 1][0] + 1) % 3) {
                dp[i][0] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = dp[i - 1][0];
                milkCity[i][0] = milkCity[i - 1][0];
            }
            if (milkCity[0][i] == (milkCity[0][i - 1] + 1) % 3) {
                dp[0][i] = dp[0][i - 1] + 1;
            } else {
                dp[0][i] = dp[0][i - 1];
                milkCity[0][i] = milkCity[0][i - 1];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                int up = 0;
                int left = 0;
                int prevUp = (milkCity[i - 1][j] + 1) % 3;
                int prevLeft = (milkCity[i][j - 1] + 1) % 3;
                if (milkCity[i][j] == prevUp) {
                    up = dp[i - 1][j] + 1;
                } else {
                    up = dp[i - 1][j];
                    prevUp = milkCity[i - 1][j];
                }
                if (milkCity[i][j] == prevLeft) {
                    left = dp[i][j - 1] + 1;
                } else {
                    left = dp[i][j - 1];
                    prevLeft = milkCity[i][j - 1];
                }

                if (up < left) {
                    dp[i][j] = left;
                    milkCity[i][j] = prevLeft;
                } else {
                    dp[i][j] = up;
                    milkCity[i][j] = prevUp;
                }
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }
}