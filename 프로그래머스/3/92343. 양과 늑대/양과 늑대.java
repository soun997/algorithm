import java.util.*;

class Solution {
    private int[] info;
    private List<Integer>[] graph;
    private int max = 0;
    private Set<Long> memo = new HashSet<>();
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        graph = new ArrayList[info.length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        circuit(0, 0, 0, 1L);
        return max;
    }
    
    private void circuit(int cur, int sheep, int wolf, long nextMask) {
        if (info[cur] == 0) {
            sheep++;
        } else {
            wolf++;
        }
        if (sheep <= wolf) {
            return;
        }
        max = Math.max(max, sheep);
        
        // 현재 노드를 방문 목록에서 제거하고 자식 노드들 추가
        nextMask &= ~(1L << cur);
        for (int child : graph[cur]) {
            nextMask |= (1L << child);
        }
        
        long state = nextMask << 10 | sheep << 5 | wolf;
        // 같은 상태라면 탐색할 필요 없음
        if (memo.contains(state)) {
            return;
        }
        memo.add(state);
        
        for (int i = 0; i < info.length; i++) {
            // 방문 가능한 노드라면
            if ((nextMask & (1L << i)) != 0) {
                circuit(i, sheep, wolf, nextMask);
            }
        }
    }
}