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
    static int n;
    static int m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Pair> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    q.add(new Pair(i, j));
                }
            }
        }
        int result = bfs();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) result = -1;
            }
        }
        System.out.println(result);

    }

    static int bfs(){
        int past = 0;
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            past = board[cur.x][cur.y];
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (board[nx][ny] != 0) continue;
                q.add(new Pair(nx, ny));
                board[nx][ny] = past + 1;
            }
        }
        return past - 1;
    }
}
