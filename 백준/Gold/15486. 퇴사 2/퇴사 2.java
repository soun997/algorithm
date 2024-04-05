import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] T = new int[1_500_002];
    static int[] P = new int[1_500_002];
    static int[] dp = new int[1_500_002];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i >= 1; i--) {
            if (i + T[i] > N + 1) {
                dp[i] = dp[i + 1];
                continue;
            }
            dp[i] = Math.max(dp[i + 1], dp[i + T[i]] + P[i]);
        }

        System.out.println(dp[1]);
    }
}