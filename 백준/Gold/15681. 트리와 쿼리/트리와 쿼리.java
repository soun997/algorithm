import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, R, Q;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dp = new int[N + 1];

        // tree 리스트 초기화
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
            dp[i] = 1;
        }

        // 양방향 그래프를 만든다
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(R);

        for (int i = 0; i < Q; i++) {
            int root = Integer.parseInt(br.readLine());
            result.append(dp[root]).append("\n");
        }
        System.out.println(result);
    }

    static int dfs(int parent){
        visited[parent] = true;

        for (int i = 0; i < tree[parent].size(); i++) {
            int child = tree[parent].get(i);
            if (visited[child]) {
                continue;
            }
            dp[parent] = dp[parent] + dfs(child);
        }

        return dp[parent];
    }
}