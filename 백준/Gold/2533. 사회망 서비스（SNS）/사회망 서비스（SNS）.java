import java.io.*;
import java.util.*;

public class Main {
	public static LinkedList<Integer>[] adj;
	public static boolean[] visited;
	public static int[][] dp; //[][0]: 해당노드가 E가 아닐 때, [][1]: 해당노드가 E일 때
	public static int N;
	public static int answer = 0;

	public static void main(String[] args) throws Exception{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력 및 초기화
		N = read();
		adj = new LinkedList[N+1];
		visited = new boolean[N+1];
		dp = new int[N+1][2];

		for (int i=1; i<N+1; i++) {
			adj[i] = new LinkedList<Integer>();
		}

		for(int i=0; i<N-1; i++) {
			int num1 = read();
			int num2 = read();
			adj[num1].add(num2);
			adj[num2].add(num1);
		}

		dp(1, -1); //루트 1설정
		
		sb.append(Math.min(dp[1][0], dp[1][1]));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dp(int cur, int parent) {
		dp[cur][0] = 0;
		dp[cur][1] = 1;
		
		for(int child : adj[cur]) {
			if(child != parent) { 
				dp(child, cur); 
				dp[cur][0] += dp[child][1]; //자신이 E가 아니면 자식은 무조건 E여야 한다.
				dp[cur][1] += Math.min(dp[child][0], dp[child][1]); //자신이 E이면 자식은 상관없다. (최소를 뽑는다.)
			}
		}
	}
    
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}