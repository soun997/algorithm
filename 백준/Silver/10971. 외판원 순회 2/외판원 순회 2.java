import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] W;
    static boolean[] visited;
    static int startCity;
    static int minCost;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minCost = Integer.MAX_VALUE;
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            startCity = i;
            visited[i] = true;
            visitCity(i, 1, 0);
            visited[i] = false;
        }
        System.out.println(minCost);
    }

    static void visitCity(int cityNo, int count, int cost){
        if (minCost < cost){
            return;
        }
        // 다시 처음 도시로 돌아가기
        if (count == N){
            // 처음 도시로 돌아갈 수 있을 때만
            if (W[cityNo][startCity] != 0){
                minCost = Math.min(minCost, cost + W[cityNo][startCity]);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            // 방문할 수 없는 경우
            if (W[cityNo][i] == 0 || visited[i]){
                continue;
            }
            visited[i] = true;
            visitCity(i, count + 1, cost + W[cityNo][i]);
            visited[i] = false;
        }
    }
}