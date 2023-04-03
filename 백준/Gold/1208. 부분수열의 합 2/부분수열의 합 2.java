import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static long[] k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        k = new long[8000001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (number < 0){
                for (int j = -number; j <= 8000000; j++) {
                    k[number + j] = k[number + j] + k[j];
                }
            }
            else {
                for (int j = 8000000 - number; j >= 0; j--) {
                    k[number + j] = k[number + j] + k[j];
                }
            }
            k[number + 4000000]++;
        }
        System.out.println(k[S + 4000000]);
    }

}