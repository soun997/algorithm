import java.util.*;

class Solution {
    int len;
    int[][] info;
    int n, m;
    boolean[][][] visited = new boolean[40][121][121];
    int minA = Integer.MAX_VALUE;
    public int solution(int[][] info, int n, int m) {
        len = info.length;
        this.info = info;
        this.n = n;
        this.m = m;
        dfs(0, 0, 0);
        if (minA == Integer.MAX_VALUE) {
            return -1;
        }
        return minA;
    }
    
    private void dfs(int depth, int sumA, int sumB) {
        if (depth == len) {
            minA = Math.min(minA, sumA);
            return;
        }
        if (visited[depth][sumA][sumB]) {
            return;
        }
        visited[depth][sumA][sumB] = true;
        if (sumA + info[depth][0] < n) {
            dfs(depth + 1, sumA + info[depth][0], sumB);   
        }
        if (sumB + info[depth][1] < m) {
            dfs(depth + 1, sumA, sumB + info[depth][1]);   
        }
    }
}