import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) list.add(new ArrayList<>());
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			list.get(v1).add(v2);
			list.get(v2).add(v1);
		}
		
		int head = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(head);
		boolean[] infected = new boolean[n+1];
		infected[1] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			head = q.poll();
			for (int i = 0; i < list.get(head).size(); i++) {
				int computer = list.get(head).get(i);
				if (!infected[computer]) {
					q.add(computer);
					infected[computer] = true;
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
