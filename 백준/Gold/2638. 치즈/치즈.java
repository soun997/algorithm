import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int r;
    static int c;
    static int[][] map;
    static int[][] copiedMap;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        copiedMap = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(calculateMeltingTime());
    }

    public static int calculateMeltingTime() {
        int time = 0;
        copiedMap = copyMap();
        while (bfs() != r*c) {
            time++;
            findMelt();
            update();
            copiedMap = copyMap();
        }
        return time;
    }

    private static int bfs(){
        int cnt = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        copiedMap[0][0] = 3;
        cnt++;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++){
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny)) continue;
                if (copiedMap[nx][ny] != 0) continue;
                copiedMap[nx][ny] = 3;
                q.offer(new int[]{nx, ny});
                cnt++;
            }
        }
        return cnt;
    }

    private static void findMelt(){

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int sides = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (!check(nx, ny)) continue;
                    if (copiedMap[nx][ny] == 3)
                        sides++;
                }
                if (sides >= 2 && copiedMap[i][j] == 1)
                    copiedMap[i][j] = 2;    // 녹을 치즈
            }
        }
    }

    private static void update(){

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (copiedMap[i][j] == 2){
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int[][] copyMap() {
        int[][] copied = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                copied[i][j] = map[i][j];
            }
        }
        return copied;
    }

    private static boolean check(int x, int y){
        if (x < 0 || x >= r || y < 0 || y >= c)
            return false;
        return true;
    }
}