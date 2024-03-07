import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] initHealths;
    static int[] bullets;
    static boolean[] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int total = 0;


    // 총알에 공격력
    // 총알은 왼 -> 오
    // 0은 표적이 없는 곳, 그 외에는 표적과 그 점수
    // 표적이 있는 위치의 값: 초기 체력 -> 표적에 총알 닿으면 공격력 만큼 체력 감소, 0이하 되면 과녁 사라짐 -> 초기체력 만큼 점수 획득
    // 값이 10 이상: 보너스 표적 -> 맞추는 순간 사라지고 초기체력 만큼 점수 획득
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        initHealths = new int[N][N];
        bullets = new int[K];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                initHealths[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            bullets[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, 0, initHealths, copy(initHealths));
        System.out.println(total);
    }

    static void backtracking(int cnt, int score, int[][] initHealths, int[][] currentHealths) {

//        print(currentHealths);
        if (cnt == K) {

            total = Math.max(total, score);
            return;
        }

        for (int i = 0; i < N; i++) {
            // 총알 발사
            for (int j = 0; j < N; j++) {
                // 해당 과녁이 available
                if (currentHealths[i][j] > 0) {
                    int[][] copiedInit = copy(initHealths);
                    int[][] copiedCurrent = copy(currentHealths);
                    if (isBonus(copiedCurrent[i][j])) {
                        copiedCurrent[i][j] = 0;
                        backtracking(cnt + 1, score + initHealths[i][j], copiedInit, copiedCurrent);
                        break;
                    }
                    copiedCurrent[i][j] -= bullets[cnt];
                    if (copiedCurrent[i][j] <= 0) {
                        createNewTarget(i, j, copiedInit, copiedCurrent);
                        backtracking(cnt + 1, score + initHealths[i][j], copiedInit, copiedCurrent);
                        break;
                    }
                    backtracking(cnt + 1, score, copiedInit, copiedCurrent);
                    break;
                }
            }
        }
    }

    static void createNewTarget(int x, int y, int[][] copiedInit, int[][] copiedCurrent) {

        int initHealth = copiedInit[x][y] / 4;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (isOutOfBounds(nx, ny) || (copiedCurrent[nx][ny] > 0)) {
                continue;
            }
            copiedInit[nx][ny] = initHealth;
            copiedCurrent[nx][ny] = initHealth;
        }
    }

    static boolean isOutOfBounds(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return true;
        }
        return false;
    }

    static boolean isBonus(int score) {

        return score >= 10;
    }

    static int[][] copy(int[][] origin) {

        int[][] copied = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copied[i][j] = origin[i][j];
            }
        }
        return copied;
    }

    static void print(int[][] current) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(current[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}