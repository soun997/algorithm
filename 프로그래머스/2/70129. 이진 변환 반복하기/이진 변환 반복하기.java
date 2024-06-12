import java.util.*;

class Solution {
    
    private int converted = 0;
    private int removed = 0;
    
    public int[] solution(String str) {
        while(!str.equals("1")) {
            String result = stkToString(stringToStk(str));
            int length = result.length();
            str = Integer.toBinaryString(length);
            converted++;
        }
        return new int[]{ converted, removed };
    }
    
    private Stack<Character> stringToStk(String str) {
        Stack<Character> stk = new Stack();
        for (char c : str.toCharArray()) {
            if (c != '0') {
                stk.push(c);
                continue;
            }
            removed++;
        }
        return stk;
    }
    
    private String stkToString(Stack<Character> stk) {
        StringBuilder sb = new StringBuilder();
        while(!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        return sb.toString();
    }
}