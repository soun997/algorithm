import java.util.*;

class Solution {
    
    private static final String ENTER_FORMAT = "%s님이 들어왔습니다.";
    private static final String LEAVE_FORMAT = "%s님이 나갔습니다.";

    public List<String> solution(String[] records) {
        Map<String, String> nicknames = new HashMap<>();
        for (String record : records) {
            String[] chunks = record.split(" ");
            if (chunks.length > 2) {
                nicknames.put(chunks[1], chunks[2]); // id, nickname   
            }
        }
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < records.length; i++) {
            String[] chunks = records[i].split(" ");
            String command = chunks[0];
            String nickname = nicknames.get(chunks[1]);
            if (command.equals("Enter")) {
                answer.add(String.format(ENTER_FORMAT, nickname));
            }
            if (command.equals("Leave")) {
                answer.add(String.format(LEAVE_FORMAT, nickname));
            }
        }
        return answer;
    }
}