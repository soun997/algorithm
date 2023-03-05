import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] setArr;
    static int[] addTwoArr;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        setArr = new int[N];
        for (int i = 0; i < N; i++) {
            setArr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(setArr);

        addTwoArr = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++){
            for (int j = i; j < N; j++) {
                addTwoArr[idx++] = setArr[i] + setArr[j];
            }
        }
        Arrays.sort(addTwoArr);

        for (int i = N - 1; i > 0; i--){
            for (int j = 0; j < i; j++) {
                if (binarySearch(setArr[i] - setArr[j])){
                    System.out.println(setArr[i]);
                    return;
                }
            }
        }
    }

    static boolean binarySearch(int target){
        int start = 0;
        int end = N * N - 1;

        while (start <= end){

            int mid = (start + end) / 2;

            if (addTwoArr[mid] == target){
                return true;
            }

            if (addTwoArr[mid] < target){
                start = mid + 1;
            }

            if (addTwoArr[mid] > target){
                end = mid - 1;
            }
        }
        return false;
    }
}