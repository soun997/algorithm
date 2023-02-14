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

            dfs(1, nums[0]);
            sb.append("#").append(t + 1).append(" ").append(max - min).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int idx, int result){

        if (idx == n){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        if (operator[0] > 0){
            // 덧셈
            operator[0]--;
            dfs(idx + 1, result + nums[idx]);
            operator[0]++;
        }
        if (operator[1] > 0) {
            // 뺄셈
            operator[1]--;
            dfs(idx + 1, result - nums[idx]);
            operator[1]++;
        }
        if (operator[2] > 0) {
            // 곱셈
            operator[2]--;
            dfs(idx + 1, result * nums[idx]);
            operator[2]++;
        }
        if (operator[3] > 0) {
            // 나눗셈
            operator[3]--;
            dfs(idx + 1, result / nums[idx]);
            operator[3]++;
        }
    }
}