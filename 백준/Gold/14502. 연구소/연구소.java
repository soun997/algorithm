import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int WALL_MAX = 3;
    static int BLANK = 0;
    static int WALL = 1;
    static int VIRUS = 2;
    static int N, M;
    static int[][] lab;
    static List<int[]> blanks = new ArrayList<>();
    static List<int[]> viruses = new ArrayList<>();
    static int[] choices = new int[3];
    static int max = 0;
    static int[] dx = { -1 , 0, 1, 0 };
    static int[] dy = { 0 , -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == BLANK) {
                    blanks.add(new int[]{i, j});
                }
                if (lab[i][j] == VIRUS) {
                    viruses.add(new int[]{i, j});
                }
            }
        }
//        print();
        build(0, 0);

        System.out.println(max);
    }

    // combination
    static void build(int start, int cnt) {

        if(cnt == WALL_MAX) {
            int[][] copied = copy();
            // 벽 세울 위치를 정했으니 세움
            for (int idx : choices) {
                int[] pos = blanks.get(idx);
                copied[pos[0]][pos[1]] = 1;
            }
            // 바이러스 퍼짐
            for (int[] pos : viruses) {
                virus(pos[0], pos[1], copied);
            }
            // 안전영역 구하기
            max = Math.max(max, countSafeZone(copied));
            return;
        }
        for (int i = start; i < blanks.size(); i++) {

            choices[cnt] = i;
            build(i + 1, cnt + 1);
        }
    }

    static void virus(int x, int y, int[][] copied) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                // 이동할 수 없는 경우 -> 범위 초과, 빈칸이 아닌 경우
                if (isOutOfBounds(nx, ny) || copied[nx][ny] != BLANK) {
                    continue;
                }
                // 이동할 수 있는 경우
                copied[nx][ny] = VIRUS;
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    static int countSafeZone(int[][] copied) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copied[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static boolean isOutOfBounds(int x, int y) {

        return x < 0 || x >= N || y < 0 || y >= M;
    }

    static int[][] copy() {
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copied[i][j] = lab[i][j];
            }
        }
        return copied;
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(lab[i]));
        }
        System.out.println();
    }
}