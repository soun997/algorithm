import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int H, W;
    static int[][] map;
    static boolean[][][] visited;
    static int min;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] ex = {-1, -2, 1, 2, -2, -1, 1, 2};
    static int[] ey = {-2, -1, 2, 1, 1, 2, -2, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;
        System.out.println(bfs(0, 0, K));
    }

    static int bfs(int x, int y, int k){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, k, 0});
        visited[x][y][k] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == H - 1 && cur[1] == W - 1){
                return cur[3];
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny) || visited[nx][ny][cur[2]] || map[nx][ny] == 1){
                    continue;
                }
                visited[nx][ny][cur[2]] = true;
                q.offer(new int[]{nx, ny, cur[2], cur[3] + 1});
            }

            if (cur[2] > 0){
                for (int d = 0; d < 8; d++) {
                    int nx = cur[0] + ex[d];
                    int ny = cur[1] + ey[d];
                    if (!check(nx, ny) || visited[nx][ny][cur[2] - 1] || map[nx][ny] == 1){
                        continue;
                    }
                    visited[nx][ny][cur[2] - 1] = true;
                    q.offer(new int[]{nx, ny, cur[2] - 1, cur[3] + 1});
                }
            }
        }
        return -1;
    }
    static boolean check(int x, int y){
        if (x < 0 || x >= H || y < 0 || y >= W){
            return false;
        }
        return true;
    }

}
