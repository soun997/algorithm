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
        permutation(0);
        System.out.println(min);
    }

    static void permutation(int cnt) {

        if (cnt == N) {
            min = Math.min(min, getDiscountedPrice());
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            orders[cnt] = i;
            permutation(cnt + 1);
            visited[i] = false;
        }
    }

    static int getDiscountedPrice() {

        int[] copiedPrices = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            copiedPrices[i] = prices[i];
        }
        int totalPrice = 0;
        for (int idx : orders) {
            totalPrice += copiedPrices[idx];
            for (int i = 0; i < discounts[idx].length; i++) {
                if (copiedPrices[discounts[idx][i][0]] - discounts[idx][i][1] < 1) {
                    copiedPrices[discounts[idx][i][0]] = 1;
                    continue;
                }
                copiedPrices[discounts[idx][i][0]] -= discounts[idx][i][1];
            }
        }
        return totalPrice;
    }
}