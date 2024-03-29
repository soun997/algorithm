import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<Integer>[] tree;
    static int[] population;
    static boolean[] visited;
    static int[][] dp;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        population = new int[N + 1];
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            tree[i] = new ArrayList<>();
        }

        // 양방향 그래프를 만든다
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int parent){
        visited[parent] = true;

        for (int i = 0; i < tree[parent].size(); i++) {
            int child = tree[parent].get(i);
            if (visited[child]) {
                continue;
            }

            dfs(child);
            dp[parent][0] = dp[parent][0] + dp[child][1];
            dp[parent][1] = dp[parent][1] + Math.max(dp[child][0], dp[child][1]);
        }

        visited[parent] = false;
        dp[parent][0] = dp[parent][0] + population[parent];
    }
}