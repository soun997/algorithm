import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			if (i % 2 == 1) {
				sb.append(" ");
			}
			for (int j = 0; j < 2*N; j++) {
				if (j % 2 == 0) {
					sb.append("*");
				}
				else {
					sb.append(" ");
				}
			}
			sb.append("\n");			
		}
		System.out.println(sb.toString());
	}

}
