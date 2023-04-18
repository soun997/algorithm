import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][][] paintedMap;
    static boolean[][] visited;
    static boolean[] usedNumbers;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        paintedMap = new int[2][N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]){
                    paintMap(i, j, idx++);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1){
                    int count = 1;
                    Set<Integer> indexes = new HashSet<>();
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (!check(nx, ny) || indexes.contains(paintedMap[1][nx][ny])){
                            continue;
                        }
                        indexes.add(paintedMap[1][nx][ny]);
                        count = count + paintedMap[0][nx][ny];
                    }
                    map[i][j] = count % 10;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result.append(map[i][j]);
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    static void paintMap(int x, int y, int idx){
        List<int[]> changed = new ArrayList<>();

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        changed.add(new int[]{x, y});
        visited[x][y] = true;

        int count = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny) || map[nx][ny] == 1 || visited[nx][ny]){
                    continue;
                }
                q.offer(new int[]{nx, ny});
                changed.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                count++;
            }
        }
        for (int i = 0; i < changed.size(); i++) {
            int[] cur = changed.get(i);
            paintedMap[0][cur[0]][cur[1]] = count;
            paintedMap[1][cur[0]][cur[1]] = idx;
        }
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }
        return true;
    }
}