import java.util.*;

class Solution {
    public int solution(int[] a) {
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        left[0] = a[0];
        right[a.length - 1] = a[a.length - 1];
        for (int i = 1; i < a.length; i++) {
            left[i] = Math.min(left[i - 1], a[i]);
            right[a.length - 1 - i] = Math.min(right[a.length - 1 - i + 1], a[a.length - 1 - i]);
        }
        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            int count = 0;
            if (i - 1 >= 0 && a[i] > left[i - 1]) {  // 왼쪽 살피기
                count++;
            }
            if (i + 1 < a.length && a[i] > right[i + 1]) { // 오른쪽 살피기
                count++;
            }
            if (count < 2) {
                answer++;
            }
        }
        return answer;
    }
}