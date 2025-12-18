import java.util.*;

class Solution {
    final int INF = Integer.MAX_VALUE / 2;
    int[][] straight = new int[][] {
        { -1, 0, 1, 0 },
        { 0, -1, 0, 1},
    };
    int[][] diag = new int[][] {
        { -1, -1, 1, 1 },
        { -1, 1, -1, 1 },
    };
    Map<Character, Integer> charToNum = new HashMap<>();
    int[][] minWeights = new int[12][12];   // minWeights[i][j]: i번째 자판에서 [j] 자판으로 갈 때의 가중치 최솟값
    int[][][] dp = new int[100_001][12][12];    // dp[i][l][r]: i번째까지 입력했고, 왼손: l, 오른손: r일 때 최솟값
    
    public int solution(String numbers) {
        initCharToNum();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                fillMinWeights(i, j);
            }
        }

        for (int i = 0; i <= 100_000; i++) {
            for (int j = 0; j < 12; j++) {
                for (int k = 0; k < 12; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }
        
        dp[0][3][5] = 0;    // 초깃값, 왼손 3(4), 오른손 5(6)
        
        for (int i = 0; i < numbers.length(); i++) {
            int target = charToNum.get(numbers.charAt(i));
            
            for (int left = 0; left < 12; left++) {
                for (int right = 0; right < 12; right++) {
                    if (dp[i][left][right] == INF) {  // 이동 불가
                        continue;
                    }
                    // 왼손으로 누르는 경우
                    if (target != right) {
                        dp[i + 1][target][right] = Math.min(
                            dp[i + 1][target][right],
                            dp[i][left][right] + minWeights[left][target]
                        );
                    }
                    
                    // 오른손으로 누르는 경우
                    if (target != left) {
                        dp[i + 1][left][target] = Math.min(
                            dp[i + 1][left][target],
                            dp[i][left][right] + minWeights[right][target]
                        );
                    }
                }
            }
        }
        
        int answer = INF;
        for (int left = 0; left < 12; left++) {
            for (int right = 0; right < 12; right++) {
                answer = Math.min(answer, dp[numbers.length()][left][right]);
            }
        }        
        return answer;
    }
    
    private void initCharToNum() {
        charToNum.put('1', 0);
        charToNum.put('2', 1);
        charToNum.put('3', 2);
        charToNum.put('4', 3);
        charToNum.put('5', 4);
        charToNum.put('6', 5);
        charToNum.put('7', 6);
        charToNum.put('8', 7);
        charToNum.put('9', 8);
        charToNum.put('*', 9);
        charToNum.put('0', 10);
        charToNum.put('#', 11);
    }
    
    private void fillMinWeights(int startR, int startC) {
        int start = startR * 3 + startC;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[4][3];
        q.offer(new int[] { startR, startC, 0 });
        visited[startR][startC] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int end = cur[0] * 3 + cur[1];
            minWeights[start][end] = cur[2];
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + straight[0][d];
                int nc = cur[1] + straight[1][d];
                if (isOutOfBounds(nr, nc) || visited[nr][nc]) {
                    continue;
                }
                q.offer(new int[] { nr, nc, cur[2] + 2 });
                visited[nr][nc] = true;
            }
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + diag[0][d];
                int nc = cur[1] + diag[1][d];
                if (isOutOfBounds(nr, nc) || visited[nr][nc]) {
                    continue;
                }
                q.offer(new int[] { nr, nc, cur[2] + 3 });
                visited[nr][nc] = true;
            }
        }
        minWeights[start][start] = 1;
    }
    
    private boolean isOutOfBounds(int r, int c) {
        return r < 0 || r >= 4 | c < 0 || c >= 3;
    }
}

/*
같은 숫자 버튼 위에 동시에 두 엄지 올릴 수 없음
왼손, 오른손 엄지 구분
bfs로 cost 채우기
*/