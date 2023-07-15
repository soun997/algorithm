import java.util.*;

class Solution {
    
    Queue<Long> q1 = new ArrayDeque<>();
    Queue<Long> q2 = new ArrayDeque<>();
    long sumQ1;
    long sumQ2;
    int limit;
    
    
    public int solution(int[] queue1, int[] queue2) {
        
        init(queue1, queue2);
        
        if ((sumQ1 + sumQ2) % 2 == 1) {
            return -1;
        }
        
        return makeEqual();
    }
    
    void init(int[] queue1, int[] queue2) {
        
        for (int i = 0; i < queue1.length; i++) {
            q1.offer((long)queue1[i]);
            sumQ1 += queue1[i];
        }
        
        for (int i = 0; i < queue2.length ; i++) {
            q2.offer((long)queue2[i]);
            sumQ2 += queue2[i];
        }
    }
    
    int makeEqual(){
        
        int count = 0;
        
        while(!q1.isEmpty() && !q2.isEmpty()) {
            
            if (count > 300000) {
                break;
            }
            
            if (sumQ1 == sumQ2) {
                return count; 
            }

            if (sumQ1 < sumQ2) {
                long num = q2.poll();
                sumQ2 -= num;
                q1.offer(num);
                sumQ1 += num;
                count++;
                continue;
            }
            if (sumQ1 > sumQ2) {
                long num = q1.poll();
                sumQ1 -= num;
                q2.offer(num);
                sumQ2 += num;
                count++;
            }
        }
        
        return -1;
    }
}