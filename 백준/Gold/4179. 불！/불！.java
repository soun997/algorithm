import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    static int r;
    static int c;
    static char[][] board;
    static int[][] fire;
    static int[][] jihoon;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Pair> q1 = new LinkedList<>();
    static Queue<Pair> q2 = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        fire = new int[r][c];
        jihoon = new int[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
            Arrays.fill(fire[i], -1);
            Arrays.fill(jihoon[i], -1);
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'F') {
                    q1.add(new Pair(i, j));
                    fire[i][j] = 0;
                }
                if (board[i][j] == 'J'){
                    q2.add(new Pair(i, j));
                    jihoon[i][j] = 0;
                }
            }
        }
        spread();
        escape();
    }

    static void spread(){
        while (!q1.isEmpty()) {
            Pair cur = q1.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (fire[nx][ny] >= 0 || board[nx][ny] == '#') continue;
                fire[nx][ny] = fire[cur.x][cur.y] + 1;
                q1.add(new Pair(nx, ny));
            }
        }

    }

    static void escape(){
        while (!q2.isEmpty()) {
            Pair cur = q2.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    System.out.println(jihoon[cur.x][cur.y] + 1);
                    return;
                }
                if (jihoon[nx][ny] >= 0 || board[nx][ny] == '#') continue;
                if (fire[nx][ny] != -1 && fire[nx][ny] <= jihoon[cur.x][cur.y] + 1) continue;
                jihoon[nx][ny] = jihoon[cur.x][cur.y] + 1;
                q2.add(new Pair(nx, ny));
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
