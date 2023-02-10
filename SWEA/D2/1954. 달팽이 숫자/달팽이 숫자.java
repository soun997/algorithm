import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    static int n;
    static int[][] snail;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int cnt;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cases = Integer.parseInt(br.readLine());

        for (int t = 0; t < cases; t++) {
            n = Integer.parseInt(br.readLine());
            snail = new int[n][n];

            int r = 0;
            int c = 0;
            int d = 0;
            for (int i = 0; i < n; i++) {
            	for (int j = 0; j < n; j++){
            		snail[r][c] = i * n + j + 1;
            		// 바깥으로 나가거나 이미 방문한 곳이라면
            		if (!(check(r + dx[d], c + dy[d]) 
            				&& snail[r + dx[d]][c + dy[d]] == 0)) {
            			d = (d + 1) % 4;
            		}
            		
            		r = r + dx[d];
            		c = c + dy[d];
            	}
            }
            sb.append("#").append(t + 1).append("\n");
            for (int i = 0; i < n; i++) {
            	for (int j = 0; j < n; j++) {
            		sb.append(snail[i][j]).append(" ");
            	}
            	sb.append("\n");
            }
            
        }
        System.out.println(sb);
    }

	static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= n || ny < 0 || ny >= n)
			return false;
		return true;
	}
}