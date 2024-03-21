import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] solutions;
    static int answerDiff = Integer.MAX_VALUE;
    static int[] answer = new int[2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        solutions = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N - 1;
        while(start < end) {

            int sum = solutions[start] + solutions[end];
            if (answerDiff >= Math.abs(sum)) {
                answerDiff = Math.abs(sum);
                answer[0] = solutions[start];
                answer[1] = solutions[end];
            }
            if (sum == 0) {
                break;
            }
            if (sum > 0) {
                end = end - 1;
            }
            if (sum < 0) {
                start = start + 1;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}