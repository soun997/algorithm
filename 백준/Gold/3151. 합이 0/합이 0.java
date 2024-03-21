import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] students;
    static long answer = 0;
    static int R = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new int[N + 1];
        students[N] = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(students);

        for (int i = 0; i < N - 1; i++) {
            binarySearch(i + 1, -1 * students[i]);
        }

        System.out.println(answer);
    }

    static void binarySearch(int initStart, int target) {

        int start = initStart;
        int end = N - 1;

        while(start < end) {
            int sum = students[start] + students[end];
            if (sum == target) {
                int startCount = upperBound(initStart, students[start]) - lowerBound(initStart, students[start]);
                int endCount = upperBound(initStart, students[end]) - lowerBound(initStart, students[end]);
                if (students[start] == students[end]) {
                    answer += (long) startCount * (startCount - 1) / 2;
                } else {
                    answer += (long) startCount * endCount;
                }
                start += startCount;
                end -= endCount;
            }
            if (sum > target) {
                end--;
            }
            if (sum < target) {
                start++;
            }
        }
    }

    static int upperBound(int initStart, int target) {

        int start = initStart;
        int end = N;

        while(start < end) {
            int mid = (start + end) / 2;
            if (students[mid] <= target) {
                start = mid + 1;
                continue;
            }
            end = mid;
        }
        return end;
    }

    static int lowerBound(int initStart, int target) {

        int start = initStart;
        int end = N;

        while(start < end) {
            int mid = (start + end) / 2;
            if (students[mid] < target) {
                start = mid + 1;
                continue;
            }
            end = mid;
        }
        return end;
    }
}