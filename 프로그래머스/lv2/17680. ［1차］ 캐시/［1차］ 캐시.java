import java.util.*;

class Solution {
    
    private static final int HIT = 1;
    private static final int MISS = 5;
    
    private Queue<String> q;
    
    public int solution(int cacheSize, String[] cities) {
        
        q = new ArrayDeque<>();
        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toUpperCase();
        }
        
        int answer = getExecuteTime(cacheSize, cities);
        return answer;
    }
    
    public int getExecuteTime(int cacheSize, String[] cities) {
        
        int executeTime = 0;
        
        for (String city : cities) {
            if (q.contains(city)) {
                q.remove(city);
                q.offer(city);
                executeTime += HIT;
                continue;
            }
            q.offer(city);
            executeTime += MISS;
            if (q.size() > cacheSize) {
                q.poll();
            }
        }
        
        return executeTime;
    }
}