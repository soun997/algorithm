import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();
        int[][] LCS = new int[a.length() + 1][b.length() + 1];

        int result = 0;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)){
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                    result = Math.max(result, LCS[i][j]);
                    continue;
                }

                LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                result = Math.max(result, LCS[i][j]);
            }
        }

        StringBuilder sb = new StringBuilder();
        Stack<Character> stk = new Stack<>();
        int[] dx = {-1, 0};
        int[] dy = {0, -1};
        boolean[][] visited = new boolean[a.length() + 1][b.length() + 1];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{a.length(), b.length()});
        while (!q.isEmpty() && LCS[a.length()][b.length()] != 0){

            int[] cur = q.poll();
            int check = 0;
            for (int d = 0; d < 2; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (visited[nx][ny]){
                    continue;
                }
                if (LCS[nx][ny] == LCS[cur[0]][cur[1]]){
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    continue;
                }
                check++;
            }
            if (check == 2){
                stk.push(a.charAt(cur[0] - 1));
                if (LCS[cur[0]][cur[1]] == 1){
                    break;
                }
                q.clear();
                q.offer(new int[]{cur[0] - 1, cur[1] - 1});
            }
        }
        System.out.println(result);
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        System.out.println(sb);
    }

}