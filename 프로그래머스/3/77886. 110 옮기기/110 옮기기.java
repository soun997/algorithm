import java.util.*;

class Solution {
    public List<String> solution(String[] inputs) {
        List<String> answer = new ArrayList<>();
        for (String s : inputs) {
            Stack<Character> stk = new Stack<>();
            int count = 0;
            for (int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                stk.push(c);
                while (stk.size () >= 3) {
                    String sub = "";
                    for (int j = 0; j < 3; j++) {
                        sub += stk.pop();
                    }
                    // System.out.println("sub: " + sub);
                    if (sub.equals("011")) {
                        count++;
                    } else {
                        for (int j = 0; j < 3; j++) {
                            stk.push(sub.charAt(2 - j));
                        }
                        break;
                    }
                }
            }
            // System.out.println(stk);
            // System.out.println("count: " + count);
            StringBuilder remainBuilder = new StringBuilder();
            for (char c : stk) {
                remainBuilder.append(c);
            }
            String remain = remainBuilder.toString();
            // System.out.println("remain: " + remain);
            boolean flag = false;
            int lastZeroIndex = -1;
            for (int i = 0; i < remain.length(); i++) {
                if (remain.charAt(i) == '0') {
                    lastZeroIndex = i;
                }
            }
            // System.out.println("lastZeroIndex: " + lastZeroIndex);
            StringBuilder result = new StringBuilder();
            result.append(remain.substring(0, lastZeroIndex + 1));
            for (int i = 0; i < count; i++) {
                result.append("110");
            }
            result.append(remain.substring(lastZeroIndex + 1, remain.length()));
            // System.out.println("result: " + result);
            answer.add(result.toString());
        }
        return answer;
    }
}