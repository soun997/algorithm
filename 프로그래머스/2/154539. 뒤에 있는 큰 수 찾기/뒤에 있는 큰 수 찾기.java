import java.util.*;

class Solution {
    
    Stack<int[]> stk = new Stack();
    
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            while (!stk.isEmpty()) {
                int[] cur = stk.peek();
                if (cur[0] >= numbers[i]) {
                    break;
                }
                answer[cur[1]] = numbers[i];
                stk.pop();
            }
            stk.push(new int[]{numbers[i], i});
        }
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == 0) {
                answer[i] = -1;
            }
        }
        
        return answer;
    }
}