import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] values = new int[4];
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 4; i++) {
                values[i] = (int)Math.pow(a, i + 1) % 10;
            }
            int idx = ((b % 4) + 3) % 4;
            sb.append((values[idx] % 10 == 0) ? 10 : values[idx]).append("\n");
        }
        System.out.println(sb);
    }
}