import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int digit = Integer.toString(N).length();
        int count = 1;
        int sum = 0;
        while (count < digit){
            sum = sum + count * 9 * (int)Math.pow(10, count++ - 1);
        }

        sum = sum + (N - (int)Math.pow(10, digit - 1) + 1) * digit;

        System.out.println(sum);
    }
}