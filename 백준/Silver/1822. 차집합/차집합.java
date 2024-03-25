import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int nA, nB;
    static int[] A;
    static boolean[] isExists;
    static int total = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nA = Integer.parseInt(st.nextToken());
        nB = Integer.parseInt(st.nextToken());
        A = new int[nA];
        isExists = new boolean[nA];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nA; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nB; i++) {
            int idx = binarySearch(Integer.parseInt(st.nextToken()));
            if (idx != -1) {
                isExists[idx] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nA; i++) {
            if (!isExists[i]) {
                sb.append(A[i]).append(" ");
            }
        }
        System.out.println(nA - total);
        System.out.println(sb);
    }

    static int binarySearch(int target) {
        int start = 0;
        int end = nA - 1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target) {
                total++;
                return mid;
            }
            if (A[mid] < target) {
                start = mid + 1;
            }
            if (A[mid] > target) {
                end = mid - 1;
            }
        }
        return -1;  // 못찾은 경우
    }
}