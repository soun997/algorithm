import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String converted = Integer.toString(n, k);
        
        String s = "";
        for (int i = 0; i < converted.length(); i++) {
            char c = converted.charAt(i);
            if (c == '0') {
                if (!s.isBlank() && isPrime(Long.parseLong(s))) {
                    answer++;
                }
                s = "";
                continue;
            }
            s += c;
        }
        if (!s.isBlank() && isPrime(Long.parseLong(s))) {
            answer++;
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