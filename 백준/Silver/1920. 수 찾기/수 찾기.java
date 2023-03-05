import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] nArr;
    static int M;

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
            sb.append(binarySearch(Integer.parseInt(st.nextToken()))).append("\n");
        }

        System.out.println(sb);
    }

    static int binarySearch(int target){

        int start = 0;
        int end = N - 1;

        while (start <= end){
            int mid = (start + end) / 2;

            if (nArr[mid] == target){
                return 1;
            }

            if (nArr[mid] < target){
                start = mid + 1;
            }

            if (nArr[mid] > target){
                end = mid - 1;
            }
        }

        return 0;
    }
}