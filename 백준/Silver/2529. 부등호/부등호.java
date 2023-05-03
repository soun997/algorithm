import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int K;
    static String[] operator;
    static String[] result;
    static boolean[] visited;
    static String min;
    static String max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        operator = br.readLine().split(" ");
        result = new String[K + 1];
        visited = new boolean[10];

        min = Long.toString(Long.MAX_VALUE);
        max = Long.toString(Long.MIN_VALUE);
        dfs(0);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int cnt){
        if (cnt == K + 1){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < K + 1; i++) {
                sb.append(result[i]);
            }

            if (Long.parseLong(min) > Long.parseLong(sb.toString())) {
                min = sb.toString();
            }
            if (Long.parseLong(max) < Long.parseLong(sb.toString())) {
                max = sb.toString();
            }
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            // 첫번째 수라면 -> 노상관
            if (cnt == 0){
                visited[i] = true;
                result[cnt] = Integer.toString(i);
                dfs(cnt + 1);
                visited[i] = false;
                continue;
            }
            // 이후부터는 왼쪽 수와 부등식이 성립하는지 체크
            int left = Integer.parseInt(result[cnt - 1]);
            int right = i;
            if (operator[cnt - 1].equals("<")){
                if (left < right){
                    visited[i] = true;
                    result[cnt] = Integer.toString(i);
                    dfs(cnt + 1);
                }
            }
            if (operator[cnt - 1].equals(">")) {
                if (left > right){
                    visited[i] = true;
                    result[cnt] = Integer.toString(i);
                    dfs(cnt + 1);
                }
            }
            visited[i] = false;
        }
    }
}