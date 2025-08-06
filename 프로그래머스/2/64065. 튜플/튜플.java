import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Solution {
    public int[] solution(String s) {

        Map<String, Integer> map = new HashMap<>();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String n = matcher.group();
            map.putIfAbsent(n, 0);
            map.computeIfPresent(n, (k, v) -> v + 1);
        }
        int size = map.size();
        int[] answer = new int[size];
        for (String key: map.keySet()) {
            answer[size - map.get(key)] = Integer.parseInt(key);
        }
        return answer;
    }

}