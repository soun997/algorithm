class Solution {
    
    private static final int FAILED = Integer.MAX_VALUE;
    private String target;
    private String[] words;
    private boolean[] visited;
    private int answer = FAILED;
    
    public int solution(String begin, String target, String[] words) {
        this.target = target;
        this.words = words;
        visited = new boolean[words.length];
        
        dfs(begin, 0);
    
        if (answer == FAILED) {
            return 0;
        }
        return answer;
    }
    
    private void dfs(String word, int count) {
        if (word.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && isConvertable(word, words[i])) {
                visited[i] = true;
                dfs(words[i], count + 1);
                visited[i] = false;
            }
        }
    }
    
    private boolean isConvertable(String from, String to) {
        int diff = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                return false;
            }
        }
        if (diff == 1) {
            return true;
        }
        return false;
    }
}