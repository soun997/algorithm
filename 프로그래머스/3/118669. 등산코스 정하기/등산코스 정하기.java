import java.util.*;

class Solution {
    int n;
    List<Node>[] graph;
    Set<Integer> gateSet = new HashSet<>();
    Set<Integer> summitSet = new HashSet<>();
    int[] dist;
    int[] answer;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        this.graph = new ArrayList[n + 1];
        this.dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        this.answer = new int[] { -1, Integer.MAX_VALUE };
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            graph[path[0]].add(new Node(path[1], path[2]));
            graph[path[1]].add(new Node(path[0], path[2]));
        }
        for (int i = 0; i < gates.length; i++) {
            gateSet.add(gates[i]);
        }
        for (int i = 0; i < summits.length; i++) {
            summitSet.add(summits[i]);
        }
        // System.out.println(summitSet);
        // System.out.println(Arrays.toString(graph));
        for (int i = 0; i < gates.length; i++) {
            // System.out.println(i);
            dijkstra(gates[i]);
        }
        return answer;
    }
    
    private void dijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        q.offer(new Node(start, 0));
        dist[start] = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            // System.out.println("cur: " + cur.to + " dist: " + Arrays.toString(dist));
            if (dist[cur.to] < cur.weight) {
                continue;
            }
            for (int i = 0; i < graph[cur.to].size(); i++) {
                Node next = graph[cur.to].get(i);
                if (!gateSet.contains(next.to) && 
                    dist[next.to] > Math.max(cur.weight, next.weight)) {
                    dist[next.to] = Math.max(cur.weight, next.weight);
                    if (summitSet.contains(next.to)) {
                        if (answer[1] > dist[next.to]) {
                            answer[0] = next.to;
                            answer[1] = dist[next.to];            
                        } else if (answer[1] == dist[next.to]) {
                            if (answer[0] > next.to) {
                                answer[0] = next.to;
                            }
                        }
                    } else {
                        q.offer(new Node(next.to, dist[next.to]));
                    }
                }
            }
        }
        // System.out.println("start: " + start + " dist: " + Arrays.toString(dist));
    }
    
    static class Node {
        int to;
        int weight;
        
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public String toString() {
            return "[to: " + to + " weight: " + weight + "]";
        }
    }
}