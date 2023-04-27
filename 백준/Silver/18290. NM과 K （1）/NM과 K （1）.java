import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    NM과 K (1)
 */
public class Main {

    static int N, M, K;
    static int[][] matrix;
    static boolean[][] visited;
    static int max;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        matrix = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        max = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 1, 0, 0);

        System.out.println(max);
    }


    static void dfs(int x, int y, int cnt, int sum) {

        if (cnt == K) {
            max = Math.max(max, sum);
            return;
        }

        for (int r = x; r <= N; r++) {
            for (int c = (r == x ? y : 1); c <= M; c++) {
                // 방문했거나, 인접한 칸이라면
                if (visited[r][c] || isAdjustArea(r, c)){
                    continue;
                }

                visited[r][c] = true;
                dfs(r, c, cnt + 1, sum + matrix[r][c]);
                visited[r][c] = false;
            }
        }

    }

    static boolean isAdjustArea(int x, int y){

        if (visited[x - 1][y] || visited[x][y - 1]){
            return true;
        }
        return false;
    }
}