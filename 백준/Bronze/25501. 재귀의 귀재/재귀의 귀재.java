import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			String s = br.readLine();
			System.out.println(isPalindrome(s) + " " + cnt);
			cnt = 0;
		}
	}

	static int isPalindrome(String s) {
		
		return recursion(s, 0, s.length()-1);
	}
	
	static int recursion(String s, int l, int r) {
		cnt++;
		if (l >= r) return 1;
		else if (s.charAt(l) != s.charAt(r)) return 0;
		else return recursion(s, l+1, r-1);
	}
}
