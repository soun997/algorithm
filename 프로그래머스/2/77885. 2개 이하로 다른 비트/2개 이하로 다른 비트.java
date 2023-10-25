class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            answer[i] = f(numbers[i]);
        }
        return answer;
    }
    
    private long f(long x) {
        
        int exp = 0;
        while(!isValid(x, x + (long)Math.pow(2, exp))) {
            exp++;
        }
        
        return x + (long)Math.pow(2, exp);
    }
    
    private boolean isValid(long a, long b) {
        String sa = Long.toString(a, 2);
        String sb = Long.toString(b, 2);
        int diff = (sa.length() == sb.length() ? 0 : 1);
        int adder = (sa.length() == sb.length() ? 0 : 1);
        for (int i = sb.length() - 1; i >= adder; i--) {
            if (sa.charAt(i - adder) != sb.charAt(i)) {
                diff++;
                if (diff > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}