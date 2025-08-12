import java.util.*;

class Solution {
    
    private Map<Character, int[]> commands = Map.of(
        'U', new int[] { 1, 0 },
        'D', new int[] { -1, 0 },
        'L', new int[] { 0, -1 },
        'R', new int[] { 0, 1 }
    );
    
    public int solution(String dirs) {
        int answer = bfs(dirs.toCharArray());
        return answer;
    }
    
    private int bfs(char[] dirs) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { 5, 5 });
        boolean[][][][] visited = new boolean[11][11][11][11]; // (x,y) -> (z,w)로 간 적이 있는지?
        int commandIndex = 0;
        int newRouteCount = 0;
        while(!q.isEmpty() && commandIndex < dirs.length) {
            int[] cur = q.poll();
            int[] dir = commands.get(dirs[commandIndex++]);
            int nx = cur[0] + dir[0];
            int ny = cur[1] + dir[1];
            if (isOutOfBounds(nx, ny)) {
                q.offer(cur);
                continue;
            }
            if (visited[cur[0]][cur[1]][nx][ny]) {
                q.offer(new int[] { nx, ny });
                continue;
            }
            q.offer(new int[] { nx, ny });
            visited[cur[0]][cur[1]][nx][ny] = true;
            visited[nx][ny][cur[0]][cur[1]] = true;
            newRouteCount++;
        }
        return newRouteCount;
    }
    
    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= 11 || y < 0 || y >= 11;
    }
}