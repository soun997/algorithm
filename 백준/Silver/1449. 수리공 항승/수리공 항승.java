import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static int[] leak;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        leak = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            leak[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(leak);

        int tape = 1;
        int start = leak[0];
        for (int i = 1; i < N; i++) {
            // 하나의 테이프로 더 이상 구멍을 막을 수 없다면
            if (leak[i] - start > L - 1){
                start = leak[i];
                tape++;
            }
        }

        System.out.println(tape);
    }
}