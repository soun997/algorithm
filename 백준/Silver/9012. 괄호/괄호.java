import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        for (int t = 0; t < N; t++) {
            char[] input = br.readLine().toCharArray();
            Stack<Character> stk = new Stack<>();
            for (char ch : input) {
                if (!stk.isEmpty() && match(stk.peek(), ch)){
                    stk.pop();
                    continue;
                }
                stk.push(ch);
            }
            if (stk.isEmpty()){
                answer.append("YES").append("\n");
                continue;
            }
            answer.append("NO").append("\n");
        }
        System.out.println(answer);
    }

    static boolean match(char left, char right){
        if (left == '(' && right == ')') {
            return true;
        }
        return false;
    }
}
