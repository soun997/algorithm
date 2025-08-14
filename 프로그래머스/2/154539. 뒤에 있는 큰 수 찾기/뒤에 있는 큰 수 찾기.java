import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        
        Stack<Integer> stack = new Stack<>();   // 뒷큰수를 결정하지 못한 숫자의 index

        for (int i = 0; i < numbers.length; i++) {
            // 현재 수가 스택에 있는 index에 해당하는 수보다 크면
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                // index에 해당하는 수의 뒷큰수는 현재 수
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);
        }
        
        return answer;
    }
}