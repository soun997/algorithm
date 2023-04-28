import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Counsel[] counsels;
    static int[] dp;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        counsels = new Counsel[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int profit = Integer.parseInt(st.nextToken());
            counsels[i] = new Counsel(time, profit);
        }

        dfs(1, 0);

        System.out.println(max);
    }

    static void dfs(int day, int sumOfProfit){
        if (day > N) {
            max = Math.max(max, sumOfProfit);
            return;
        }

        // 해당 일의 상담을 선택한 경우
        if (day + counsels[day].time <= N + 1){
            dfs(day + counsels[day].time, sumOfProfit + counsels[day].profit);
        }
        // 해당 일의 상담을 선택하지 않은 경우
        dfs(day + 1, sumOfProfit);
    }

    static class Counsel {

        int time;
        int profit;

        public Counsel(int time, int profit){
            this.time = time;
            this.profit = profit;
        }
    }
}