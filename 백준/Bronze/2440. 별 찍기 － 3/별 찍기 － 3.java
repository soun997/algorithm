import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			sb.append("*");
		}
		for (int i = 0; i < N; i++) {
			System.out.println(sb.toString());
			sb.delete(sb.length() - 1, sb.length());
		}
	}

}
