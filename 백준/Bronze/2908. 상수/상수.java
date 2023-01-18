import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int revA = 0;
        int revB = 0;

        for (int i = 0; i < 3; i++){
            revA += (a % 10) * (int)Math.pow(10, 3-i-1);
            revB += (b % 10) * (int)Math.pow(10, 3-i-1);
            a = a / 10;
            b = b / 10;
        }

        System.out.println(Math.max(revA, revB));
    }
}
