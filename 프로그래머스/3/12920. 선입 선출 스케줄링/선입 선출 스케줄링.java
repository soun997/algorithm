import java.util.*;

class Solution {
    
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }
        int left = 0;
        int right = n * 10000;
        int processed = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            int count = calculate(mid, cores);
            // 처리할 수 있는 작업 개수가 더 많다면
            if (count >= n) {
                right = mid - 1;
            } else {    // 아니라면
                left = mid + 1;
                processed = count;
            }
        }
        
        int remain = n - processed;
        for (int i = 0; i < cores.length; i++) {
            if (left % cores[i] == 0) {
                remain--;
                if (remain == 0) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    private int calculate(int totalTime, int[] cores) {
        int count = cores.length;
        for (int processTime : cores) {
            // 해당 코어는 totalTime 내에 (totalTime / processTime)개의 작업 처리 가능
            count += totalTime / processTime;
        }
        return count;
    }

}