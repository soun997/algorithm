import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int mul5 = N / 5;
        int mul25 = N / 25;
        int mul125 = N / 125;

        System.out.println(mul5 + mul25 + mul125);
    }
}