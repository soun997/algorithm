import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 용액
 */
public class Main {

    static int N;
    static int[] solutions;
    static int min = Integer.MAX_VALUE;
    static int[] minSolutions = new int[2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        solutions = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
        makeZero();
        System.out.println(minSolutions[0] + " " + minSolutions[1]);
    }

    static void makeZero() {
        int start = 0;
        int end = N - 1;
        while (start < end) {
            int sum = solutions[start] + solutions[end];
            //System.out.println(sum);
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                minSolutions[0] = solutions[start];
                minSolutions[1] = solutions[end];
            }
            if (sum > 0) {
                end--;
            } else {
                start++;
            }
        }
    }
}