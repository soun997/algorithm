import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] prices;
    // x번 물약을 사면 y번째 물약이 원소만큼 할인된다.
    static boolean[] visited;
    static int[] orders;
    static int[][][] discounts;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prices = new int[N + 1];
        visited = new boolean[N + 1];
        orders = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }
        discounts = new int[N + 1][][];
        for (int i = 1; i <= N; i++) {
            int p = Integer.parseInt(br.readLine());
            discounts[i] = new int[p][2];
            for (int j = 0; j < p; j++) {
                st = new StringTokenizer(br.readLine());
                discounts[i][j][0] = Integer.parseInt(st.nextToken());
                discounts[i][j][1] = Integer.parseInt(st.nextToken());
            }
        }
        permutation(0, 0);
        System.out.println(min);
    }

    static void permutation(int cnt, int total) {

        if (total > min) {
            return;
        }

        if (cnt == N) {
            min = total;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            for (int j = 0; j < discounts[i].length; j++) {
                prices[discounts[i][j][0]] -= discounts[i][j][1];
            }
            if (prices[i] < 1) {
                permutation(cnt + 1, total + 1);
            } else {
                permutation(cnt + 1, total + prices[i]);
            }
            for (int j = 0; j < discounts[i].length; j++) {
                prices[discounts[i][j][0]] += discounts[i][j][1];
            }
            visited[i] = false;
        }
    }
}