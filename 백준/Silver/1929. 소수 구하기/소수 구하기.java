import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int M, N;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        numbers = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            numbers[i] = i;
        }

        for (int i = 2; i <= N; i++) {
            // 이미 지운 수 -> 건너뛴다
            if (numbers[i] == 0){
                continue;
            }

            // i의 배수부터 출발, 가능한 배수들은 모두 소수에서 제외
            for (int j = 2 * i; j <= N; j = j + i) {
                numbers[j] = 0;
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = M; i <= N; i++) {
            // 소수가 아니라면
            if (numbers[i] == 0){
                continue;
            }
            answer.append(numbers[i]).append("\n");
        }
        System.out.println(answer);
    }
}