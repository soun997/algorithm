import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// SWEA - 보급로
public class Solution {

	static int TC;
	static int N;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				char[] ch = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = ch[j] - '0';
				}
			
			}
			sb.append("#").append(t).append(" ").append(dijkstra()).append("\n");
		}
		System.out.println(sb);
	}
	
	static int dijkstra() {
		final int INF = Integer.MAX_VALUE;
		int[][] minTime = new int[N][N];	// 출발 정점에서 자신까지 이르는 최소 복구 시간
		boolean[][] visited = new boolean[N][N];
		
		// x, y, 출발지에서 자신까지의 최소 비용(weight)
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				
				return Integer.compare(o1[2], o2[2]);
			}
		});	
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}
		minTime[0][0] = 0;	// 출발 지점의 비용을 0으로 초기화
		pq.offer(new int[] {0, 0, minTime[0][0]});
		
		while(true) {
			
			// step 1
			int[] cur = pq.poll();
			
			// 큐에 남아있는 잔재
			if (visited[cur[0]][cur[1]]) {
				continue;
			}
			visited[cur[0]][cur[1]] = true;
			if (cur[0] == N - 1 && cur[1] == N - 1) {
				return cur[2];	// 도착지에 오면 끝냄
			}
			
			// step 2
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				
				if (check(nx, ny) && !visited[nx][ny] 
						&& minTime[nx][ny] > cur[2] + map[nx][ny]) {
					minTime[nx][ny] = cur[2] + map[nx][ny];
					pq.offer(new int[] {nx, ny, minTime[nx][ny]});
				}
			}			
		}
	}
	
	static boolean check(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}
		return true;
	}
}