import java.util.*;

class Solution {
    public int solution(int[] a) {
        int[] dp1 = new int[a.length];
        int[] dp2 = new int[a.length];
        dp1[0] = a[0];
        dp2[a.length - 1] = a[a.length - 1];
        for (int i = 1; i < a.length; i++) {
            dp1[i] = Math.min(dp1[i - 1], a[i]);
            dp2[a.length - 1 - i] = Math.min(dp2[a.length - 1 - i + 1], a[a.length - 1 - i]);
        }
        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            int count = 0;
            if (i - 1 >= 0 && a[i] > dp1[i - 1]) {  // 왼쪽 살피기
                count++;
            }
            if (i + 1 < a.length && a[i] > dp2[i + 1]) { // 오른쪽 살피기
                count++;
            }
            if (count < 2) {
                answer++;
            }
        }
        return answer;
    }
}