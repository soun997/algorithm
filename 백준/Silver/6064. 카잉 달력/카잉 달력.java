import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());


        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            boolean flag = false;
            for (int i = x; i < (M * N); i += M) {
                if (i % N == y) {
                    sb.append(i + 1).append("\n");
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                sb.append(-1).append("\n");
            }
        }

        System.out.println(sb);
    }
}