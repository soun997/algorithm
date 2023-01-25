import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(sc.nextLine());
		String[] decrypto = { "000000", "001111", "010011", "011100", 
								"100110", "101001", "110101", "111010" };
		String input = sc.nextLine();
		String[] sliced = new String[n];
		
		for (int i = 0; i < n; i++) {
			sliced[i] = input.substring(6 * i, 6 * (i + 1));
			boolean isValid = false;	// 해독 가능한 암호인지
			for (int j = 0; j < decrypto.length; j++) {
				// 해독 문자열 목록에 있는 문자열이라면 -> 해독 가능
				if (sliced[i].equals(decrypto[j])) {
					sb.append((char)(65 + j));
					isValid = true;
					break;
				}
				else {
					int cnt = 0;
					for (int k = 0; k < 6; k++) {
						if (sliced[i].charAt(k) != decrypto[j].charAt(k)) {
							cnt++;
						}
					}
					if (cnt < 2) {
						sb.append((char)(65 + j));
						isValid = true;
						break;
					}
				}
			}
			if (!isValid) {
				System.out.println(i + 1);
				sc.close();
				return;
			}
		}
		System.out.println(sb);
		sc.close();
	}
	
}