import java.util.Scanner;

public class Main {
	
	static int[] height = new int[9];
	static boolean[] visited = new boolean[9];
	static int[] choices = new int[7];
	static int[] answer = new int[7];
	static int answerSum = 0;

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 9; i++) {
			height[i] = sc.nextInt();
		}
		
		combi(0, 0);
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (answer[i] < answer[j]) {
					int temp = answer[i];
					answer[i] = answer[j];
					answer[j] = temp;
				}
			}
		}
		
		for (int i = 0; i < 7; i++) {
			System.out.println(answer[i]);
		}
	}
	
	static void combi(int cnt, int start) {
		
		if (cnt == 7) {
			int[] result = new int[7];
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				result[i] = choices[i];
				sum += choices[i];
			}
			if (sum == 100) {
				answer = result;
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			choices[cnt] = height[i];
			combi(cnt + 1, start + 1);
			visited[i] = false;
		}
	}
}
