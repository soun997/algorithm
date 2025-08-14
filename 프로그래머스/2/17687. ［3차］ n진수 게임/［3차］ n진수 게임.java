class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        int num = 0;
        int pos = 0;
        
        while (answer.length() < t) {
            String converted = Integer.toString(num, n).toUpperCase();
            
            for (char c : converted.toCharArray()) {
                if (pos % m == p - 1) {
                    answer.append(c);
                    if (answer.length() == t) {
                        break;
                    }
                }
                pos++;
            }
            num++;
        }
        return answer.toString();
    }
}