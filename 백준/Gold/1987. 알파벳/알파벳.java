import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {

    static char[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int r;
    static int c;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        visited = new boolean[26];
        for (int i = 0; i < r; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                board[i][j] = input[j];
            }
        }
        visited[board[0][0] % 65] = true;
        dfs(0, 0, 1);
        visited[board[0][0] % 65] = false;
        System.out.println(max);
    }

    static void dfs(int x, int y, int cnt) {

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!check(nx, ny) || visited[(board[nx][ny] % 65)]){
                continue;
            }
            visited[board[nx][ny] % 65] = true;
            dfs(nx, ny, cnt + 1);
            visited[board[nx][ny] % 65] = false;
        }

        max = Math.max(max, cnt);
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= r || y < 0 || y >= c){
            return false;
        }
        return true;
    }
}