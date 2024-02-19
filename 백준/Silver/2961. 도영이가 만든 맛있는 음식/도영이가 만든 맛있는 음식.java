import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] flavors;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        flavors = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            flavors[i][0] = Integer.parseInt(st.nextToken());
            flavors[i][1] = Integer.parseInt(st.nextToken());
        }

        powerSet(0, 1, 0);
        System.out.println(min);
    }

    // 물은 요리가 아니다 -> 공집합 제외
    static void powerSet(int cnt, int sour, int bitter) {

        if (cnt == N) {

            if (sour != 1 || bitter != 0) {
                min = Math.min(min, Math.abs(sour - bitter));
            }
            return;
        }

        powerSet(cnt + 1, sour * flavors[cnt][0], bitter + flavors[cnt][1]);
        powerSet(cnt + 1, sour, bitter);
    }
}