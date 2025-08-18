import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int scov : scoville) {
            pq.offer(scov);
        }
        if (pq.peek() >= K) {
            return 0;
        }
        boolean isPossible = false;
        int answer = 0;
        while (pq.size() >= 2) {            
            int first = pq.poll();
            int second = pq.poll();
            
            int mixed = first + second * 2;
            pq.offer(mixed); 
            answer++;
            
            int min = pq.peek();
            if (min >= K) {
                isPossible = true;
                break;
            }
        }
        if (isPossible) {
            return answer;
        }
        return -1;
    }
}