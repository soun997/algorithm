import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int gcd = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            gcd = 1;
            getGcd(N, K);
            sb.append('#').append(t).append(' ').append(gcd).append('\n');
        }
        System.out.println(sb);
    }

    private static void getGcd(int fact, int divisor) {
        if (divisor == 1) {
            return;
        }
        for (int i = fact; i > 1; i--) {
            if (divisor % i == 0) {
                gcd *= i;
                fact = Math.min(divisor, i - 1);
                getGcd(fact, divisor / i);
                break;
            }
        }
    }
}