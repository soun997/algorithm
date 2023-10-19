
import java.util.*;

class Solution {
    static int[] answer;
    private static int m;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[][]{{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}})));
    }

    public static int[] solution(int[][] arr) {
        answer = new int[2];
        int n = arr.length;

        divide(0, 0, n, arr);

        return answer;
    }

    private static boolean check(int row, int col, int n, int[][] map) {
        int std = map[row][col];
        for (int i = row; i < row + n; i++) {
            for (int j = col; j < col + n; j++) {
                if (std != map[i][j]) {
                    return false;
                }
            }
        }
        m = std;
        return true;
    }

    private static void divide(int row, int col, int n, int[][] map) {
        if (check(row, col, n, map)) {
            if (m == 0)
                answer[0]++;
            else
                answer[1]++;
        } else {
            int s = n / 2;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    divide(row + s * i, col + s * j, s, map);
                }
            }
        }
    }
}