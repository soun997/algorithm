import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] w;
    static boolean[] visited;
    static int origin = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = new int[n][n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            origin = i;
            visited[i] = true;
            visitCity(i, 0, 0);
            visited[i] = false;
        }
        if (min == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(min);
    }

    static void visitCity(int city, int cnt, int sum) {

        if (cnt == n - 1) {
            sum += w[city][origin];
            if (w[city][origin] != 0)
                min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < n; i++){
            if (visited[i] || w[city][i] == 0) continue;
            visited[i] = true;
            visitCity(i , cnt + 1, sum + w[city][i]);
            visited[i] = false;
        }
    }
}
