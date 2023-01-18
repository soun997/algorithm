import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N = 0;
	static int[][] matrix; 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		N = 4*(N-1)+1;
		StringBuilder sb = new StringBuilder();
		matrix = new int[N][N];
		
		square(0, N);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(matrix[i][j] == 1) {
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
	
	static void square(int r, int n) {
		
		if (n==1) {
			matrix[r][r] = 1;
		}
		else {
			for (int i = 0; i < n; i++) {	
				matrix[r][r+i] = 1;
				matrix[r+i][r] = 1;
				matrix[N-r-1][r+i] = 1;
				matrix[r+i][N-r-1] = 1;
			}
			square(r+2, n-4);
		}
	}

}
