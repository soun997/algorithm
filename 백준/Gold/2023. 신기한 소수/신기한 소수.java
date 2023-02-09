import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int[] primes = {1, 2, 3, 5, 7, 9};   // 덧붙이기 위한 숫자들
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        nPir(0, nums);
        System.out.println(result);
    }

    static void nPir(int cnt, int[] nums){

        if (cnt == nums.length){
            int n = stringToInt(nums);
            //System.out.println(n);
            if (isPrime(n)){
                result.append(n).append("\n");
            }
            return;
        }

        for (int i = 0; i < primes.length; i++) {
            nums[cnt] = primes[i];
            int n = stringToInt(nums);
            //System.out.println(n);
            if (isPrime(n)){
                nPir(cnt + 1, nums);
            }
            nums[cnt] = 0;
        }
    }

    static int stringToInt(int[] numArr){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numArr.length; i++) {
            if (numArr[i] != 0) sb.append(numArr[i]);
        }
        return Integer.parseInt(sb.toString());
    }

    // 에라토스테네스의 체
    static boolean isPrime(int n){
        if (n == 0 || n == 1) return false;
        for (int i = 2; i <= (int)Math.sqrt(n); i++){
            if (n % i == 0) return false;
        }
        return true;
    }
}
