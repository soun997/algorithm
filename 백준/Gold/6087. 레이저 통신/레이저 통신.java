import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int W, H;
    static char[][] map;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        int[] start = new int[2];
        for (int i = 0; i < H; i++) {
            char[] inputs = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                map[i][j] = inputs[j];
                if (map[i][j] == 'C'){
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        min = Integer.MAX_VALUE;
        System.out.println(bfs(start[0], start[1]));
    }

    static int bfs(int x, int y){
        map[x][y] = '.';
        PriorityQueue<int[]> q = new PriorityQueue<>((Comparator.comparingInt(o -> o[3])));
        // x, y, dir, 거울설치횟수
        for (int d = 0; d < 4; d++) {
            q.offer(new int[]{x, y, d, 0});
        }

        int[][][] visited = new int[4][H][W];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < H; j++){
                Arrays.fill(visited[i][j], Integer.MAX_VALUE / 1000);
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (map[cur[0]][cur[1]] == 'C'){
                min = Math.min(min, cur[3]);
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                int nMirrors = (cur[2] == d) ? cur[3] : cur[3] + 1;

                // 범위 벗어나거나 벽인 경우 -> 진행 불가
                if (!check(nx, ny) || map[nx][ny] == '*'){
                    continue;
                }

                // 정반대 -> 갈 수 없음
                if ((cur[2] + 2) % 4 == d){
                    continue;
                }

                if (visited[d][nx][ny] <= nMirrors){
                    continue;
                }
                q.offer(new int[]{nx, ny, d, nMirrors});
                visited[d][nx][ny] = nMirrors;
            }
        }

        return min;
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= H || y < 0 || y >= W) {
            return false;
        }
        return true;
    }
}