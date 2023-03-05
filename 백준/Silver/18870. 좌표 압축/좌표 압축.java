import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] storedArr;
    static int[] setArr;
    static int newLength;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        setArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        storedArr = Arrays.copyOf(arr, N);
        Arrays.sort(arr);
        newLength = removeDuplicated();

        for (int i = 0; i < N; i++) {
            sb.append(binarySearch(storedArr[i])).append(" ");
        }
        System.out.println(sb);
    }

    static int removeDuplicated(){

        int cnt = 1;
        setArr[0] = arr[0];
        for (int i = 1; i < N; i++) {
            if (setArr[cnt - 1] == arr[i]){
                continue;
            }
            setArr[cnt] = arr[i];
            cnt++;
        }
        return cnt;
    }

    static int binarySearch(int target){

        int start = 0;
        int end = newLength - 1;

        while(start <= end){

            int mid = (start + end) / 2;

            if (setArr[mid] == target){
                return mid;
            }
            if (setArr[mid] < target){
                start = mid + 1;
            }
            if (setArr[mid] > target){
                end = mid - 1;
            }
        }
        return -1;
    }
}