import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N];
        int[] LIS = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int maxLength = Integer.MIN_VALUE;
        // LIS 배열 채우기 + LIS 구하기
        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i] && LIS[j] + 1 > LIS[i]){
                    LIS[i] = LIS[j] + 1;
                }

            }
            maxLength = Math.max(maxLength, LIS[i]);
        }

        StringBuilder sb = new StringBuilder();

        int[] elements = new int[maxLength];
        int count = maxLength;
        for (int i = N - 1; i >= 0; i--) {
            if (LIS[i] == count){
                elements[count - 1] = sequence[i];
                count--;
            }
        }

        for (int i = 0; i < maxLength; i++) {
            sb.append(elements[i]).append(" ");
        }

        System.out.println(maxLength);
        System.out.println(sb);
    }
}