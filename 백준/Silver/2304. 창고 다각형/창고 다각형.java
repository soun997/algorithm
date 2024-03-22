import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int start = Integer.MAX_VALUE;
    static int end = Integer.MIN_VALUE;
    static int maxHeight = Integer.MIN_VALUE;
    static int[] columns = new int[1001];
    static int[] M = new int[1001];
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            columns[idx] = height;
            start = Math.min(start, idx);
            end = Math.max(end, idx);
            maxHeight = Math.max(maxHeight, height);
        }

        prefixMax();
        System.out.println(min);
    }

    static void prefixMax() {

        int p1 = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= 1000; i++) {
            if (columns[i] == maxHeight) {
                p1 = i;
                break;
            }
            max = Math.max(max, columns[i]);
            min += max;
        }
        int p2 = 0;
        max = Integer.MIN_VALUE;
        for (int i = 1000; i >= 0; i--) {
            if (columns[i] == maxHeight) {
                p2 = i + 1;
                break;
            }
            max = Math.max(max, columns[i]);
            min += max;
        }

        min += (p2 - p1) * maxHeight;
    }

    static int left() {

        for (int i = 0; i <= 1000; i++) {
            if(columns[i] == maxHeight) {
                return i;
            }
            min += M[i];
        }
        return 0;
    }

    static int right() {

        for (int i = 1000; i >= 0; i--) {
            if (columns[i] == maxHeight) {
                return i + 1;
            }
            min += M[i];
        }
        return 1000;
    }
}