import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static boolean[] visitedCol = new boolean[15];    // 배열의 idx는 행 번호, 배열의 원소값은 열 번호
    static boolean[] visitedDiag1 = new boolean[30];
    static boolean[] visitedDiag2 = new boolean[30];
    static int total = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        putQueen(0);
        System.out.println(total);
    }

    static void putQueen(int row) {

        if (row == N) {
            total++;
            return;
        }

        for (int col = 0; col < N; col++) {
            // 놓을 수 있는 위치라면
            if (visitedCol[col] ||
                    visitedDiag1[row + col] ||
                    visitedDiag2[row - col + N - 1]) {
                continue;
            }
            visitedCol[col] = true;
            visitedDiag1[row + col] = true;
            visitedDiag2[row - col + N - 1] = true;
            putQueen(row + 1);
            visitedCol[col] = false;
            visitedDiag1[row + col] = false;
            visitedDiag2[row - col + N - 1] = false;
        }
    }
}