import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int cases;
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int max;
    static int no;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        cases = Integer.parseInt(br.readLine());
        for (int t = 0; t < cases; t++) {

            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            visited = new boolean[n][n];
            max = Integer.MIN_VALUE;
            no = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) continue;
                    bfs(i, j);

                }
            }

            sb.append("#").append(t + 1).append(" ")
                    .append(no).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int x, int y){

        int cnt = 0;
        int minNo = map[x][y];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;
            //System.out.println(Arrays.toString(cur));
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (!check(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (Math.abs(map[cur[0]][cur[1]] - map[nx][ny]) == 1){
                    minNo = Math.min(minNo, map[nx][ny]);
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        //System.out.println();
        if (cnt > max){
            no = minNo;
            max = cnt;
        }
        if (cnt == max){
            no = Math.min(no, minNo);
        }
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= n || y < 0 || y >= n) {
            return false;
        }
        return true;
    }
}