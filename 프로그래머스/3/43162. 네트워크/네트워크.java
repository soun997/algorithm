import java.util.*;

class Solution {
    
    List<Integer>[] graph;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        // init graph
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    graph[i].add(j);
                }
            }
        }
        // bfs
        int answer = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            bfs(i);           
            answer++;
        }
        
        return answer;
    }
    
    private void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            visited[cur] = true;
            //System.out.println(cur);
            for (int next : graph[cur]) {
                if (visited[next]) {
                    continue;
                }
                q.offer(next);             
            }
        }
    }
}