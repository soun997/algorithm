import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int cases;
    static int n;
    static int[] operator;
    static int[] nums;
    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        cases = Integer.parseInt(br.readLine());

        for (int t = 0; t < cases; t++) {

            n = Integer.parseInt(br.readLine());
            operator = new int[4];
            nums = new int[n];
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                operator[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            dfs(1, nums[0], operator[0], operator[1], operator[2], operator[3]);
            sb.append("#").append(t + 1).append(" ").append(max - min).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int idx, int result,
                    int sum, int sub, int mul, int div){

        if (idx == n){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        if (sum > 0){
            // 덧셈
            dfs(idx + 1, result + nums[idx],
                    sum - 1, sub, mul, div);
        }
        if (sub > 0) {
            // 뺄셈
            dfs(idx + 1, result - nums[idx],
                    sum, sub - 1, mul, div);
        }
        if (mul > 0) {
            // 곱셈
            dfs(idx + 1, result * nums[idx],
                    sum, sub, mul - 1, div);
        }
        if (div > 0) {
            // 나눗셈
            dfs(idx + 1, result / nums[idx],
                    sum, sub, mul, div - 1);
        }
    }
}
