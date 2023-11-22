import java.util.*;

class Solution {
    
    private String begin, target;
    private Map<String, Boolean> visited = new HashMap<>();
    private int min;
    
    public int solution(String begin, String target, String[] words) {
        
        this.begin = begin;
        this.target = target;
        this.min = Integer.MAX_VALUE;
        
        for (String word : words) {
            visited.put(word, false);
        }
        
        dfs(begin, 0);
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    private void dfs(String word, int cnt) {
        
        if (word.equals(target)) {
            min = Math.min(min, cnt);
            return;
        }
        
        for (String key : visited.keySet()) {
            if (visited.get(key)) {
                continue;
            }
            if (isConvertable(word, key)) {
                visited.put(key, true);
                dfs(key, cnt + 1);
                visited.put(key, false);
            }
        }
    }
    
    private boolean isConvertable(String origin, String target) {
        
        int cnt = 0;
        for (int i = 0; i < origin.length(); i++) {
            if (origin.charAt(i) != target.charAt(i)) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}