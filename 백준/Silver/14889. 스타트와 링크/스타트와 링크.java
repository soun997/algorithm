import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static boolean[] visited;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;
        dfs(0, 0);
        System.out.println(min);
    }

    static void dfs(int cnt, int now){
        if (cnt == N / 2){
            int start = 0;
            int link = 0;
            for (int i = 0; i < N; i++) {
                if (!visited[i]){
                    continue;
                }
                for (int j = 0; j < N; j++) {
                    if (!visited[j]){
                        continue;
                    }
                    start = start + map[i][j];
                }
            }
            for (int i = 0; i < N; i++) {
                if (visited[i]){
                    continue;
                }
                for (int j = 0; j < N; j++) {
                    if (visited[j]){
                        continue;
                    }
                    link = link + map[i][j];
                }
            }
            min = Math.min(min, Math.abs(start - link));
            return;
        }

        for (int i = now; i < N; i++) {
            visited[i] = true;
            dfs(cnt + 1, i + 1);
            visited[i] = false;
        }
    }
}