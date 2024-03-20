import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] have;
    static int[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        have = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            have[i] = Integer.parseInt(st.nextToken());
        }
        have[N] = Integer.MAX_VALUE;
        Arrays.sort(have);
        M = Integer.parseInt(br.readLine());
        count = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int end = upperBound(target);
            int start = lowerBound(target);
            count[i] = end - start;
        }

        StringBuilder sb = new StringBuilder();
        for (int c : count) {
            sb.append(c).append(" ");
        }
        System.out.println(sb);
    }
    static int upperBound(int target) {

        int start = 0;
        int end = N;
        while(start < end) {
            int mid = (start + end) / 2;
            if (have[mid] <= target) {
                start = mid + 1;
                continue;
            }
            end = mid;
        }
        return end;
    }

    static int lowerBound(int target) {

        int start = 0;
        int end = N;
        while(start < end) {
            int mid = (start + end) / 2;
            if (have[mid] < target) {
                start = mid + 1;
                continue;
            }
            end = mid;
        }
        return end;
    }
}