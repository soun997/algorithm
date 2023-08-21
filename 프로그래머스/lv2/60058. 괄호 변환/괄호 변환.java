import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = makeRightString(p);
        return answer;
    }
    
    public String makeRightString(String w) {
        
        // 1. 입력이 빈 문자열인 경우, 빈 문자열 반환
        if (w.isEmpty()) {
            return w;
        }
        
        String[] divided = divide(w);
        
        if (isRightString(divided[0])) {
            return new StringBuilder(divided[0])
                .append(makeRightString(divided[1])).toString();
        }
        return tryToMakeRightString(divided[0], divided[1]);
    }
    
    public String[] divide(String w) {
        
        int idx = 0;
        int left = 0;
        int right = 0;
        for (char c : w.toCharArray()) {
            if (c == '(') {
                left++;
            }
            if (c == ')') {
                right++;
            }
            idx++;
            if (left == right) {
                break;
            }
        }
        String u = w.substring(0, idx);
        String v = w.substring(idx, w.length());
        
        return new String[]{u, v};
    }
    
    public boolean isRightString(String u) {
        Stack<Character> stk = new Stack<>();
        for (char bracket : u.toCharArray()) {
            if (stk.isEmpty()) {
                stk.push(bracket);
                continue;
            }
            if (stk.peek() == '(' && bracket == ')') {
                stk.pop();
                continue;
            }
            stk.push(bracket); 
        }
        if (stk.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public String tryToMakeRightString(String u, String v) {
        StringBuilder result = new StringBuilder();
        result.append("(");
        result.append(makeRightString(v));
        result.append(")");
        
        String[] subResult = u.substring(1, u.length() - 1).split("");
        for (String s : subResult) {
            if (s.equals("(")){
                result.append(")");
            }
            if (s.equals(")")){
                result.append("(");
            }
        }

        return result.toString();
    }
}