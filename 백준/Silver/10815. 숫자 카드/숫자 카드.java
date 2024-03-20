import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[] have;
    static int[] find;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        have = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            have[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(have);
        M = Integer.parseInt(br.readLine());
        find = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            find[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (isExist(find[i])) {
                sb.append(1).append(" ");
                continue;
            }
            sb.append(0).append(" ");
        }

        System.out.println(sb);
    }

    static boolean isExist(int target) {
        int start = 0;
        int end = N - 1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if (have[mid] == target) {
                return true;
            }
            if (have[mid] < target) {
                start = mid + 1;
            }
            if (have[mid] > target) {
                end = mid - 1;
            }
        }
        return false;
    }
}