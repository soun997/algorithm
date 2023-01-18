import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			sb1.append(" ");
		}
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				sb2.append(sb1).append("*").append("\n");
				sb1.append("*");
			}
			else {
				sb2.append(sb1).append("**").append("\n");
				sb1.append("**");
			}
			sb1.delete(0, 1);
		}
		System.out.print(sb2.toString());
		
		sb1.setLength(0);
		sb2.setLength(0);
		
		for (int i = 0; i < N * 2 - 1; i++) {
			sb1.append("*");
		}
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				sb1.delete(0,  2);
				continue;
			}
			sb2.append(" ").append(sb1).append("\n");
			sb1.insert(0, " ");
			sb1.delete(sb1.length() - 2, sb1.length());
		}
		System.out.print(sb2.toString());
	}
}
