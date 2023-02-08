import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int r;
    static int c;
    static char[][] board;
    static int[] alphabets = new int[26];

    static int max = Integer.MIN_VALUE;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] init = br.readLine().split(" ");
        r = Integer.parseInt(init[0]);
        c = Integer.parseInt(init[1]);
        board = new char[r][c];

        for (int i = 0; i < r; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                board[i][j] = input[j];
            }
        }
        System.out.println(dfs(0, 0, 1));
    }

    static int dfs(int x, int y, int d){
        Stack<Pair> stk = new Stack<>();
        alphabets[board[x][y] % 65]++;
        stk.push(new Pair(x, y, d, Arrays.copyOf(alphabets, 26)));
        while (!stk.empty()) {
            Pair cur = stk.pop();
            alphabets = Arrays.copyOf(cur.status, 26);
            boolean movable = false;
            //System.out.println(board[cur.x][cur.y] + " " + cur.distance);
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (alphabets[board[nx][ny] % 65] > 0) continue;
                movable = true;
                int[] status = Arrays.copyOf(cur.status, 26);
                status[board[nx][ny] % 65]++;
                stk.push(new Pair(nx, ny, cur.distance + 1, status));
            }
            if (!movable){
                max = Math.max(max, cur.distance);
            }
        }
        return max;
    }

    static class Pair {
        public int x;
        public int y;
        public int distance;
        public int[] status;

        public Pair(int x, int y, int distance, int[] status){
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.status = status;
        }
    }
}
