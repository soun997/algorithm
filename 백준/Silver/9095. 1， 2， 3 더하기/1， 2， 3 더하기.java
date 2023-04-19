import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] d = new int[n + 1];

            if (n == 1){
                sb.append(1).append("\n");
                continue;
            }
            if (n == 2){
                sb.append(2).append("\n");
                continue;
            }
            d[1] = 1;
            d[2] = 2;
            d[3] = 4;
            for (int i = 4; i <= n; i++) {
                d[i] = d[i - 1] + d[i - 2] + d[i - 3];
            }
            sb.append(d[n]).append("\n");
        }
        System.out.println(sb);
    }
}