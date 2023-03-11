import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        List<Integer> sorted = new ArrayList<>();
        int[][] LCS = new int[n + 1][n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sorted.add(0);
        for (int i = 0; i < n + 1; i++) {
            if (!sorted.contains(arr[i])) {
                sorted.add(arr[i]);
            }
        }
        Collections.sort(sorted);

        int result = 0;
        List<Integer> longestSubsequence = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < sorted.size(); j++) {
                if (arr[i] == sorted.get(j)){
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                    result = Math.max(result, LCS[i][j]);
                    continue;
                }

                LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                result = Math.max(result, LCS[i][j]);
            }
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        int[] dx = {-1, 0};
        int[] dy = {0, -1};
        boolean[][] visited = new boolean[n + 1][sorted.size()];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{n, sorted.size() - 1});
        while (!q.isEmpty()){

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
                stk.push(arr[cur[0]]);
                if (LCS[cur[0]][cur[1]] == 1){
                    break;
                }
                q.clear();
                q.offer(new int[]{cur[0] - 1, cur[1] - 1});
            }
        }
        System.out.println(result);
        while (!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }
        System.out.println(sb);
    }
}