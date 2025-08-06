import java.util.*;
import java.util.regex.*;

class Solution {
    public List<Integer> solution(String s) {
        Map<Integer, List<Integer>> parsedSet = new HashMap<>();
        
        // 중괄호로 둘러싸인 집합들을 찾는 패턴
        Pattern pattern = Pattern.compile("\\{([^{}]*)\\}");
        Matcher matcher = pattern.matcher(s);
        
        int max = Integer.MIN_VALUE;
        while (matcher.find()) {
            String group = matcher.group(1).trim();
            List<Integer> numbers = new ArrayList<>();
            for (String number : group.split(",")) {
                numbers.add(Integer.parseInt(number));
            }
            parsedSet.put(numbers.size(), numbers);
            max = Math.max(max, numbers.size());
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            parsedSet.get(i).removeAll(result);
            int number = parsedSet.get(i).get(0);
            result.add(number);
        }
        return result;
    }
}