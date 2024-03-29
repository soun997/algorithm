import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int T;
    static int N;   // 성냥개비의 개수
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        makeMinNumber();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            // 1과 7만을 이용해서 가장 큰 수 만들기
            String max = makeMaxNumber();

            // dp를 이용해서 가장 작은 수 만들기
            long min = dp[N];

            System.out.println(min + " " + max);
        }

    }

    static String makeMaxNumber(){
        StringBuilder sb = new StringBuilder();
        // 짝수 -> 최상위수가 1
        if (N % 2 == 0){
            for (int i = 0; i < N / 2; i++){
                sb.append("1");
            }
        }
        // 홀수 -> 최상위수가 7
        if (N % 2 == 1){
            for (int i = 0; i < N / 2 - 1; i++){
                sb.append("1");
            }
            sb.append("7");
        }

        return sb.reverse().toString();
    }

    static void makeMinNumber(){

        // STEP1 초기 dp 테이블 채우기
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 0;
        dp[7] = 8;

        // STEP2 dp 수행
        for (int i = 8; i <= 100; i++){
            for (int j = 2; j <= i - 5; j++){
                long prefix = dp[j] == 0 ? 6 : dp[j];
                long postfix = dp[i - j];
                dp[i] = Math.min(dp[i], prefix * (long)Math.pow(10, postfix == 0 ? 1 : (long)Math.log10(postfix) + 1) + postfix);
            }
        }

        dp[6] = 6;
    }
}