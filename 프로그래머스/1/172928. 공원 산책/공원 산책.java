import java.util.*;

class Solution {
    
    char[][] park;
    String[] routes;
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        this.park = new char[park.length][park[0].length()];
        this.routes = routes;
        int x = 0;
        int y = 0;
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                this.park[i][j] = park[i].charAt(j);
                if (this.park[i][j] == 'S') {
                    x = i;
                    y = j;
                }
            }
        }
        return simulate(x, y);
    }
    
    private int[] simulate(int x, int y) {
        int[] cur = {x, y};
        for (String query : routes) {
            String[] splitted = query.split(" ");
            String dir = splitted[0];
            int delta = Integer.parseInt(splitted[1]);
            executeQuery(cur, dir, delta);
        }
        return cur;
    }
    
    private void executeQuery(int[] cur, String query, int delta) {
        switch(query) {
            case "N" -> move(cur, 0, delta);
            case "W" -> move(cur, 1, delta);
            case "S" -> move(cur, 2, delta);
            case "E" -> move(cur, 3, delta);
        }
    }
    
    private void move(int[] cur, int dir, int delta) {
        int tempX = cur[0];
        int tempY = cur[1];
        for(int i = 0; i < delta; i++) {
            int nx = tempX + dx[dir];
            int ny = tempY + dy[dir];
            if (isOutOfBounds(nx, ny) || park[nx][ny] == 'X') {
                return;
            }
            tempX = nx;
            tempY = ny;
        }
        cur[0] = tempX;
        cur[1] = tempY;
    }
    
    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= park.length || y < 0 || y >= park[0].length;
    }
}