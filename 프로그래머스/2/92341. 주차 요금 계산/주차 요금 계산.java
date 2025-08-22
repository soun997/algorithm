import java.util.*;

class Solution {
    private static final int MAX = 23 * 60 + 59;
    private int basicTime, basicFee;
    private int unitTime, unitFee;
    private List<Integer>[] parkingTimes;
    
    public List<Integer> solution(int[] fees, String[] records) {
        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
        parkingTimes = parseRecords(records);

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            if (parkingTimes[i].size() > 0) {
                answer.add(calculateFee(parkingTimes[i]));   
            }
        }
        return answer;
    }
    
    private List<Integer>[] parseRecords(String[] records) {
        List<Integer>[] totalTimes = new ArrayList[10000];
        for (int i = 0; i < totalTimes.length; i++) {
            totalTimes[i] = new ArrayList<>();
        }
        for (String r : records) {
            String[] chunks = r.split(" ");
            int time = parseTime(chunks[0]);
            int carNum = Integer.parseInt(chunks[1]);
            String command = chunks[2];
            totalTimes[carNum].add(time);
        }
        return totalTimes;
    }
    
    private int parseTime(String time) {
        String[] chunks = time.split(":");
        int hour = Integer.parseInt(chunks[0]);
        int minute = Integer.parseInt(chunks[1]);
        return hour * 60 + minute;
    }
    
    private int calculateFee(List<Integer> times) {
        if (times.size() % 2 == 1) {
            times.add(MAX);
        }
        int totalTime = 0;
        for (int i = 0; i < times.size() - 1; i += 2) {
            int enter = times.get(i);
            int exit = times.get(i + 1);
            totalTime += exit - enter;
        }
        int totalFee = basicFee;
        if (totalTime > basicTime) {
            totalFee += Math.ceil((totalTime - basicTime) / (float)unitTime) * unitFee;
        }
        return totalFee;
    }
}