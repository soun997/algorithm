import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 2 * N - 1; i++) {
			for (int k = 0; k < -1 * Math.abs(i-(N-1)) + (N-1); k++){
				sb.append(" ");
			}
			for (int j = 0; j < 2 * Math.abs(i-(N-1)) + 1; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

}
