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

    static int[][] board = new int[102][102];
    static int n;
    static int m;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if (Integer.parseInt(input[j]) == 1) board[i][j] = -1;
            }
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        board[0][0] = 1;
        int result = bfs(q);
        System.out.println(result);
    }

    static int bfs(Queue<Pair> q) {
        int past = 0;
        while(!q.isEmpty()){
            Pair cur = q.poll();
            past = board[cur.x][cur.y]; // 전 칸의 값을 가져온다.
            // 사방 탐색
            for (int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (board[nx][ny] != -1) continue;
                board[nx][ny] = past + 1;
                q.add(new Pair(nx, ny));
            }
        }
        return board[n - 1][m - 1];
    }
}

