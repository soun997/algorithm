import java.util.*;

class Solution {
    public String solution(String s) {
        int min = Arrays.stream(s.split(" ")).map(Integer::parseInt).min(Integer::compareTo).orElseThrow();
        int max = Arrays.stream(s.split(" ")).map(Integer::parseInt).max(Integer::compareTo).orElseThrow();
        
        return String.format("%d %d", min, max);
    }
}