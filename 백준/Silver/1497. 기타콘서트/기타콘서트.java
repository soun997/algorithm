import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static Guitar[] guitars;
    static long maxBit;
    static int min = Integer.MAX_VALUE;

    static class Guitar {

        String name;
        long bit;

        public Guitar(String name, long bit) {
            this.name = name;
            this.bit = bit;
        }

        static long stringToBit(String s) {
            String bit = "";
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'Y') {
                    bit += "1";
                } else {
                    bit += "0";
                }
            }
            return Long.parseLong(bit, 2);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        guitars = new Guitar[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            guitars[i] = new Guitar(st.nextToken(), Guitar.stringToBit(st.nextToken()));
        }
        maxBit = Integer.MIN_VALUE;
        powerSet(0, 0, 0);
        if (min == 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    static void powerSet(int cnt, int total, long bit) {

        if (cnt == N) {
            if (bit > maxBit) {
                maxBit = bit;
                min = total;
            }
            if (bit == maxBit) {
                min = Math.min(min, total);
            }
            return;
        }

        powerSet(cnt + 1, total + 1, bit | guitars[cnt].bit);
        powerSet(cnt + 1, total, bit);
    }
}