import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // n: 키의 개수(스킬 개수는 2n), m: 퀘스트 개수, k: 퀘스트 클리어에 필요한 스킬 수
    static int n, m, k;
    static boolean[] skills;
    static int[][] quests;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        skills = new boolean[2 * n + 1];
        quests = new int[m][k];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                quests[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backtracking(0, 1);
        System.out.println(max);
    }

    static void backtracking(int cnt, int skill) {

        if (cnt == n) {

            max = Math.max(max, countClearableQuests());
            return;
        }

        for (int i = skill; i <= 2 * n; i++) {
            skills[skill] = true;
            backtracking(cnt + 1, i + 1);
            skills[skill] = false;
        }
    }

    static int countClearableQuests() {

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            boolean flag = false;
            for (int j = 0; j < k; j++) {
                if (!skills[quests[i][j]]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                cnt++;
            }
        }
        return cnt;
    }
}