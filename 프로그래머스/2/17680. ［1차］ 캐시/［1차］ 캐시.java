import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int execTime = 0;
        List<String> cache = new LinkedList<>();
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();
            if (cache.contains(city)) {
                cache.remove(city);
                cache.add(city);
                execTime += 1;
                continue;
            }
            cache.add(city);
            execTime += 5;
            if (cache.size() > cacheSize) {
                cache.remove(0);
            }
        }
        return execTime;
    }
}