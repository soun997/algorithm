import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] sentence = br.readLine().toCharArray();
        int N = sentence.length;

        // palindrome[i][j]: i ~ j 인덱스 까지의 문자열이 팰린드롬인지
        boolean[][] palindrome = new boolean[N][N];
        // 우선은 Math.abs(i - j) == 1인 경우를 채워준다
        for (int i = 0; i < N; i++) {
            int row = 0;
            int col = i;
            while (check(row, col, N)) {
                if (Math.abs(row - col) > 1) {
                    palindrome[row][col] = (sentence[row] == sentence[col]) & palindrome[row + 1][col - 1];
                } else {
                    palindrome[row][col] = (sentence[row] == sentence[col]);
                }
                row++;
                col++;
            }
        }

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                if (palindrome[j - 1][i - 1]){
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        System.out.println(dp[N]);
    }

    static boolean check(int row, int col, int N) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return false;
        }
        return true;
    }
}