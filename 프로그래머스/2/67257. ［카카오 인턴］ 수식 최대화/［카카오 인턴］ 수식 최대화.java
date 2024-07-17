import java.util.*;

class Solution {
    
    String[] literal = { "+", "-", "*" };
    
    String[] priority = new String[3];
    boolean[] visited = new boolean[3];

    String[] strNumbers;
    String[] strOperations;
    ArrayDeque<Long> numbers;
    ArrayDeque<String> operators;
    
    long answer = 0;
    
    public long solution(String expression) {
        
        strNumbers = expression.split("\\D");
        strOperations = expression.replaceAll("\\d+", "").split("");
        
        perm(0);

        return answer;
    }
    
    public void perm(int cnt) {
        
        if (cnt == 3) {
            numbers = new ArrayDeque<>();
            for (int i = 0; i < strNumbers.length; i++) {
                numbers.offer(Long.parseLong(strNumbers[i]));
            }
            operators = new ArrayDeque<>();
            for (int i = 0; i < strOperations.length; i++) {
                operators.offer(strOperations[i]);
            }
            answer = Math.max(answer, getResult());
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            priority[cnt] = literal[i];
            perm(cnt + 1);
            visited[i] = false;
        }
    }
    
    public long getResult() {
        for (int i = 0; i < priority.length; i++) {
            ArrayDeque<Long> newNumbers = new ArrayDeque();
            newNumbers.offer(numbers.poll());
            ArrayDeque<String> newOperators = new ArrayDeque<>();
            while(!operators.isEmpty()) {
                String op = operators.poll();
                if (op.equals(priority[i])) {
                    long arg1 = newNumbers.pollLast();
                    long arg2 = numbers.poll();
                    newNumbers.offer(calculate(arg1, arg2, op));
                    continue;
                }
                newNumbers.offer(numbers.poll());
                newOperators.offer(op);
            }
            numbers = newNumbers;
            operators = newOperators;
        }
        return Math.abs(numbers.poll());
    }
    
    public long calculate(long a, long b, String op) {
        
        return switch(op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> -1;
        };
    }
}