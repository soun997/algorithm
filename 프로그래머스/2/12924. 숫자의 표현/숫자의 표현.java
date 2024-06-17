class Solution {
    public int solution(int n) {
        int lower = 0;
        int upper = 1;
        int answer = 0;
        
        while(upper <= n && lower <= n) {
            int sum = getSum(lower, upper);
            if (sum < n) {
                upper++;
            }
            if (sum == n) {
                answer++;
                lower++;
                upper++;
            }
            if (sum > n) {
                lower++;
            }
        }
        
        return answer;
    }
    
    private int getSum(int lower, int upper) {
        int a = lower * (lower + 1) / 2;
        int b = upper * (upper + 1) / 2;
        return b - a;
    }
}