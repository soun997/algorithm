import java.util.*;

class Solution {
    public List<int[]> solution(int n, int[][] build_frame) {
        List<int[]> answer = new ArrayList<>();
        boolean[][] columns = new boolean[n + 1][n + 1];
        boolean[][] panels = new boolean[n + 1][n + 1];
        for (int[] frame: build_frame) {
            int x = frame[0];
            int y = n - frame[1];
            int a = frame[2];
            int b = frame[3];
            
            if (a == 0) {   // 기둥
                if (b == 0) {
                    boolean backup = columns[y][x];
                    columns[y][x] = false;
                    if (!isValid(columns, panels, n)) {
                        columns[y][x] = backup;
                    };
                    continue;
                }
                if (y == n ||   // 바닥 위 설치
                   (x - 1 >= 0 && panels[y][x - 1]) || panels[y][x] ||  // 보의 한쪽 끝에 설치
                   (y + 1 <= n && columns[y + 1][x])) {    // 다른 기둥 위 설치
                    columns[y][x] = true;
                } 
            } else {    // 보
                if (b == 0) {
                    boolean backup = panels[y][x];
                    panels[y][x] = false;
                    if (!isValid(columns, panels, n)) {
                        panels[y][x] = backup;
                    };
                    continue;
                }
                if ((y + 1 <= n && columns[y + 1][x]) || 
                    (x + 1 <= n && y + 1 <= n && columns[y + 1][x + 1]) || 
                   ((x - 1 >= 0 && panels[y][x - 1]) && (x + 1 <= n && panels[y][x + 1]))) {
                    panels[y][x] = true;
                }
            }
        }
        
        for (int y = 0; y < n + 1; y++) {
            for (int x = 0; x < n + 1; x++) {
                if (columns[y][x]) {
                    answer.add(new int[] { x, n - y, 0 });
                }
                if (panels[y][x]) {
                    answer.add(new int[] { x, n - y, 1 });
                }
            }
        }
        
        Collections.sort(answer, (a, b) -> {
            if (a[0] == b[0]) {
                if (a[1] == b[1]) {
                    return Integer.compare(a[2], b[2]);
                }
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        return answer;
    }
    
    private boolean isValid(boolean[][] columns, boolean[][] panels, int n) {
        for (int y = 0; y < n + 1; y++) {
            for (int x = 0; x < n + 1; x++) {
                if (!columns[y][x] && !panels[y][x]) {
                    continue;   
                }
                if (columns[y][x]) {   // 기둥
                    boolean flag = false;
                    if (y == n ||   // 바닥 위 설치
                       (x - 1 >= 0 && panels[y][x - 1]) || panels[y][x] ||  // 보의 한쪽 끝에 설치
                       (y + 1 <= n && columns[y + 1][x])) {    // 다른 기둥 위 설치
                        flag = true;
                    } 
                    if (!flag) {
                        return false;
                    }
                }
                if (panels[y][x]) {    // 보
                    boolean flag = false;
                    if ((y + 1 <= n && columns[y + 1][x]) || 
                        (x + 1 <= n && y + 1 <= n && columns[y + 1][x + 1]) || 
                       ((x - 1 >= 0 && panels[y][x - 1]) && (x + 1 <= n && panels[y][x + 1]))) {
                        flag = true;
                    }
                    if (!flag) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}