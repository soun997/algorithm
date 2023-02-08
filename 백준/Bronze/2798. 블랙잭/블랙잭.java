import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[] cards;
	static boolean[] visited;
	static int[] choices = new int[3];
	static int winner = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		cards = new int[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		combi(0, 0);
		System.out.println(winner);
	}
	
	static void combi(int cnt, int start) {
		
		if (cnt == 3) {
			int result = 0;
			for (int i = 0; i < 3; i++) {
				result += choices[i];
			}
			if (result <= m) {
				winner = Math.max(winner, result);
			}
			return;
		}
		
		for (int i = start; i < n; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			choices[cnt] = cards[i];
			combi(cnt + 1, start + 1);
			visited[i] = false;
		}
	}
}
