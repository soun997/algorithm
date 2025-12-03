import java.util.*;

class Solution {
    
    final int EMPTY = 0;
    final int WALL = 1;
    final int HORIZONTAL = 0;
    final int VERTICAL = 1;
    final int[] DR = new int[] {-1, 0, 1, 0};
    final int[] DC = new int[] {0, -1, 0, 1};
    final int[][] UP = new int[][] { 
        { 0, 0 },
        { 0, 1 },
        { -1, 0 },
        { -1, 1 },
    };
    final int[][] DOWN = new int[][] { 
        { 0, 0 },
        { 0, 1 },
        { 1, 0 },
        { 1, 1 },
    };
    final int[][] LEFT = new int[][] { 
        { 0, 0 },
        { 1, 0 },
        { 0, -1 },
        { 1, -1 },
    };
    final int[][] RIGHT = new int[][] { 
        { 0, 0 },
        { 1, 0 },
        { 0, 1 },
        { 1, 1 },
    };
    
    int n;
    int[][] board;
    boolean[][][] visited;
    
    public int solution(int[][] board) {
        this.n = board.length;
        this.board = board;
        this.visited = new boolean[n][n][2];
        return bfs();
    }
    
    private int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        // (r, c, horizontal: 0/vertical : 1, time)
        q.offer(new int[] { 0, 0, HORIZONTAL, 0 });
        visited[0][0][HORIZONTAL] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            // System.out.println(Arrays.toString(cur));
            if (cur[2] == HORIZONTAL) {
                if (cur[0] == n - 1 && cur[1] + 1 == n - 1) {
                    return cur[3];
                }
            } else {
                if (cur[0] + 1 == n - 1 && cur[1] == n - 1) {
                    return cur[3];
                }
            }
            // 1. 로봇 이동시켜보기
            for (int d = 0; d < 4; d++) {
                int nr1 = cur[0] + DR[d];
                int nc1 = cur[1] + DC[d];
                int nr2 = nr1;
                int nc2 = nc1;
                if (cur[2] == HORIZONTAL) {
                    nc2 += 1;
                } else {
                    nr2 += 1;
                }
                // 범위 벗어나는 경우
                if (isOutOfBounds(nr1, nc1) || isOutOfBounds(nr2, nc2)) {
                    continue;
                }
                // 벽에 막히는 경우
                if (board[nr1][nc1] == WALL || board[nr2][nc2] == WALL) {
                    continue;
                }
                // 이미 방문한 상태
                if (visited[nr1][nc1][cur[2]]) {
                    continue;
                }
                // 가능한 상태
                q.offer(new int[] { nr1, nc1, cur[2], cur[3] + 1});
                visited[nr1][nc1][cur[2]] = true;
            }
            // 2. 로봇 회전시켜보기
            if (cur[2] == HORIZONTAL) { // 가로 상태
                // UP 방향 회전
                if (canRotateUp(cur[0], cur[1])) {
                    if (!visited[cur[0] - 1][cur[1]][VERTICAL]) {
                        q.offer(
                            new int[]{cur[0] - 1, cur[1], VERTICAL, cur[3] + 1});
                        visited[cur[0] - 1][cur[1]][VERTICAL] = true;
                    }
                    if (!visited[cur[0] - 1][cur[1] + 1][VERTICAL]) {
                        q.offer(
                            new int[]{cur[0] - 1, cur[1] + 1, VERTICAL, cur[3] + 1});
                        visited[cur[0] - 1][cur[1] + 1][VERTICAL] = true;
                    }
                }
                // DOWN 방향 회전
                if (canRotateDown(cur[0], cur[1])) {
                    if (!visited[cur[0]][cur[1]][VERTICAL]) {
                        q.offer(
                            new int[]{cur[0], cur[1], VERTICAL, cur[3] + 1});
                        visited[cur[0]][cur[1]][VERTICAL] = true;
                    }
                    if (!visited[cur[0]][cur[1] + 1][VERTICAL]) {
                        q.offer(
                            new int[]{cur[0], cur[1] + 1, VERTICAL, cur[3] + 1});
                        visited[cur[0]][cur[1] + 1][VERTICAL] = true;
                    }
                }
            } else {    // 세로 상태
                // LEFT 방향 회전
                if (canRotateLeft(cur[0], cur[1])) {
                    if (!visited[cur[0]][cur[1] - 1][HORIZONTAL]) {
                        q.offer(
                            new int[]{cur[0], cur[1] - 1, HORIZONTAL, cur[3] + 1});
                        visited[cur[0]][cur[1] - 1][HORIZONTAL] = true;
                    }
                    if (!visited[cur[0] + 1][cur[1] - 1][HORIZONTAL]) {
                        q.offer(
                            new int[]{cur[0] + 1, cur[1] - 1, HORIZONTAL, cur[3] + 1});
                        visited[cur[0] + 1][cur[1] - 1][HORIZONTAL] = true;
                    }
                }
                // RIGHT 방향 회전
                if (canRotateRight(cur[0], cur[1])) {
                    if (!visited[cur[0]][cur[1]][HORIZONTAL]) {
                        q.offer(
                            new int[]{cur[0], cur[1], HORIZONTAL, cur[3] + 1});
                        visited[cur[0]][cur[1]][HORIZONTAL] = true;
                    }
                    if (!visited[cur[0] + 1][cur[1]][HORIZONTAL]) {
                        q.offer(
                            new int[]{cur[0] + 1, cur[1], HORIZONTAL, cur[3] + 1});
                        visited[cur[0] + 1][cur[1]][HORIZONTAL] = true;
                    }
                }
            }
        }
        
        return -1;
    }
    
    private boolean canRotateUp(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + UP[d][0];
            int nc = c + UP[d][1];
            if (isOutOfBounds(nr, nc) || board[nr][nc] == WALL) {
                return false;
            }
        }
        return true;
    }
    
    private boolean canRotateDown(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + DOWN[d][0];
            int nc = c + DOWN[d][1];
            if (isOutOfBounds(nr, nc) || board[nr][nc] == WALL) {
                return false;
            }
        }
        return true;
    }
    
    private boolean canRotateLeft(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + LEFT[d][0];
            int nc = c + LEFT[d][1];
            if (isOutOfBounds(nr, nc) || board[nr][nc] == WALL) {
                return false;
            }
        }
        return true;
    }
    
    private boolean canRotateRight(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + RIGHT[d][0];
            int nc = c + RIGHT[d][1];
            if (isOutOfBounds(nr, nc) || board[nr][nc] == WALL) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isOutOfBounds(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}