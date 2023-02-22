import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int test;
    static int n, m;
    static char[][] map;
    static int[][] devil;
    static int[][] sooyeon;
    static Queue<int[]> q1;
    static Queue<int[]> q2;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        test = Integer.parseInt(br.readLine());

        for (int t = 0; t < test; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n][m];
            devil = new int[n][m];
            sooyeon = new int[n][m];
            q1 = new ArrayDeque<>();
            q2 = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                String[] inputs = br.readLine().split("");
                Arrays.fill(devil[i], -1);
                Arrays.fill(sooyeon[i], -1);
                for (int j = 0; j < m; j++) {
                    map[i][j] = inputs[j].charAt(0);
                    if (inputs[j].equals("*")){
                        devil[i][j] = 0;
                        q1.offer(new int[]{i, j, 0});
                        continue;
                    }
                    if (inputs[j].equals("S")){
                        sooyeon[i][j] = 0;
                        q2.offer(new int[]{i, j, 0});
                    }
                }
            }

            spread();
            int time = escape();
            if (time == 0){
                sb.append("#").append(t + 1).append(" ").append("GAME OVER").append("\n");
                continue;
            }
            sb.append("#").append(t + 1).append(" ").append(time).append("\n");
        }

        System.out.println(sb);
    }

    static void spread(){

        while(!q1.isEmpty()){
            int[] cur = q1.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny)) continue;
                if (devil[nx][ny] != -1) continue;
                if (map[nx][ny] == 'X' || map[nx][ny] == 'D') continue;
                q1.offer(new int[]{nx, ny, cur[2] + 1});
                devil[nx][ny] = cur[2] + 1;
            }
        }
    }

    static int escape(){
        while(!q2.isEmpty()){
            int[] cur = q2.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!check(nx, ny)) continue;
                if (map[nx][ny] == 'D')
                    return cur[2] + 1;
                if (sooyeon[nx][ny] != -1) continue;
                if (map[nx][ny] == 'X') continue;
                if (devil[nx][ny] != -1 && cur[2] + 1 >= devil[nx][ny]) continue;
                q2.offer(new int[]{nx, ny, cur[2] + 1});
                sooyeon[nx][ny] = cur[2] + 1;
            }
        }
        return 0;
    }

    static boolean check(int x, int y){
        if (x < 0 || x >= n || y < 0 || y >= m){
            return false;
        }
        return true;
    }
}