import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] trees;
    static int max = Integer.MIN_VALUE;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        trees = new int[N];
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 1_000_000_000;
        while(start <= end) {
            int mid = (start + end) / 2;

            long total = 0;
            for (int tree : trees) {
                long diff = Math.max(tree - mid, 0);
                total += diff;
            }
            if (total >= M) {
                max = Math.max(max, mid);
                start = mid + 1;
                continue;
            }
            end = mid - 1;
        }
        System.out.println(max);
    }
}