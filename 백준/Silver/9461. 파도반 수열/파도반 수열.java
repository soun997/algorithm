import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int t = 0; t < N; t++) {
            int k = Integer.parseInt(br.readLine());
            long[] arr = new long[k + 5];
            arr[1] = 1;
            arr[2] = 1;
            arr[3] = 1;
            arr[4] = 2;
            arr[5] = 2;

            int cnt = 1;
            for (int i = 6; i <= k; i++) {
                arr[i] = arr[i - 1] + arr[cnt++];
            }

            sb.append(arr[k]).append("\n");
        }
        System.out.println(sb);
    }
}