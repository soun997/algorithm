import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Integer>[] tree;
    static int[] dp;
    static int total;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        dp = new int[N + 1];
        total = 0;
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            tree[parent].add(child);
            tree[child].add(parent);
        }

        dfs(1);

        System.out.println(total - getSummation(N));
    }

    static int dfs(int cur){

        dp[cur] = 1;
        for (int child : tree[cur]){
            if (dp[child] == 0) {
                dp[cur] = dp[cur] + dfs(child);
            }
        }
        total = total + getSummation(N) - getSummation(N - dp[cur]);
        return dp[cur];
    }

    static int getSummation(int n){
        return n * (n - 1) / 2;
    }
}