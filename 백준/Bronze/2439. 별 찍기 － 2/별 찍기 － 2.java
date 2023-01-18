import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N-1-i; j++) {
				System.out.print(" ");
			}
			for (int k = 0; k < i+1; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
}
