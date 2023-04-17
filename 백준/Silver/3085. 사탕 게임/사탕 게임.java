import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        max = Math.max(max, getMaxCandyCount());

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    // 범위 벗어나거나 두 사탕의 색이 같다면 넘어감
                    if (!check(nx, ny) || map[x][y] == map[nx][ny]){
                        continue;
                    }
                    swap(x, y, nx, ny);
                    max = Math.max(max, getMaxCandyCount());
                    swap(x, y, nx, ny);
                }
            }
        }

        System.out.println(max);
    }

    static int getMaxCandyCount(){
        int colMax = 1;
        int rowMax = 1;

        for (int i = 0; i < N; i++){
            int colSum = 1;
            int rowSum = 1;
            for (int j = 1; j < N; j++) {
                if (map[i][j] == map[i][j - 1]){
                    colSum++;
                }
                else {
                    colMax = Math.max(colMax, colSum);
                    colSum = 1;
                }
                if (map[j][i] == map[j - 1][i]){
                    rowSum++;
                }
                else {
                    rowMax = Math.max(rowMax, rowSum);
                    rowSum = 1;
                }
            }
            colMax = Math.max(colMax, colSum);
            rowMax = Math.max(rowMax, rowSum);
        }

        return Math.max(colMax, rowMax);
    }

    static void swap(int x, int y, int nx, int ny){
        char temp = map[x][y];
        map[x][y] = map[nx][ny];
        map[nx][ny] = temp;
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return false;
        }
        return true;
    }
}