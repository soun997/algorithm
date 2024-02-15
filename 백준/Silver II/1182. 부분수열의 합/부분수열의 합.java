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
        System.out.println(total);
    }

    static void powerSet(int cnt, int idx) {
        if (idx == N) {
            return;
        }
        for (int i = idx; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            if (isPossible()) {
                total++;
            }
            powerSet(cnt + 1, i + 1);
            visited[i] = false;
        }
    }

    static boolean isPossible() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                sum += numbers[i];
            }
        }
        return (sum == S);
    }
}