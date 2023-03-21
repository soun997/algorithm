import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] chessboard;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chessboard = new char[N][M];

        for (int i = 0; i < N; i++) {
            chessboard[i] = br.readLine().toCharArray();
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                min = Math.min(min, find(i, j, 'W'));
                min = Math.min(min, find(i, j, 'B'));
            }
        }

        System.out.println(min);
    }

    static int find(int x, int y, char initColor) {
        int cnt = (chessboard[x][y] == initColor ? 0 : 1);
        char prevRow = initColor;
        char prevCol = prevRow;
        for (int i = x; i < x + 8; i++) {
            for (int j = y + 1; j < y + 8; j++) {
                // 전 열의 칸 색과 현재 열의 칸 색이 같다면 -> 변경이 필요한 칸
                if (prevCol == chessboard[i][j]) {
                    cnt++;
                    prevCol = (chessboard[i][j] == 'W' ? 'B' : 'W');
                } else {
                    prevCol = chessboard[i][j];
                }
            }
            // i + 1이 범위를 초과하지 않는다면
            if (i + 1 < x + 8) {
                // chessboard[i][y]와 chessboard[i + 1][y]비교
                if (prevRow == chessboard[i + 1][y]) {
                    cnt++;
                    prevRow = (chessboard[i + 1][y] == 'W' ? 'B' : 'W');
                    prevCol = prevRow;
                } else {
                    prevRow = chessboard[i + 1][y];
                }
            }
        }
        return cnt;
    }
}