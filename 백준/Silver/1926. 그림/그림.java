import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {

    public int x;
    public int y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int[][] board;
    static boolean[][] visited;
    static int n;
    static int m;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        Queue<Pair> q = new LinkedList<>();
        int count = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || board[i][j] == 0) continue;
                q.add(new Pair(i, j));
                max = Math.max(max, bfs(q));
                count++;
            }
        }
        System.out.println(count);
        System.out.println(max);
    }

    static int bfs(Queue<Pair> q) {
        int area = 0;
        while(!q.isEmpty()){
            Pair cur = q.poll();
            // 사방 탐색
            for (int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                // 사방탐색 시 범위를 벗어난다면 continue (index 범위 벗어나지 않도록)
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                // 이미 방문했던 칸이거나 색칠이 되어있지 않은 칸이라면 탐색할 이유 없음
                if (visited[nx][ny] || board[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
                area++;
            }
        }
        if (area == 0) return 1;
        return area;
    }
}

