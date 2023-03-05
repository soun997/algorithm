import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long[] solutions;
    static long min;
    static long[] answer;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        solutions = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(solutions);

        min = Long.MAX_VALUE;
        answer = new long[3];

        for (int i = 0; i < N - 2; i++) {

            for (int j = i + 1; j < N - 1; j++){

                long target = solutions[i] + solutions[j];
                int index = binarySearch(target, j);
                if (Math.abs(target + solutions[index]) < min){
                    min = Math.abs(target + solutions[index]);
                    answer[0] = solutions[i];
                    answer[1] = solutions[j];
                    answer[2] = solutions[index];
                }
            }
        }
        System.out.printf("%d %d %d", answer[0], answer[1], answer[2]);
    }

    static int binarySearch(long target, int s){
        int start = s + 1;
        int end = N - 1;
        int mid = -1;
        while (start <= end){
            mid = (start + end) / 2;
            if (solutions[mid] + target == 0){
                return mid;
            }
            if (solutions[mid] + target < 0){
                start = mid + 1;
            }
            if (solutions[mid] + target > 0){
                end = mid - 1;
            }
        }

        if (s + 1 == mid){
            return mid;
        }

        long sum1 = target + solutions[mid];
        long sum2 = target + solutions[mid - 1];
        if (Math.abs(sum1) < Math.abs(sum2)){
            return mid;
        }
        return mid - 1;
    }
}