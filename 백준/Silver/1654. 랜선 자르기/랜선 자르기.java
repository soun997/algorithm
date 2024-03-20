import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 랜선 자르기
public class Main {

    static int K, N;
    static int[] wires;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        wires = new int[K];

        long max = 0; // 최댓값을 long으로 설정하여 오버플로우 방지

        for (int i = 0; i < K; i++) {
            wires[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, wires[i]);
        }

        System.out.println(binarySearch(0, max));
    }

    static long binarySearch(long left, long right) {
        long result = 0;

        while (left < right) {
            long mid = (left + right) / 2 + 1; // mid 길이로 랜선을 자를 때, N개를 만들 수 있는지?

            if (isPossible(mid)) {
                result = mid; // 가능한 경우에 mid를 저장해둠
                left = mid; // 탐색 범위를 오른쪽으로 좁힘
            } else {
                right = mid - 1; // 탐색 범위를 왼쪽으로 좁힘
            }
        }

        return result;
    }

    static boolean isPossible(long length) {
        long count = 0;

        for (int wire : wires) {
            count += wire / length;
        }

        return count >= N;
    }
}