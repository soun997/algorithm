import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] sequence = new int[1001];
    static long[] dp = new long[1001];
    static long max = 0L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            dp[i] = sequence[i];
            for (int j = 1; j <= i; j++) {
                if (sequence[i] > sequence[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + sequence[i]);
                }
                max = Math.max(max, dp[i]);
            }
        }
        System.out.println(max);
    }
}