import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N;
    static List<Integer> difficulty;
    static int cut;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        difficulty = new ArrayList<>();
        cut = (int)Math.round(N * 15 / 100D);
        for (int i = 0; i < N; i++) {
            difficulty.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(difficulty);

        int sum = 0;
        for (int i = cut; i < N - cut; i++) {
            sum = sum + difficulty.get(i);
        }
        int result = (int)Math.round((double)sum / (N - cut * 2));
        System.out.println(result);
    }
}