import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int score = 0;
        int a = 0;
        int b = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        while(a < A.length && b < B.length) {
            if (A[a] < B[b]) {
                score++;
                a++;
                b++;
                continue;
            }
            b++;
        }
        return score;
    }
}