import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Parametric Search
public class Main {

    static int K, N;
    static long[] wires;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        wires = new long[K];
        for (int i = 0; i < K; i++) {
            wires[i] = Integer.parseInt(br.readLine());
        }

        long start = 0;
        long end = Integer.MAX_VALUE;
        while (start < end){
            long mid = (start + end + 1) / 2;
            if (solve(mid)){
                start = mid;
            }
            else {
                end = mid - 1;
            }
        }
        System.out.println(start);
    }

    static boolean solve(long x){
        long cur = 0;
        for (int i = 0; i < K; i++) {
            cur += wires[i] / x;
        }
        return cur >= N;
    }
}