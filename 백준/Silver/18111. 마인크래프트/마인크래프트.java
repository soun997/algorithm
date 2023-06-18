import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, B;
    static int[][] ground;
    static int min;
    static int height;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        ground = new int[N][M];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k <= 256; k++) {
            int added = 0;
            int required = 0;
            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 블럭 놓아야 함
                    if (ground[i][j] < k){
                        required = required + k - ground[i][j];
                        result = result + k - ground[i][j];
                    }
                    // 블럭 뿌셔야 함
                    if (ground[i][j] > k){
                        added = added + ground[i][j] - k;
                        result = result + 2 * (ground[i][j] - k);
                    }
                }
            }
            // 불가능한 경우 -> continue;
            if (required > added + B){
                continue;
            }
            if (result <= min){
                min = result;
                height = k;
            }
        }

        System.out.println(min + " " + height);
    }
}