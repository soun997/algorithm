import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int number;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        number = 666;
        while(N > 1) {
            number++;
            if (String.valueOf(number).contains("666")) {
                N = N - 1;
            }
        }

        System.out.println(number);
    }
}