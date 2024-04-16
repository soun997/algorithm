import java.util.*;

class Solution {
    private int[] dx = {-1,1,0,0};
    private int[] dy = {0,0,-1,1};
    private List<Group> groups = new ArrayList<>();
    private int width,height;
    private boolean[][] visited;
    private int[][] land;
    
    public int solution(int[][] land) {
        int maxNum = 0;
        this.land = land;
        initGroups(land);
        int[] answer = new int[land[0].length];
        
        // 모든 석유 덩어리를 확인해서, 시추 가능한 땅의 열에 모두 저장
        for(int i = 0; i < groups.size(); i++){
            for(Integer offset : groups.get(i).possibleY){
                answer[offset] += groups.get(i).count;
                maxNum = Math.max(maxNum,answer[offset]);
            }
        }
        return maxNum;
    }
    
    private void initGroups(int[][] land){
        visited = new boolean[land.length][land[0].length];
        height = land.length;
        width = land[0].length;
        
        for(int i = 0; i < land.length; i++){
            for(int j = 0; j < land[0].length; j++){
            	// 석유가 없는 땅이거나, 이미 방문한 땅이면 패스
                if(visited[i][j] || land[i][j] == 0) continue;
                groups.add(bfs(i,j));
            }
        }
    }
    
    // 최종적으로 만들어지는 것은 석유 덩어리
    private Group bfs(int sx, int sy){
        Queue<List<Integer>> q = new LinkedList<>();
        Set<Integer> possibleY = new HashSet<>();
        int count = 1;
        q.add(List.of(sx,sy));
        visited[sx][sy] = true;
        possibleY.add(sy);
        while(!q.isEmpty()){
            List<Integer> temp = q.poll();
            int x = temp.get(0);
            int y = temp.get(1);
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= height || ny >= width
                  || land[nx][ny] == 0 || visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.add(List.of(nx,ny));
                count++;
                possibleY.add(ny);
            }
        }
        return new Group(possibleY,count);
    }
    
    // 석유 덩어리 그룹 클래스를 만듦
    public class Group{
        int count;
        Set<Integer> possibleY;
        public Group(Set<Integer> possibleY, int count){
            this.possibleY = possibleY;
            this.count = count;
        }
    }
}