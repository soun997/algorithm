import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 2 * N - 1; i++) {
			for (int j = 0; j < -1 * Math.abs(i-(N-1)) + N; j++) {
				sb.append("*");
			}
			for (int j = 0; j < Math.abs(2 * N - 2 * (i + 1)); j++) {
				sb.append(" ");
			}
			for (int j = 0; j < -1 * Math.abs(i-(N-1)) + N; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
