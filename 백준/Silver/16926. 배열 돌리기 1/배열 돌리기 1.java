import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, r;
    static int[][] arr;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int x, y, d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int times = Math.min(n, m);
        for (int i = 0; i < times / 2; i++) {
            x = i;
            y = i;
            rotate(r, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void rotate(int cnt, int times){

        for (int i = 0; i < cnt; i++) {
            int d = 0;
            int temp = arr[x][y];
            while(true){
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (!check(nx, ny, times)) {
                    if (d == 3) break;
                    d = (d + 1) % 4;
                    nx = x + dx[d];
                    ny = y + dy[d];
                }

                int t = arr[nx][ny];
                arr[nx][ny] = temp;
                temp = t;
                x = nx;
                y = ny;
            }
        }
    }

    static boolean check(int nx, int ny, int times) {
        if (nx < times || nx >= n - times || ny < times || ny >= m - times)
            return false;
        return true;
    }
}