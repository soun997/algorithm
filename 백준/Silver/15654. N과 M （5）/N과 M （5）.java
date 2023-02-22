import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] numbers;
    static boolean[] visited;
    static int[] choices;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        visited = new boolean[n];
        choices = new int[m];
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        permutation(0);

        System.out.println(sb.toString());
    }

    static void permutation(int cnt){
        if (cnt == m){
            for (int i = 0; i < m; i++) {
                sb.append(choices[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            choices[cnt] = numbers[i];
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
}