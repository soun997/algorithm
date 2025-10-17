import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for (int i = 0; i < timetable.length; i++) {
            pq.offer(parseTime(timetable[i]));
        }
        int time = 540;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pq.isEmpty()) {
                    return toString(time);
                }
                int cur = pq.peek();
                if (cur > time) {
                    break;
                }
                if (i == n - 1 && j == m - 1) {
                    return toString(cur - 1);
                }
                pq.poll();
            }
            time += t;
        }
        return toString(time - t);
    }
    
    int parseTime(String str) {
        String[] chunks = str.split(":");
        return Integer.parseInt(chunks[0]) * 60 + Integer.parseInt(chunks[1]);
    }
    
    String toString(int time) {
        String hour = String.format("%02d",time / 60);
        String minute = String.format("%02d", time % 60);
        return hour + ":" + minute;
    }
}