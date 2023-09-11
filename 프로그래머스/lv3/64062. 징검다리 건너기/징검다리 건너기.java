import java.util.*;

class Solution {
    
    public int solution(int[] stones, int k) {
        
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));

        for (int i = 0; i < k - 1; i++) {
            q.offer(new int[]{-1 * stones[i], i});
        }
        int min = Integer.MAX_VALUE;
        for (int i = k - 1; i < stones.length; i++) {
            q.offer(new int[]{-1 * stones[i], i});
            while (!q.isEmpty()) {
                int[] cur = q.peek();
                if (cur[1] >= i - k + 1) {
                    break;
                }
                q.poll();
            }
            min = Math.min(min, -1 * q.peek()[0]);
        }
        return min;
    }
}