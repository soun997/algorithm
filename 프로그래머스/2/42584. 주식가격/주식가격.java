import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stk = new Stack<>();
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            // 스택이 비어있거나 주가 떨어지지 않음
            if (stk.isEmpty() || prices[stk.peek()] <= prices[i]) {
                stk.push(i);
                continue;
            }
            // 주가 떨어짐
            while (!stk.isEmpty() && prices[stk.peek()] > prices[i]) {
                int idx = stk.pop();
                answer[idx] = i - idx;   
            }                
            stk.push(i);
        }
        // 끝까지 주가 떨어지지 않은 주식들
        while(!stk.isEmpty()) {
            int idx = stk.pop();
            answer[idx] = (prices.length - 1) - idx;
        }
        return answer;
    }
}