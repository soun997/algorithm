import java.util.*;

class Solution {
    boolean solution(String input) {
        int count = 0;
        for(char c : input.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            } else {
                return false; // 괄호가 아닌 입력
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}