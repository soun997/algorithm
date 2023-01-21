import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    static int[][] matrix;
    static int n;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];

        StringBuilder sb = new StringBuilder();

        recursion(0, 0, n);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1)
                    sb.append("*");
                else
                    sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void recursion(int a, int b, int n){

        for (int i = a; i < a + n; i += n / 3){

            for (int j = b; j < b + n; j += n / 3){
                if (i == a + n / 3 && j == b + n / 3) continue;
                if (n == 3)
                    matrix[i][j] = 1;
                else
                    recursion(i, j, n / 3);
            }
        }
    }
}
