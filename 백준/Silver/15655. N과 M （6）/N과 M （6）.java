import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] numbers;
    static int[] sequence;
    static StringBuilder result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        sequence = new int[M];
        result = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        combination(0, 0);

        System.out.println(result);
    }

    static void combination(int start, int cnt){
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                result.append(sequence[i]).append(" ");
            }
            result.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            sequence[cnt] = numbers[i];
            combination(i + 1, cnt + 1);
        }
    }
}