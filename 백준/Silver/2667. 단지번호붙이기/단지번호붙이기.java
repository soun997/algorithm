import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> result = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 || visited[i][j]) continue;
                result.add(dfs(i, j) + 1);
            }
        }
        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        for (int i : result){
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    static int dfs(int x, int y){
        visited[x][y] = true;
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!valid(nx, ny)) continue;
            if (visited[nx][ny] || map[nx][ny] == 0) continue;
            cnt += dfs(nx, ny) + 1;
        }

        return cnt;
    }

    static boolean valid(int x, int y){
        if (x < 0 || x >= n || y < 0 || y >= n)
            return false;
        return true;
    }
}