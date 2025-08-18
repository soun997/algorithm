import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int scov : scoville) {
            pq.offer(scov);
        }
        int answer = 0;
        while (pq.size() >= 2 && pq.peek() < K) {            
            int first = pq.poll();
            int second = pq.poll();
            
            int mixed = first + second * 2;
            pq.offer(mixed); 
            answer++;
        }
        if (pq.peek() >= K) {
            return answer;
        }
        return -1;
    }
}