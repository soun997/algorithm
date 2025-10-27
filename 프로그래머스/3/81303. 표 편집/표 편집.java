import java.util.*;

class Solution {
    
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        
        int cursor = k;
        boolean[] deleted = new boolean[n];
        Stack<Integer> stk = new Stack<>();
        
        for (String c : cmd) {
            String[] chunks = c.split(" ");
            
            if (chunks[0].equals("U")) {
                int length = Integer.parseInt(chunks[1]);
                for (int i = 0; i < length; i++) {
                    cursor = prev[cursor];
                }
            } else if (chunks[0].equals("D")) {
                int length = Integer.parseInt(chunks[1]);
                for (int i = 0; i < length; i++) {
                    cursor = next[cursor];
                }
            } else if (chunks[0].equals("C")) {
                deleted[cursor] = true;
                stk.push(cursor);
                int prevRow = prev[cursor];
                int nextRow = next[cursor];
                if (prevRow != -1) {
                    next[prevRow] = nextRow;
                }
                if (nextRow != n) {
                    prev[nextRow] = prevRow;
                }
                if (next[cursor] == n) {  // cursor가 마지막 요소를 가리키는 경우
                    cursor = prevRow;
                } else {
                    cursor = nextRow;
                }
            } else { // Z
                int restore = stk.pop();
                deleted[restore] = false;
                int prevRow = prev[restore];
                int nextRow = next[restore];
                if (prevRow != -1) {
                    next[prevRow] = restore;
                }
                if (nextRow != n) {
                    prev[nextRow] = restore;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(deleted[i] ? "X" : "O");
        }
        return sb.toString();
    }
}