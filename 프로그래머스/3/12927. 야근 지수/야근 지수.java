import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(x, y) * -1);
        for (int work: works) {
            pq.offer(work);
        }
        while (!pq.isEmpty() && n > 0) {
            int remain = Math.max(0, pq.poll() - 1);
            n--;
            pq.offer(remain);   
        }
        long answer = 0;
        while(!pq.isEmpty()) {
            int fatigue = pq.poll();
            answer += fatigue * fatigue;
        }
        return answer;
    }
}