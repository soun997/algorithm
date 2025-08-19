import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stk = new Stack<>();
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            if (stk.isEmpty()) {
                stk.push(i);
                continue;
            }
            if (prices[stk.peek()] <= prices[i]) {
                stk.push(i);
            } else {
                while (!stk.isEmpty() && prices[stk.peek()] > prices[i]) {
                    int idx = stk.pop();
                    answer[idx] = i - idx;   
                }                
                stk.push(i);
            }
        }
        for (int idx: stk) {
            answer[idx] = (prices.length - 1) - idx;
        }
        return answer;
    }
}