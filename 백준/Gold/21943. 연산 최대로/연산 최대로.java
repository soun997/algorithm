import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numbers;
    static boolean[] visited;
    static int[] operand;
    static boolean[] operator;
    static int sum, mul;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        visited = new boolean[N + 1];
        operand = new int[N];
        operator = new boolean[N - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        sum = Integer.parseInt(st.nextToken());
        mul = Integer.parseInt(st.nextToken());
        max = 0;

        pickOperand(0);

        System.out.println(max);
    }

    static void pickOperand(int cnt) {
        if (cnt == N) {
            pickOperator(0, 0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            operand[cnt] = numbers[i];
            pickOperand(cnt + 1);
            visited[i] = false;
        }
    }

    static void pickOperator(int start, int cnt) {
        if (cnt == mul) {
            int result = 1;
            int sum = operand[0];
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i < N; i++) {
                // 곱셈일 경우
                if (operator[i - 1]) {
                    list.add(sum);
                    sum = operand[i];
                    continue;
                }
                sum = sum + operand[i];
            }
            for (int num : list) {
                result = result * num;
            }
            result = result * sum;
            max = Math.max(max, result);

            return;
        }

        for (int i = start; i < N - 1; i++) {
            operator[i] = true;
            pickOperator(i + 1, cnt + 1);
            operator[i] = false;
        }
    }
}