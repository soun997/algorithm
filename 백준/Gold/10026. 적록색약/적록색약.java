import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static char[][] picture;
    static int[][] map;
    static boolean[][] visited;
    static int normal;
    static int blind;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        picture = new char[N][N];

        for (int i = 0; i < N; i++) {
            picture[i] = br.readLine().toCharArray();
        }

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                normalBfs(i, j);
                normal++;
            }
        }

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                blindBfs(i, j);
                blind++;
            }
        }

        System.out.println(normal + " " + blind);
    }

    static void normalBfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, blind});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                // 범위 초과 혹은 이미 방문
                if (!check(nx, ny) || visited[nx][ny]) continue;
                // 현재 구역이랑 같은 색
                if (picture[cur[0]][cur[1]] == picture[nx][ny]){
                    map[nx][ny] = cur[2];
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny, map[nx][ny]});
                }
            }
        }
    }

    static void blindBfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, blind});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                // 범위 초과 혹은 이미 방문
                if (!check(nx, ny) || visited[nx][ny]) continue;
                // 현재 구역이랑 같은 색
                if ((picture[cur[0]][cur[1]] == 'R' && picture[nx][ny] == 'G') ||
                        (picture[cur[0]][cur[1]] == 'G' && picture[nx][ny] == 'R') ||
                        (picture[cur[0]][cur[1]] == picture[nx][ny])){
                    map[nx][ny] = cur[2];
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny, map[nx][ny]});
                }
            }
        }
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= N){
            return false;
        }
        return true;
    }
}