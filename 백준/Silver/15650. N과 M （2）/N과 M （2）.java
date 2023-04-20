import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] choices;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        choices = new int[M];

        dfs(0, 0);

        System.out.println(sb);
    }

    static void dfs(int start, int cnt){
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(choices[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {

            choices[cnt] = i + 1;
            dfs(i + 1, cnt + 1);
        }
    }
}