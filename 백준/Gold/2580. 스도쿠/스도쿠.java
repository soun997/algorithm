import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] sudoku = new int[9][9];
    static int blanks = 0;
    static int[] dx = { -1, 0, -1, -1, 0, 1, 1, 0, 1};
    static int[] dy = { -1, -1, 0, 1, 0, -1, 0, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if (sudoku[i][j] == 0) {
                    blanks++;
                }
            }
        }
//        System.out.println();
        backtracking(0, 0, 0);
    }

    static void backtracking(int x, int y, int cnt) {

//        System.out.println("x: " + x + " y: " + y);
        if (cnt == blanks) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    result.append(sudoku[i][j]).append(" ");
                }
                result.append("\n");
            }
            System.out.println(result);
            System.exit(0);
        }

        if (sudoku[x][y] == 0) {
            for (int k = 1; k <= 9; k++) {
                if (isPossible(x, y, k)) {

                    sudoku[x][y] = k;
                    if (y + 1 == 9) {
                        backtracking(x + 1, 0, cnt + 1);
                    } else {
                        backtracking(x, y + 1, cnt + 1);
                    }
                    sudoku[x][y] = 0;
                }
            }
        } else {
            if (y + 1 == 9) {
                backtracking(x + 1, 0, cnt);
            } else {
                backtracking(x, y + 1, cnt);
            }
        }
    }

    static boolean isPossible(int x, int y, int k) {

        // 가로줄
        for (int i = 0; i < 9; i++) {

            if (k == sudoku[x][i]) {
                return false;
            }
        }
        // 세로줄
        for (int i = 0; i < 9; i++) {

            if (k == sudoku[i][y]) {
                return false;
            }
        }
        // 3x3
        int mx = findMiddle(x);
        int my = findMiddle(y);
        for (int i = 0; i < 9; i++) {

            if (k == sudoku[mx + dx[i]][my + dy[i]]) {
                return false;
            }
        }
        return true;
    }

    static int findMiddle(int p) {

        for (int i = 0; i < 3; i++) {

            if (p <= i * 3 + 2) {
                return i * 3 + 1;
            }
        }
        return -1;
    }
}