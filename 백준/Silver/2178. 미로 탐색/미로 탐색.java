import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] maze;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                maze[i][j] = input[j] - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    static int bfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, 1});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            //System.out.println(cur[0] + " " + cur[1]);
            if (cur[0] == n - 1 && cur[1] == m - 1) return cur[2];
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (!check(nx, ny) || visited[nx][ny] || maze[nx][ny] == 0) continue;

                q.offer(new int[]{nx, ny, cur[2] + 1});
                visited[nx][ny] = true;
            }
        }
        return -1;  // 비정상 종료 - 있을 수 없는 케이스
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        return true;
    }
}