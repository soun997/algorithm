import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int num = 1;
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
		System.out.println(sb2.toString());
	}
}
