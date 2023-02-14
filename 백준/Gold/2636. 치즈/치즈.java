import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int r;
    static int c;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int lastCheeses = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        while (bfs(cnt, cnt) > 0){
            visited = new boolean[r][c];
            cnt++;
        }
        System.out.println(cnt);
        System.out.println(lastCheeses);
    }

    static int bfs(int x, int y){
        // 배열 복사
        int[][] copied = new int[r][c];
        for (int i = 0; i < r; i++) {
            copied[i] = Arrays.copyOf(map[i], c);
        }
        int melted = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        while (!q.isEmpty()){
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (!check(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 1 && copied[nx][ny] == 1){
                    map[nx][ny] = 0;
                    melted++;
                    continue;
                }
                if (map[nx][ny] == 0 && copied[nx][ny] == 1){
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        if (melted != 0)
            lastCheeses = melted;
        return melted;
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= r || y < 0 || y >= c)
            return false;
        return true;
    }
}