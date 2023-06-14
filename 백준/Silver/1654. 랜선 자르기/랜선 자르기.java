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

        for (int i = 0; i < K; i++) {
            wires[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(binarySearch(0, Integer.MAX_VALUE));
    }

    static int binarySearch(int left, int right){

        while(left < right){
            int mid = left + (right - left) / 2 + 1;    // mid 길이로 랜선을 자를 때, N개를 만들 수 있는지?

            // 만들 수 있다면 -> 해당 mid가 최댓값이 아닐 수도 있으므로 계속 탐색
            if (isPossible(mid)){
                left = mid;
                continue;
            }

            // 만들 수 없다면 -> 길이를 더 줄여야 함
            right = mid - 1;
        }

        return left;
    }

    // 해당 길이로 자를 경우, N개의 랜선을 만들 수 있는 지
    static boolean isPossible(int length){
        int count = 0;
        for (int wire : wires) {
            count = count + wire / length;
        }

        return count >= N;
    }
}