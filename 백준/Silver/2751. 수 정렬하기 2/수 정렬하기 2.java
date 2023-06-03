import java.io.BufferedReader;
import java.io.InputStreamReader;

// 수 정렬하기 2
public class Main {

    static final int OFFSET = 1_000_000;
    static int N;
    static int[] numbers;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[OFFSET * 2 + 1];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            numbers[num + OFFSET]++;
            max = Math.max(max, num + OFFSET);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= max; i++) {
            if (numbers[i] != 0){
                sb.append(i - OFFSET).append("\n");
            }
        }

        System.out.println(sb);
    }
}