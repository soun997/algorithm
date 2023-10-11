import java.util.*;

class Solution {

    Map<Integer, Integer> mapper = Map.of(0, 1,
                                         1, 2,
                                         2, 4);
    
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        
        while (n > 0){
            answer.append(mapper.get((n - 1) % 3));
            n = (n - 1) / 3;
        }
        
        return answer.reverse().toString();
    }
}