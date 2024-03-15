import java.util.*;

class Solution {
    
    Queue<Integer> bridge = new ArrayDeque<>();
    
    int curLength = 0;
    int curWeight = 0;
    int time = 0;
    int idx = 0;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }
        
        while(idx < truck_weights.length) {
            //System.out.println(bridge);
            int out = bridge.poll();
            curWeight -= out;
            int in = truck_weights[idx];
            time++;
            // 다리가 버틸 수 있는 최대 무게를 넘지 않는다면 -> 트럭을 올림
            if (curWeight + in <= weight) {
                bridge.offer(in);
                curWeight += in;
                idx++;
                continue;
            }
            bridge.offer(0);
        }
        while(!bridge.isEmpty()) {
            bridge.poll();
            time++;
        }
        
        return time;
    }
}