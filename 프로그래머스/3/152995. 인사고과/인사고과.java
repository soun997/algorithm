import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] target = scores[0];
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]) * -1;
        });

        int maxCoworker = 0;
        List<int[]> incentive = new ArrayList<>();
        for (int[] s : scores) {
            if (s[1] < maxCoworker) {
                if (Arrays.equals(s, target)) { // 완호가 탈락하는 경우
                    return -1;
                }
            } else {
                incentive.add(s);
                maxCoworker = s[1];
            }
        }
        int rank = 1;
        for (int[] s : incentive) {
            if (s[0] + s[1] > target[0] + target[1]) {
                rank++;
            }
        }
        return rank;
    }
}
