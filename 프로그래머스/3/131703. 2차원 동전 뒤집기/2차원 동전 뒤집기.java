class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int answer = beginning.length + beginning[0].length;
        for (int k = 0; k < beginning[0].length; k++) {
            int result = 0;
            int[][] copied = copy(beginning);
            for (int i = 0; i < copied.length; i++) {    // 행 뒤집기
                if (copied[i][k] != target[i][k]) {
                    for (int j = 0; j < copied[i].length; j++) {
                        copied[i][j] = 1 - copied[i][j];
                    }
                    result++;
                }
            }
            for (int i = 0; i < copied[0].length; i++) {    // 열 뒤집기
                if (copied[0][i] != target[0][i]) {
                    for (int j = 0; j < copied.length; j++) {
                        copied[j][i] = 1 - copied[j][i];
                    }
                    result++;
                }
            }
            for (int i = 0; i < copied.length; i++) {
                for (int j = 0; j < copied[i].length; j++) {
                    if (copied[i][j] != target[i][j]) {
                        return -1;
                    }
                }
            }   
            answer = Math.min(answer, result);   
        }
        return answer;
    }
    
    private int[][] copy(int[][] origin) {
        int[][] copied = new int[origin.length][origin[0].length];
        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < origin[i].length; j++) {
                copied[i][j] = origin[i][j];
            }
        }
        return copied;
    }
}

/*
행 또는 열을 선택해서 뒤집기
*/