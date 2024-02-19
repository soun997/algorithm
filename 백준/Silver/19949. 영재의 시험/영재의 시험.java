import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N = 10;
    static int[] choices;
    static int[] answers;
    static int total = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        choices = new int[N];
        answers = new int[N];
        for (int i = 0; i < N; i++) {
            answers[i] = Integer.parseInt(st.nextToken());
        }
        backtracking(0);
        System.out.println(total);
    }

    static void backtracking(int cnt) {

        if (!checkDuplication(cnt)) {
            return;
        }
        if (cnt == N) {

            if (isPass()) {
                //System.out.println(Arrays.toString(choices));
                total++;
            }
            return;
        }
        for (int i = 1; i <= 5; i++) {
            choices[cnt] = i;
            backtracking(cnt + 1);
            choices[cnt] = 0;
        }
    }

    static boolean checkDuplication(int cnt) {

        int duplicated = 1;
        int prev = choices[0];
        for (int i = 1; i < cnt; i++) {
            if (choices[i] == prev) {
                duplicated++;
                if (duplicated >= 3) {
                    return false;
                }
            } else {
                duplicated = 1;
                prev = choices[i];
            }
        }
        return true;
    }

    static boolean isPass() {

        int score = 0;
        for (int i = 0; i < N; i++) {
            if (choices[i] == answers[i]) {
                score++;
            }
        }
        return score >= 5;
    }
}