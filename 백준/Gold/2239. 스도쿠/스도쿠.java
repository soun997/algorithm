import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Main {

    static final int N = 9;
    static int[][] board = new int[N][N];
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        dfs(0, 0);
    }

    static void dfs(int r, int c) {
        if (flag){
            return;
        }

        if (r == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            flag = true;
            return;
        }

        if (board[r][c] != 0) {
            c++;
            if (c == N){
                c = 0;
                r++;
            }
            dfs(r,c);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (checkRow(i, r) && checkCol(i, c) && checkSquare(i, (r / 3) * 3, (c / 3) * 3)) {
                board[r][c] = i;

                if (c + 1 == N) {
                    dfs(r + 1, 0);
                } else {
                    dfs(r, c + 1);
                }
                board[r][c] = 0;
            }
        }
    }

    static boolean checkRow(int number, int r){
        for (int i = 0; i < N; i++) {
            if (number == board[r][i]){
                return false;
            }
        }
        return true;
    }

    static boolean checkCol(int number, int c){
        for (int i = 0; i < N; i++) {
            if (number == board[i][c]){
                return false;
            }
        }
        return true;
    }

    static boolean checkSquare(int number, int r, int c){
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (number == board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}