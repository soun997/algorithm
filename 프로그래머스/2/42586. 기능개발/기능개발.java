import java.util.*;

/**
- 각 기능은 진도가 100%일 경우 서비스 반영
- 기능개발속도는 모두 다름 -> 뒤에 있는 기능이 앞의 기능보다 먼저 개발 가능
- 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포
- progresses: 작업의 진도
- speeds: 작업의 개발 속도
*/
class Solution {
    
    Queue<Integer> q = new ArrayDeque<>();
    
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        // 전처리
        for (int i = 0; i < progresses.length; i++) {
            progresses[i] = 100 - progresses[i];
            q.offer((int)Math.ceil((double)progresses[i] / speeds[i]));
        }
        
        if (q.isEmpty()) {
            return List.of(0);
        }
        
        int prev = q.poll();
        int chunk = 1;
        while(!q.isEmpty()) {
            
            int cur = q.poll();
            
            if (prev < cur) {
                answer.add(chunk);
                prev = cur;
                chunk = 1;
                continue;
            }
            chunk++;
        }
        if (chunk >= 1) {
            answer.add(chunk);
        }
        
        return answer;
    }
}