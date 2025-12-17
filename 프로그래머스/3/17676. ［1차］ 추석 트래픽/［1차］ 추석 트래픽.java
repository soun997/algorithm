import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int[][] logs = new int[lines.length][2];
        int idx = 0;
        for (String line : lines) {
            String[] chunks = line.split(" ");
            int endTime = endTimeToMilli(chunks[1]);            
            int processTime = processTimeToMilli(chunks[2]);
            int startTime = endTime - (processTime - 1);
            // System.out.println("start: " + startTime);
            // System.out.println("end: " + endTime);
            // System.out.println("process: " + processTime);
            logs[idx][0] = startTime;
            logs[idx][1] = endTime;
            idx++;
        }
        int answer = 0;
        for (int i = 0; i < logs.length; i++) {
            int count = 1;
            for (int j = i + 1; j < logs.length; j++) {
                if (logs[i][1] + 1000 > logs[j][0]) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }
        return answer;
    }
    
    private int endTimeToMilli(String str) {
        String[] chunks1 = str.split("\\.");
        String[] chunks2 = chunks1[0].split(":");
        int hour = Integer.parseInt(chunks2[0]) * 3600 * 1000;
        int minute = Integer.parseInt(chunks2[1]) * 60 * 1000;
        int second = Integer.parseInt(chunks2[2]) * 1000;
        int milli = Integer.parseInt(chunks1[1]);
        return hour + minute + second + milli;
    }
    
    private int processTimeToMilli(String str) {
        String time = str.replace("s", "");
        double seconds = Double.parseDouble(time);
        return (int)(seconds * 1000);
    }
}