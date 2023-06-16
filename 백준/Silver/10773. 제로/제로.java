import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int K;
    static int sum;
    static Stack<Integer> prev;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        sum = 0;
        prev = new Stack<>();
        for (int t = 0; t < K; t++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0){
                sum = sum - prev.pop();
                continue;
            }
            sum = sum + input;
            prev.push(input);
        }
        System.out.println(sum);
    }
}