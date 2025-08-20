import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int n = 1;
        Stack<Integer> stk = new Stack<>();
        for (int o : order) {
            while (n <= o) {
                stk.push(n++);
            }
            if (stk.isEmpty() || stk.peek() != o) {
                break;
            }
            stk.pop();
            answer++;
        }
        return answer;
    }
}