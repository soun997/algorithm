import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] S = new int[21];
    static int max = 0;
    static boolean[] possibles;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            max += S[i];
        }

        possibles = new boolean[max + 1];
        powerSet(0, 0);
//        System.out.println(possibles);
        System.out.println(findMissing());
    }

    static void powerSet(int idx, int sum) {

        if (idx == N) {
            //System.out.println(sum);
            possibles[sum] = true;
            return;
        }

        powerSet(idx + 1, sum + S[idx]);
        powerSet(idx + 1, sum);
    }

    static int findMissing() {

        for (int i = 1; i <= max; i++) {
            if (!possibles[i]) {
                return i;
            }
        }
        return max + 1;
    }
}