import java.util.*;

class Solution {
    boolean solution(String input) {
        Stack<Character> stk = new Stack<>();
        for(char c : input.toCharArray()) {
            if (c == '(') {
                stk.push(c);
            } else if (c == ')') {
                if (stk.isEmpty()) {
                    return false;
                }
                stk.pop();
            } else {
                return false; // 괄호가 아닌 입력
            }
        }
        return stk.isEmpty();
    }
}