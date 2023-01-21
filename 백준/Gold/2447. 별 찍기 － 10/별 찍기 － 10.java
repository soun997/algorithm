import java.util.Scanner;

public class Main {

    static int[][] matrix;
    static int n;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        matrix = new int[n][n];

        StringBuilder sb = new StringBuilder();    // System.out.print()로 풀면 시간 초과가 남

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

        if (n == 3) {

            for (int i = a; i < a + 3; i++){

                for (int j = b; j < b + 3; j++){
                    if (i == a + 1 && j == b + 1) continue;
                    matrix[i][j] = 1;
                }
            }
        }
        else{
            for (int i = a; i < a + n; i += n / 3){

                for (int j = b; j < b + n; j += n / 3){
                    if (i == a + n / 3 && j == b + n / 3) continue;
                    recursion(i, j, n/3);
                }
            }
        }
    }
}
