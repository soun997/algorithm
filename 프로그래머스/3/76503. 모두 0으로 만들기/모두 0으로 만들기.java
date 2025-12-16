import java.util.*;

class Solution {
    public long solution(int[] a, int[][] edges) {
        int n = a.length;

        long sum = 0;
        for (int val : a) {
            sum += val;
        }
        if (sum != 0) {
            return -1;
        }

        List<Integer>[] graph = new ArrayList[n];
        int[] degree = new int[n];
        long[] weights = new long[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            weights[i] = a[i];
        }
        
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        
        long answer = 0;
        boolean[] visited = new boolean[n];
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = true;

            for (int next : graph[cur]) {
                if (!visited[next]) {
                    answer += Math.abs(weights[cur]);
                    weights[next] += weights[cur];
                    weights[cur] = 0;
                    
                    degree[next]--;
                    
                    if (degree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        
        return answer;
    }
}
