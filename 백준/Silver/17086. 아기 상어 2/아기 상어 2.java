import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] sea;
    static int max;
    // 8방 탐색
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sea = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (sea[i][j] == 1){
                    continue;
                }
                max = Math.max(max, bfs(i, j));
            }
        }

        System.out.println(max);
    }

    static int bfs(int x, int y){
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, 0});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (sea[cur[0]][cur[1]] == 1){
                return cur[2];
            }
            for (int d = 0; d < 8; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny) || visited[nx][ny]){
                    continue;
                }
                q.offer(new int[]{nx, ny, cur[2] + 1});
                visited[nx][ny] = true;
            }
        }

        return 0;
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }
        return true;
    }
}
