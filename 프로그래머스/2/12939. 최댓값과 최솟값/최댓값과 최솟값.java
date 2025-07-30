import java.util.*;

class Solution {
    public String solution(String input) {
        String[] strNumbers = input.split(" ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (String s : strNumbers) {
            int n = Integer.parseInt(s);
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        return String.format("%d %d", min, max);
    }
}