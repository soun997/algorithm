import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] sour;
	static int[] bitter;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n];
		sour = new int[n];
		bitter = new int[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}
		powerSet(0, 0, 1);
		System.out.println(min);
	}
	
	static void powerSet(int cnt, int tot, int mul) {
		
		if (cnt == n) {
/*			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					System.out.printf("%d ", bitter[i]);
					System.out.printf("%d ", sour[i]);
				}
			}*/
			//System.out.println();
			if (tot == 0) return;
			min = Math.min(min,  Math.abs(tot - mul));
			//System.out.println(tot + " " + mul);
			return;
		}
		
		visited[cnt] = true;
		powerSet(cnt + 1, tot + bitter[cnt], mul * sour[cnt]);
		visited[cnt] = false;
		powerSet(cnt + 1, tot, mul);
	}
}
