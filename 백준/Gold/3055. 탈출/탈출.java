import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int r;
    static int c;
    static char[][] map;
    static int[][] dochi;
    static int[][] water;
    static Queue<int[]> q1 = new LinkedList<>();
    static Queue<int[]> q2 = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        dochi = new int[r][c];
        water = new int[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(dochi[i], -1);
            Arrays.fill(water[i], Integer.MAX_VALUE);
            for (int j = 0; j < c; j++) {
                // 고슴도치가 있는 곳
                if (map[i][j] == 'S') {
                    dochi[i][j] = 0;
                    q1.offer(new int[]{i, j});
                }
                if (map[i][j] == '*'){
                    water[i][j] = 0;
                    q2.offer(new int[]{i, j});
                }
            }
        }
        spread();
        int result = escape();
        System.out.println(result == -1 ? "KAKTUS" : result);
    }

    static int escape(){

        while (!q1.isEmpty()) {
            int[] cur = q1.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1]+ dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (map[nx][ny] == 'D') {
                    return dochi[cur[0]][cur[1]] + 1;
                }
                if (map[nx][ny] == 'X' || dochi[nx][ny] != -1) continue;
                if (dochi[cur[0]][cur[1]] + 1 >= water[nx][ny]) continue;
                // if (map[nx][ny] == 'D') return dochi[cur.x][cur.y] + 1; 해당 코드가 여기 있을 때는 제대로 동작하지 않음!!
                dochi[nx][ny] = dochi[cur[0]][cur[1]] + 1;
                q1.offer(new int[]{nx, ny});
            }
        }
        return -1;
    }

    static void spread(){

        while (!q2.isEmpty()) {
            int[] cur = q2.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (map[nx][ny] == 'X' || map[nx][ny] == 'D' || water[nx][ny] != Integer.MAX_VALUE) continue;
                water[nx][ny] = water[cur[0]][cur[1]] + 1;
                q2.offer(new int[]{nx, ny});
            }
        }
    }
}
