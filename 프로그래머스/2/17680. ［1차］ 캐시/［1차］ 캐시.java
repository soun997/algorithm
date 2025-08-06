import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        // cacheSize가 0인 경우 모든 요청이 cache miss
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        int execTime = 0;
        
        // LinkedHashMap with access-order = true for LRU behavior
        LinkedHashMap<String, Boolean> cache = new LinkedHashMap<String, Boolean>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Boolean> eldest) {
                return size() > cacheSize;
            }
        };
        
        for (String city : cities) {
            String upperCity = city.toUpperCase();
            
            if (cache.containsKey(upperCity)) {
                // Cache hit: get을 호출하여 access order 업데이트
                cache.get(upperCity);
                execTime += 1;
            } else {
                // Cache miss: 새로운 항목 추가
                cache.put(upperCity, true);
                execTime += 5;
            }
        }
        
        return execTime;
    }
}