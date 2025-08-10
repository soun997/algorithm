import java.util.*;

class Solution {
    
    private int[] parent;
    private int[] rank;
    
    public int solution(int n, int[][] computers) {
        makeSet(n);
        for (int i = 0; i < computers.length; i++) {
            for (int j = i + 1; j < computers[i].length; j++) {
                if (computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        Set<Integer> networks = new HashSet<>();
        for (int i = 0; i < parent.length; i++) {
            networks.add(find(i));
        }
        return networks.size();
    }
    
    private void makeSet(int count) {
        parent = new int[count];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        rank = new int[count];
        Arrays.fill(rank, 1);
    }
    
    private int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }
    
    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA == rootB) {
            return;
        }
        
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }
}