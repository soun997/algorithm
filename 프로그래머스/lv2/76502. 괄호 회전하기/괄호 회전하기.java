import java.util.*;

class Solution {
    
    private Map<Character, Character> map = Map.of(
        '(', ')',
        '{', '}',
        '[', ']'
    );
    
    public int solution(String s) {
        Queue<Character> q = makeQueue(s);
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isCorrect(q)) {
                answer++;
            }
            q.offer(q.poll());
        }
        return answer;
    }
    
    public Queue<Character> makeQueue(String s) {
        Queue<Character> q = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            q.offer(c);
        }
        return q;
    }
    
    public boolean isCorrect(Queue<Character> q) {
        Stack<Character> stk = new Stack<>();
        for (Character bracket : q) {
            if (stk.isEmpty()) {
                stk.push(bracket);
                continue;
            }
            if (map.get(stk.peek()) == bracket) {
                stk.pop();
                continue;
            }
            stk.push(bracket);
        }
        
        return stk.isEmpty();
    }
}