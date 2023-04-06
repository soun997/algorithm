import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, R;
    static int[][] dp;
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        System.out.println(result);
    }
}