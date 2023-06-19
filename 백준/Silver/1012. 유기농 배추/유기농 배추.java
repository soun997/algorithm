import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 아 맛좋은~ '유기농 배추'
public class Main {

    static int N, M, K;
    static int[][] field;
    static int worm;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            field = new int[N][M];
            worm = 0;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                field[x][y] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (field[i][j] == 0){
                        continue;
                    }
                    bfs(i, j);
                    worm++;
                }
            }
            answer.append(worm).append("\n");
        }
        System.out.println(answer);
    }

    static int[] DX = {-1, 0, 1, 0};
    static int[] DY = {0, -1, 0, 1};

    static void bfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + DX[d];
                int ny = cur[1] + DY[d];
                if (isOutOfBound(nx, ny) || field[nx][ny] == 0){
                    continue;
                }
                q.offer(new int[]{nx, ny});
                field[nx][ny] = 0;
            }
        }
    }

    static boolean isOutOfBound(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return true;
        }
        return false;
    }
}
