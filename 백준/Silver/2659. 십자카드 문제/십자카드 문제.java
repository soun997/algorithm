import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] inputs;
    static int max;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        inputs = new int[4];
        max = 0;
        count = 0;
        for (int i = 0; i < 4; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        max = getMinimumClockNumber(inputs);

        for (int i = 1111; i < max; i++) {
            if (isClockNumber(i)){
                count++;
            }
        }
        System.out.println(count + 1);
    }

    static boolean isClockNumber(int number){
        int[] digits = { number / 1000, (number % 1000) / 100, (number % 100) / 10, number % 10};
        if (number == getMinimumClockNumber(digits)){
            return true;
        }
        return false;
    }

    static int getMinimumClockNumber(int[] digits){
        int[] clockNumber = new int[4];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            clockNumber[i] = makeClockNumber(digits, i);
            min = Math.min(min, clockNumber[i]);
        }
        return min;
    }

    static int makeClockNumber(int[] digits, int offset){
        return digits[offset % 4] * 1000 +
                digits[(offset + 1) % 4] * 100 +
                digits[(offset + 2) % 4] * 10 +
                digits[(offset + 3) % 4];
    }
}