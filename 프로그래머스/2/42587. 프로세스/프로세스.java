import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Integer[] sorted = copy(priorities);
        Arrays.sort(sorted, Collections.reverseOrder());
        Queue<Integer> sortedQ = new ArrayDeque<>();
        for (int n : sorted) {
            sortedQ.offer(n);
        }
        Queue<Integer> prioritiesQ = new ArrayDeque<>();
        Queue<Boolean> targetQ = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            prioritiesQ.offer(priorities[i]);
            if (i == location) {
                targetQ.offer(true);
                continue;
            }
            targetQ.offer(false);
        }
        int order = 0;
        while(!sortedQ.isEmpty()) {
            int cur = prioritiesQ.poll();
            boolean isTarget = targetQ.poll();
            
            if (cur < sortedQ.peek()) {
                prioritiesQ.offer(cur);
                targetQ.offer(isTarget);
                continue;
            }
            sortedQ.poll();
            order++;
            if (isTarget) {
                return order;
            }
        }
        return order;
    }
    
    public Integer[] copy(int[] origin) {
        Integer[] copied = new Integer[origin.length];
        for (int i = 0; i < origin.length; i++) {
            copied[i] = origin[i];
        }
        return copied;
    }
}