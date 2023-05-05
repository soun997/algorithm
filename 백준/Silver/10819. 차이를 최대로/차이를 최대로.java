import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numbers;
    static int[] selected;
    static boolean[] visited;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        selected = new int[N];
        visited = new boolean[N];
        max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(max);
    }

    static void dfs(int cnt){

        if (cnt == N) {
            // TODO: i, i + 1의 차이를 모두 더한 값을 max와 비교하여 갱신한다
            int sum = 0;
            for (int i = 0; i < N - 1; i++){
                sum = sum + Math.abs(selected[i] - selected[i + 1]);
            }
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            selected[cnt] = numbers[i];
            dfs(cnt + 1);
            visited[i] =  false;
        }
    }
}