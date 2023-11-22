class Solution {
    
    private int[][] arr1;
    private int[][] arr2;
    
    public int[][] solution(int[][] arr1, int[][] arr2) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[0].length; j++){
               
                answer[i][j] = getValue(i, j);
            }
        }
        return answer;
    }
    
    private int getValue(int i, int j) {
        
        int result = 0;
        for (int a = 0; a < arr1[0].length; a++) {
            result += arr1[i][a] * arr2[a][j];
        }
        
        return result;
    }
}