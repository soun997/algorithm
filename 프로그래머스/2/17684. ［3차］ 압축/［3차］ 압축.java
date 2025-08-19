import java.util.*;

class Solution {
    public List<Integer> solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            dict.put(Character.toString(i), dict.size() + 1);
        }
        String w = "";
        for (int i = 0; i < msg.length(); i++) {
            w = msg.substring(i, i + 1);
            while (i + 1 < msg.length()) {
                String c = msg.substring(i + 1, i + 2);
                String wc = w + c;
                if (dict.containsKey(wc)) {
                    w += c;
                    i++;
                } else {
                    answer.add(dict.get(w));
                    dict.put(wc, dict.size() + 1);
                    break;
                }
            }
        }
        answer.add(dict.get(w));
        return answer;
    }
}