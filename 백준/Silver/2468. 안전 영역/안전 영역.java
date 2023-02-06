import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] area;
    static boolean[][] check;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        area = new int[n][n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, area[i][j]);
                max = Math.max(max, area[i][j]);
            }
        }

        int result = Integer.MIN_VALUE;
        int cnt;
        // 왜 min - 1이어야 하는 걸까? ->
        for (int k = min - 1; k < max + 1; k++) {
            cnt = 0;
            check = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!check[i][j] && area[i][j] > k) {
                        bfs(i, j, k);
                        cnt++;
                    }
                }
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);
    }

    static void bfs(int r, int c, int depth) {

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (check[nx][ny]) continue;
                if (area[nx][ny] <= depth) continue;
                q.offer(new int[] {nx, ny});
                check[nx][ny] = true;
            }
        }
    }
}
