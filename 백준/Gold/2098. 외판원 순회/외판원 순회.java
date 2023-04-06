import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] weights;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE / 1000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        weights = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                weights[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 배열을 초기화한다
        init();

        // 0번째 도시부터 순회를 시작한다
        // 0번째 도시를 방문 처리 -> visited: 0001
        System.out.println(visitCity(0, 1));
    }

    static void init(){
        dp = new int[1 << N][N];
        for (int i = 0; i < (1 << N); i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    // 비트마스킹을 이용한 도시 방문 체크
    static int visitCity(int cityNo, int visited){

        // 모든 도시 순회가 끝난 경우
        if (visited == (1 << N) - 1) {

            // 처음의 도시로 되돌아 갈 수 없다면
            if (weights[cityNo][0] == 0) {
                return INF;
            }
            // 처음의 도시로 되돌아 갈 수 있다면
            return weights[cityNo][0];
        }

        // 현재 경우의 수에서, 해당 도시를 방문한 적이 있다면 -> 중복 방문을 하지 않는다.
        if (dp[visited][cityNo] != -1){
            return dp[visited][cityNo];
        }

        /*
            현재 경우의 수에서, 해당 도시를 방문한 적이 없다면
         */

        // 현재 경우의 수 기준, 해당 도시 방문 처리
        dp[visited][cityNo] = INF;

        for (int i = 0; i < N; i++) {

            /*
                다음 도시 방문
             */

            // 1. 이미 방문한 도시라면
            // AND 연산: 해당 도시 방문 여부를 체크 -> 0001 & 0010 = 0000, 0이라면 방문하지 않은 도시
            if ((visited & (1 << i)) != 0){
                continue;
            }

            // 2. 그 도시로 갈 수 있는 경로가 없다면
            if (weights[cityNo][i] == 0){
                continue;
            }

            // 3. 방문이 가능한 도시라면
            // OR 연산: 해당 도시 방문 처리 -> 0001 | 0010 = 0011
            int cost = visitCity(i, visited | (1 << i));    // 다음 도시를 방문해주고, 최소값을 반환받는다.
            // 현재 경우의 수에서, 해당 도시로 가는 최소값 갱신
            dp[visited][cityNo] = Math.min(cost + weights[cityNo][i], dp[visited][cityNo]);
        }

        // 반환하는 값은 최소값
        return dp[visited][cityNo];
    }
}