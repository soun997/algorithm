import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        if (s.charAt(0) == ')'){
            return false;
        }
        
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cnt++;
                continue;
            }
            if (cnt > 0) {
                cnt--;
                continue;
            }
            return false;
        }

        return cnt == 0;
    }
}