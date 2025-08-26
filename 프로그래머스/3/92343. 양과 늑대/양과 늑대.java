import java.util.*;

class Solution {
    private int[] info;
    private List<Integer>[] graph;
    private int max = 0;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        graph = new ArrayList[info.length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        circuit(0, 0, 0, new ArrayList<>());
        return max;
    }
    
    private void circuit(int cur, int sheep, int wolf, List<Integer> nextNodes) {
        if (info[cur] == 0) {
            sheep++;
        } else {
            wolf++;
        }
        if (sheep <= wolf) {
            return;
        }
        if (nextNodes.size() > 0) {
            nextNodes.remove(Integer.valueOf(cur));
        }
        max = Math.max(max, sheep);
        for (int next : graph[cur]) {
            nextNodes.add(next);
        }
        for (int next : nextNodes) {
            circuit(next, sheep, wolf, new ArrayList<>(nextNodes));
        };
    }
}