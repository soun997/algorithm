import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static long[] S;
    static Map<Long, Long> map = new HashMap<>();
    static long total = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            S[i] = S[i - 1] + num;

            if (S[i] == K) {
                total++;
            }
            if (map.containsKey(S[i] - K)) {
                total += map.get(S[i] - K);
            }

            if (map.containsKey(S[i])) {
                map.put(S[i], map.get(S[i]) + 1);
            } else {
                map.put(S[i], 1L);
            }
        }
        System.out.println(total);
    }
}