import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            int N = Integer.parseInt(br.readLine());

            if (N == 0){
                sb.append(1).append(" ").append(0).append("\n");
                continue;
            }
            if (N == 1){
                sb.append(0).append(" ").append(1).append("\n");
                continue;
            }
            long zero = 0;
            long one = 0;
            long[][] f = new long[2][N + 1];
            f[0][0] = f[1][1] = 1;
            for (int i = 2; i <= N; i++){
                f[0][i] = f[0][i - 1] + f[0][i - 2];
                f[1][i] = f[1][i - 1] + f[1][i - 2];
            }
            sb.append(f[0][N]).append(" ").append(f[1][N]).append("\n");
        }
        System.out.println(sb);
    }
    static int fibo(int n){
        int[] f = new int[40];
        f[0] = f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }
}