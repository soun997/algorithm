import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] p;
	static int[] rank;
	static int n;
	static int m;
	static PriorityQueue<Computer> links;
	static int cnt;
	static int min;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		links = new PriorityQueue<>();
		cnt = 0;
		min = 0;
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			links.offer(new Computer(a, b, weight));
		}
		
		makeSet();
		
		while(cnt != n - 1) {
			Computer c = links.poll();
			if (union(c.a, c.b)) {
				//print();
				//System.out.println(c.weight);
				min += c.weight;
				//System.out.println(min);
			}			
		}
		System.out.println(min);
	}
	
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px == py) return false;
		if (rank[px] > rank[py]) {
			p[py] = p[px];
			cnt++;
		}
		else {
			p[px] = p[py];
			cnt++;
			if (rank[px] == rank[py]) {
				rank[py]++;
			}
		}
		return true;
	}
	
	static int find(int x) {
		if (x == p[x]) return x;
		
		return p[x] = find(p[x]);
	}

	static void print() {
		System.out.println(Arrays.toString(p));
		System.out.println(Arrays.toString(rank));
	}
	
	static void makeSet() {
		p = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			p[i] = i;
			rank[i] = 1;
		}
	}
	
	static class Computer implements Comparable<Computer>{
		int a;
		int b;
		int weight;
		
		public Computer(int a, int b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Computer other) {
			
			return Integer.compare(this.weight, other.weight);
		}
	}
}