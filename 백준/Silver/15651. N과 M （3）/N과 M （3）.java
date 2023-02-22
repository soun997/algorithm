import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] numbers;
    static int[] choices;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        choices = new int[m];
        sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            numbers[i - 1] = i;
        }

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
            choices[cnt] = numbers[i];
            permutation(cnt + 1);
        }
    }
}