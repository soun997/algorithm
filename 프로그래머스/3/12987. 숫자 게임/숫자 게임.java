import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int score = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int idx = B.length - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] < B[idx]) {
                score++;
                idx--;
                continue;
            }
        }
        return score;
    }
}