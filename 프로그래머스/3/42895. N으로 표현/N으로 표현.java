import java.util.*;

class Solution {
    
    public int solution(int N, int number) {
        Set<Integer>[] dp = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            dp[i] = new HashSet<>();
        }
        for (int k = 1; k <= 8; k++) { // N은 최대 8개까지 사용가능
            // 조합 생성
            for (int i = 1; i < k; i++) {
                for (int a : dp[i]) {   // N을 i개 사용했을 때
                    for (int b : dp[k - i]) {   // N을 k - i개 사용했을 때
                        dp[k].add(a + b);
                        dp[k].add(a - b);
                        dp[k].add(a * b);
                        if (b != 0) {
                            dp[k].add(a / b);
                        }
                    }
                }
            }
            // N을 k번 이어붙인 값
            int appended = Integer.parseInt(String.valueOf(N).repeat(k));
            dp[k].add(appended);
            if (dp[k].contains(number)) {
                return k;
            }
        }
        return -1;
    }
}
