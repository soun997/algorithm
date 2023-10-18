import java.util.*;

class Solution {
    
    int[][] arr;
    int length;
    int[] answer;
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    
    public int[] solution(int[][] arr) {
        this.arr = arr;
        this.length = arr.length;
        this.answer = new int[2];
        if (isPressed(0, 0, arr[0][0], length)) {
            answer[arr[0][0]]++;
            return answer;
        }
        divide(0, 0, length / 2);
        
        return answer;
    }
    
    int[] wx = {0, 0, 1, 1};
    int[] wy = {0, 1, 0, 1};
    
    public void divide(int x, int y, int n) {
        
        for (int d = 0; d < 4; d++) {
            int nx = x + wx[d] * n;
            int ny = y + wy[d] * n;
            if (isPressed(nx, ny, arr[nx][ny], n)) {
                answer[arr[nx][ny]]++;
                continue;
            }
            divide(x + wx[d] * n, y + wy[d] * n, n / 2);
        }
    }
    
    public boolean isPressed(int x, int y, int origin, int n) {
        if (n == 1) {
            return true;
        }
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[length][length];
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if (arr[cur[0]][cur[1]] != origin) {
                return false;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (isOutOfBounds(nx, ny, x, y, n) || 
                    visited[nx][ny]) {
                    continue;
                }
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
        
        return true;
    }
    
    private boolean isOutOfBounds(int x, int y, 
                                  int startX, int startY, 
                                  int range){
        if (x < startX || x >= startX + range || 
            y < startY || y >= startY + range) {
            return true;
        }
        return false;
    }
}