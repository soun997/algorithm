import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String converted = Integer.toString(n, k);
        
        String[] numbers = converted.split("0");
        for (String number : numbers) {
            if (!number.isEmpty() && isPrime(Long.parseLong(number))) {
                answer++;
            }
        }
        return answer;
    }
    
    private boolean isPrime(long n) {
        if (n == 0 || n == 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}