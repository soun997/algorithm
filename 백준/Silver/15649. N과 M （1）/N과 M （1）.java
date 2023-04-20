import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] choices;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        choices = new int[M];
        visited = new boolean[N];

        permutation(0);

        System.out.println(sb);
    }

    static void permutation(int cnt){
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(choices[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            choices[cnt] = i + 1;
            visited[i] = true;
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
}