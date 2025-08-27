import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int idx = 0;
        while (n > 1) {
            int k = s / n;
            if (k == 0) {
                return new int[] { -1 };
            }
            answer[idx++] = k;
            s -= k;
            n -= 1;
        }
        answer[idx] = s;
        return answer;
    }
}