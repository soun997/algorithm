import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();
            BigInteger[] dp = new BigInteger[n + 3];
            dp[0] = new BigInteger("1");
            dp[1] = new BigInteger("1");
            dp[2] = new BigInteger("3");

            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1].add(dp[i - 2].multiply(new BigInteger("2")));
            }

            System.out.println(dp[n].toString());
        }
    }
}