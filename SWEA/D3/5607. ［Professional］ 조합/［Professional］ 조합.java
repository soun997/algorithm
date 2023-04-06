import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, R;
    static int[][] dp;
    static final long MOD = 1_234_567_891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            long result = 1;
            //
            for (int i = R + 1; i <= N; i++) {
                result = (result * i) % MOD;
            }

            long a = 1;
            for (int i = 1; i <= N - R; i++) {
                a = (a * i) % MOD;
            }
            long p = MOD - 2;
            // 분할정복
            while (p > 0) {
                // 홀수일 경우
                if (p % 2 == 1) {
                    result = (result * a) % MOD;
                }
                p = p / 2;
                a = (a * a) % MOD;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

//    static int factorial(int N, int R){
//
//        if (dp[N][R] > 0) {
//            return dp[N][R];
//        }
//
//        if (N == R || R == 0){
//            return dp[N][R] = 1;
//        }
//
//        return dp[N][R] = factorial(N - 1, R - 1) + factorial(N - 1, R);
//    }

}