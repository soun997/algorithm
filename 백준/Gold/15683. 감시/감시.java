import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class CCTV {

        public int x;
        public int y;
        public int type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public void supervise(int dir){
            int[] dx;
            int[] dy;
            int nx = x;
            int ny = y;
            switch(type){
                case 1:
                     dx = new int[]{-1, 0, 1, 0};
                     dy = new int[]{0, -1, 0, 1};
                    while(true){
                        nx = nx + dx[dir];
                        ny = ny + dy[dir];
                        if (!check(nx, ny))
                            break;
                        if (map[nx][ny] == 6)
                            break;
                        if (map[nx][ny] != 0)
                            continue;
                        map[nx][ny] = -1;
                    }
                    break;
                case 2:
                    dx = new int[]{-1, 0, 1, 0};
                    dy = new int[]{0, -1, 0, 1};
                    for (int i = 0; i < 2; i++){
                        nx = x;
                        ny = y;
                        while (true) {
                            nx = nx + dx[dir % 2 + 2 * i];
                            ny = ny + dy[dir % 2 + 2 * i];
                            if (!check(nx, ny))
                                break;
                            if (map[nx][ny] == 6)
                                break;
                            if (map[nx][ny] != 0)
                                continue;
                            map[nx][ny] = -1;
                        }
                    }
                    break;
                case 3:
                    dx = new int[]{-1, 0, 1, 0};
                    dy = new int[]{0, 1, 0, -1};
                    for (int i = 0; i < 2; i++){
                        nx = x;
                        ny = y;
                        while (true) {
                            nx = nx + dx[(dir + i) % 4];
                            ny = ny + dy[(dir + i) % 4];
                            if (!check(nx, ny) || !check(nx, ny))
                                break;
                            if (map[nx][ny] == 6)
                                break;
                            if (map[nx][ny] != 0)
                                continue;
                            map[nx][ny] = -1;
                        }
                    }
                    break;
                case 4:
                    dx = new int[]{-1, 0, 1, 0};
                    dy = new int[]{0, 1, 0, -1};
                    for (int i = 0; i < 3; i++){
                        nx = x;
                        ny = y;
                        while (true) {
                            nx = nx + dx[(dir % 4 + i) % 4];
                            ny = ny + dy[(dir % 4 + i) % 4];
                            if (!check(nx, ny) || !check(nx, ny))
                                break;
                            if (map[nx][ny] == 6)
                                break;
                            if (map[nx][ny] != 0)
                                continue;
                            map[nx][ny] = -1;
                        }
                    }
                    break;
                case 5:
                    dx = new int[]{-1, 0, 1, 0};
                    dy = new int[]{0, -1, 0, 1};
                    for (int i = 0; i < 4; i++){
                        nx = x;
                        ny = y;
                        while(true){
                            nx = nx + dx[i];
                            ny = ny + dy[i];
                            if (!check(nx, ny))
                                break;
                            if (map[nx][ny] == 6)
                                break;
                            if (map[nx][ny] != 0)
                                continue;
                            map[nx][ny] = -1;
                        }
                    }
                    break;
            }
        }

        private boolean check(int x, int y){
            if (x < 0 || x >= n || y < 0 || y >= m)
                return false;
            return true;
        }
    }

    static int n;
    static int m;
    static int[][] map;
    static List<CCTV> cctvs;
    static int min;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cctvs = new ArrayList<>();
        min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6)
                    cctvs.add(new CCTV(i, j, map[i][j]));
            }
        }

        dfs(0);

        System.out.println(min);
    }

    static void getMinArea(){
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0)
                    count++;
            }
        }
        min = Math.min(min, count);
    }

    static void dfs(int idx){

        if (idx == cctvs.size()){
            getMinArea();
            return;
        }

        CCTV cctv = cctvs.get(idx);
        for (int d = 0; d < 4; d++) {
            int[][] copiedMap = copyMap();
            cctv.supervise(d);
            dfs(idx + 1);

            for (int i = 0; i < n; i++){
                map[i] = Arrays.copyOf(copiedMap[i], m);
            }
        }
    }

    static int[][] copyMap(){
        int[][] copied = new int[n][m];
        for (int i = 0; i < n; i++) {
            copied[i] = Arrays.copyOf(map[i], m);
        }
        return copied;
    }
}