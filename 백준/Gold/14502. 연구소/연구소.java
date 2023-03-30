import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] wallPos;
    static int maxSafeArea;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        wallPos = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxSafeArea = Integer.MIN_VALUE;
        combination(0, 0);
        System.out.println(maxSafeArea);
    }

    static void spread(int x, int y, boolean[][] visited){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny) || visited[nx][ny] || map[nx][ny] != 0){
                    continue;
                }
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                map[nx][ny] = 2;
            }
        }
    }

    static int checkSafeArea(int x, int y, boolean[][] visited){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        int safeArea = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny) || visited[nx][ny] || map[nx][ny] != 0){
                    continue;
                }
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                safeArea++;
            }
        }
        return safeArea;
    }

    static int[][] copyMap(){
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; i++) {
            copied[i] = Arrays.copyOf(map[i], map[i].length);
        }
        return copied;
    }

    static boolean check(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }
        return true;
    }

    static void combination(int cnt, int start){
        if (cnt == 3){
            int[][] originMap = copyMap();

            for (int i = 0; i < 3; i++) {
                int x = wallPos[i] / M;
                int y = wallPos[i] % M;
                map[x][y] = 1;
            }

            boolean[][] visited = new boolean[N][M];
            // 역병 퍼짐
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 2 && !visited[i][j]) {
                        spread(i, j, visited);
                    }
                }
            }

            visited = new boolean[N][M];
            // 안전구역 구하기
            int safeArea = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0 && !visited[i][j]){
                        safeArea = safeArea + checkSafeArea(i, j, visited);
                    }
                }
            }
            map = originMap;
            maxSafeArea = Math.max(maxSafeArea, safeArea);

            return;
        }

        for (int i = start; i < N * M; i++){
            int x = i / M;
            int y = i % M;
            if (map[x][y] != 0){
                continue;
            }
            wallPos[cnt] = i;
            combination(cnt + 1, i + 1);
        }
    }
}