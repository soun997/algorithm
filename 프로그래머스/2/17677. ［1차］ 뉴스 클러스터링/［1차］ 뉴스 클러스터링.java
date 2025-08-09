import java.util.*;

class Solution {
    
    private final int CONST = 65536;
    
    public int solution(String str1, String str2) {
        Map<String, Integer> a = parse(str1);
        Map<String, Integer> b = parse(str2);
        
        int intersection = getIntersectionSize(a, b);
        int union = getUnionSize(a, b);
        
        if (intersection == 0 && union == 0) {
            return 1 * CONST;
        }
        return (int)((float)intersection / union * CONST);
    }
    
    private Map<String, Integer> parse(String str) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String sub = str.substring(i, i + 2).toUpperCase();
            if (sub.matches("^[A-Za-z]*$")) {   // 영어로만 이루어져 있다면
                map.putIfAbsent(sub, 0);
                map.computeIfPresent(sub, (k, v) -> v + 1);
            }
        }
        return map;
    }
    
    private int getIntersectionSize(Map<String, Integer> a, Map<String, Integer> b) {
        int size = 0;
        for(String sub : a.keySet()) {
            if (b.containsKey(sub)) {
                size += Math.min(a.get(sub), b.get(sub));
            }
        }
        return size;
    }
    
    private int getUnionSize(Map<String, Integer> a, Map<String, Integer> b) {
        int size = 0;
        for (String sub : a.keySet()) {
            if (b.containsKey(sub)) {
                size += Math.max(a.get(sub), b.get(sub));
                b.remove(sub);
                continue;
            }
            size += a.get(sub);
        }
        for (String sub : b.keySet()) {
            size += b.get(sub);
        }
        return size;
    }
}