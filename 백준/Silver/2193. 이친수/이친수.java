import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static long[] dp0 = new long[91];
    static long[] dp1 = new long[91];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp0[1] = 0;
        dp1[1] = 1;
        dp0[2] = 1;
        dp1[2] = 0;
        dp0[3] = 1;
        dp1[3] = 1;
        for (int i = 4; i <= N; i++) {
            dp0[i] = dp0[i - 1] + dp1[i - 1];
            dp1[i] = dp0[i - 1];
        }

        System.out.println(dp0[N] + dp1[N]);
    }
}