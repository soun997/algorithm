import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[] nArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        nArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nArr);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int lowerIdx = lowerIndex(target, N);
            int upperIdx = upperIndex(target, N);
            sb.append(upperIdx - lowerIdx).append(" ");
        }
        System.out.println(sb);
    }

    // start = end가 될 경우 -> 가능한 후보를 하나 찾은 것 (가장 처음 나타나는 index)
    static int lowerIndex(int target, int length){
        int start = 0;
        int end = length;
        while (start < end){
            int mid = (start + end) / 2;
            if (nArr[mid] < target){
                start = mid + 1;
            }
            if (nArr[mid] >= target){
                end = mid;
            }
        }
        return start;
    }

    static int upperIndex(int target, int length){
        int start = 0;
        int end = length;
        while (start < end){
            int mid = (start + end) / 2;
            if (nArr[mid] <= target){
                start = mid + 1;
            }
            if (nArr[mid] > target){
                end = mid;
            }
        }
        return start;
    }
}