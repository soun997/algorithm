import java.util.*;

class Solution {
    
    private PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        
        for (int val : scoville) {
            pq.offer(val);
        }
        
        int answer = 0;
        
        while (!pq.isEmpty()) {
            int first = pq.poll();
            if (first >= K) {
                break;
            }
            if (pq.isEmpty()) {
                return -1;
            }
            int second = pq.poll();
            
            int newScoville = first + second * 2;
            
            pq.offer(newScoville);
            answer++;
        }
        
        return answer;
    }
}