import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int cases;
    static int[] passes;
    static int[] plans;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        cases = Integer.parseInt(br.readLine());
        for (int t = 0; t < cases; t++) {
            passes = new int[4];
            plans = new int[12];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                passes[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                plans[i] = Integer.parseInt(st.nextToken());
            }

            min = passes[3];

            dfs(0, 0);

            sb.append("#").append(t + 1).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int month, int sum) {

        if (month >= 12){
            //System.out.println(min);
            min = Math.min(min, sum);
            return;
        }

        int days = plans[month];

        if (days == 0){
            dfs(month + 1, sum);
        }
        else {
            // 1일권
            dfs(month + 1, sum + passes[0] * days);
            // 1개월권
            dfs(month + 1, sum + passes[1]);
            // 3개월권
            dfs(month + 3, sum + passes[2]);
        }
    }
}
