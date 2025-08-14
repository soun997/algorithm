import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= m * t; i++) {
            sb.append(Integer.toString(i, n).toUpperCase());
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            answer.append(sb.toString().charAt(p - 1));
            p = p + m;
        }
        return answer.toString();
    }
    private int findFitValue(int n, int maxOrder) {
        int fitValue = 0;
        int min = 0;
        int max = n - 1;
        int mul = 1;
        while(fitValue < maxOrder) {
            fitValue += ((max - min) + 1) * mul;
            mul++;
            max = max + max * n;
            min = min + (min == 0 ? 1 : min) * n;
        }
        return fitValue;
    }
}