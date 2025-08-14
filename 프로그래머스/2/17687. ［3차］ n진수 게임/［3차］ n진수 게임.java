import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= t * m; i++) {
            sb.append(Integer.toString(i, n).toUpperCase());
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            answer.append(sb.toString().charAt(p - 1));
            p = p + m;
        }
        return answer.toString();
    }
}