import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] counseling;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        counseling = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            counseling[i][0] = Integer.parseInt(st.nextToken());
            counseling[i][1] = Integer.parseInt(st.nextToken());
        }
        backtracking(0, 0);
        System.out.println(max);
    }

    static void backtracking(int cnt, int profit) {

        if (cnt == N) {
            max = Math.max(max, profit);
            return;
        }

        if (cnt + counseling[cnt][0] <= N) {
            backtracking(cnt + counseling[cnt][0], profit + counseling[cnt][1]);
        }
        backtracking(cnt + 1, profit);
    }
}