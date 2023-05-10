import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;
    static int[] sequence;
    static boolean[] visited;
    static Stack<Integer> stk;
    static StringBuilder result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sequence = new int[N + 1];
        visited = new boolean[N + 1];
        stk = new Stack<>();
        result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        int idx = 1;
        for (int i = 1; i <= N; i++) {
            stk.push(i);
            result.append("+\n");
            if (stk.peek() == sequence[idx]){
                while(!stk.isEmpty() && stk.peek() == sequence[idx]){
                    visited[idx] = true;
                    stk.pop();
                    result.append("-\n");
                    idx++;
                }
            }
        }
        for (int i = 1; i <= N; i++){
            if (!visited[i]){
                System.out.println("NO");
                return;
            }
        }
        System.out.println(result);
    }
}