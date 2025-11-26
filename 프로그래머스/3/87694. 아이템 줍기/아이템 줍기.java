import java.util.*;

class Solution {
    
    final int[] DX = { -1, 0, 1, 0 };
    final int[] DY = { 0, -1, 0, 1 };
    int[][] map;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[101][101];
        for (int[] rect : rectangle) {
            fill(rect[0] * 2, rect[1] * 2, rect[2] * 2, rect[3] * 2);
        }
        for (int[] rect : rectangle) {
            remove(rect[0] * 2, rect[1] * 2, rect[2] * 2, rect[3] * 2);
        }
        // for (int i = 0; i < 21; i++) {
        //     for (int j = 0; j < 21; j++) {
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }
    
    private void fill(int x1, int y1, int x2, int y2) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                map[x][y] = 1;
            }
        }
    }
    
    private void remove(int x1, int y1, int x2, int y2) {
        for (int x = x1 + 1; x <= x2 - 1; x++) {
            for (int y = y1 + 1; y <= y2 - 1; y++) {
                map[x][y] = 0;
            }
        }
    }
    
    private int bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { characterX, characterY, 0 });
        boolean[][] visited = new boolean[101][101];
        visited[characterX][characterY] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == itemX && cur[1] == itemY) {
                return cur[2] / 2;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + DX[d];
                int ny = cur[1] + DY[d];
                if (isOutOfBounds(nx, ny) || 
                    visited[nx][ny] ||
                    map[nx][ny] == 0) {
                    continue;
                }
                q.offer(new int[] { nx, ny, cur[2] + 1 });
                visited[nx][ny] = true;
            }
        }
        return 0;
    }
    
    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x > 100 || y < 0 || y > 100;
    }
}