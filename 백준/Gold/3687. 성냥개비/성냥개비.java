import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    static final int[] NEED = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6, 6};

    static int T;
    static int N;   // 성냥개비의 개수
    static List<MatchStickNumber> decreaseOrderNeed;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            decreaseOrderNeed = new ArrayList<>();
            dp = new long[101];
            Arrays.fill(dp, Long.MAX_VALUE);

            for (int i = 0; i <= 9; i++) {
                decreaseOrderNeed.add(new MatchStickNumber(i, NEED[i]));
            }
            Collections.sort(decreaseOrderNeed);

            // 1과 7만을 이용해서 가장 큰 수 만들기
            String max = makeMaxNumber();

            // dp를 이용해서 가장 작은 수 만들기
            long min = makeMinNumber();

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

    static long makeMinNumber(){

        // STEP1 초기 dp 테이블 채우기
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 0;
        dp[7] = 8;

        // STEP2 dp 수행
        for (int i = 8; i <= N; i++){
            for (int j = 2; j <= i - 2; j++){
                long prefix = dp[j];
                long postfix = dp[i - j];
                if (dp[j] == 0){
                    prefix = 6;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(prefix).append(postfix);
                dp[i] = Math.min(dp[i], Long.parseLong(sb.toString()));
            }
        }

        return dp[N] == 0 ? 6 : dp[N];
    }

    static class MatchStickNumber implements Comparable<MatchStickNumber> {

        int number;
        int need;

        public MatchStickNumber(int number, int need) {
            this.number = number;
            this.need = need;
        }

        @Override
        public int compareTo(MatchStickNumber other) {
            if (this.need == other.need){
                return Integer.compare(this.number, other.number);
            }
            return -1 * Integer.compare(this.need, other.need);
        }
    }
}