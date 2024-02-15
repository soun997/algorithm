import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 부분수열의 합
 */
public class Main {

    static int N, S;
    static int[] numbers = new int[20];
    static boolean[] visited = new boolean[20];
    static int total = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        powerSet(0, 0);
        // 공집합을 빼주어야 함
        if (S == 0) {
            total--;
        }
        System.out.println(total);
    }

    static void powerSet(int idx, int sum) {
        if (idx == N) {
            if (sum == S) {
                total++;
            }
            return;
        }
        /**
         * 부분집합을 구하는 방법
         * 원소의 개수가 N개일 때,
         * 각각의 원소는 포함될 수도 있고 포함되지 않을 수도 있다.
         * 그러므로 경우의 수는 2^N (공집합 포함)
         */
        visited[idx] = false;
        powerSet(idx + 1, sum);
        visited[idx] = true;
        powerSet(idx + 1, sum + numbers[idx]);
    }
}