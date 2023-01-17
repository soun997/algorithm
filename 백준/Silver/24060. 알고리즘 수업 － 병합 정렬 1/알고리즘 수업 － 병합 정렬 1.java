import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] tmp;
    static int cnt = 0;
    static int K;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        tmp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(A, 0, N - 1);

        if (K > cnt) System.out.println(-1);
    }

    // A[p..r]을 오름차순 정렬
    static void mergeSort(int[] A, int p, int r) {

        if (p < r){
            int q = (int)Math.floor((p + r) / 2D);
            mergeSort(A, p, q); // 전반부 정렬
            mergeSort(A, q + 1, r); // 후반부 정렬
            merge(A, p, q, r);  // 병합
        }
    }

    // A[p..q]와 A[p+1..r]을 병합 -> 오름차순 정렬된 상태로
    static void merge(int[] A, int p, int q, int r){
        if (cnt > K){
            return;
        }
        int i = p;
        int j = q + 1;
        int t = 0;

        while(i <= q && j <= r){
            if (A[i] <= A[j]) {
                tmp[t++] = A[i++];
            } else {
                tmp[t++] = A[j++];
            }
        }
        // 왼쪽 배열 부분이 남은 경우
        while (i <= q) {
            tmp[t++] = A[i++];
        }
        // 오른쪽 배열 부분이 남은 경우
        while (j <= r) {
            tmp[t++] = A[j++];
        }
        i = p;
        t = 0;
        // 결과를 A[p..r]에 저장
        while (i <= r) {
            cnt++;
            if (cnt == K){
                System.out.println(tmp[t]);
                break;
            }
            A[i++] = tmp[t++];
            //System.out.println(Arrays.toString(A));
        }
    }
}
