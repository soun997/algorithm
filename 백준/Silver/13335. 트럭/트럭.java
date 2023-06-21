import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Bridge {

        Queue<Integer> bridge;
        int curWeight;
        int time;

        public Bridge() {
            bridge = new ArrayDeque<>();
            for (int i = 0; i < W; i++){
                bridge.offer(0);
            }
            this.curWeight = 0;
            this.time = 0;
        }

        public boolean isOutOfWeight(int weight) {
            return (curWeight + weight) > L;
        }

        public void addTruck(int weight) {
            bridge.offer(weight);
            curWeight += weight;
            time++;
        }

        public void clearBridge(int weight) {
            int removed = bridge.poll();
            curWeight -= removed;
            while (isOutOfWeight(weight)){
                moveTruck();
            }
        }

        private void moveTruck() {
            int removed = bridge.poll();
            curWeight -= removed;
            bridge.offer(0);
            time++;
        }

        public int getTotalTime() {
            return time + W;
        }
    }

    static int N, W, L;
    static int[] trucks;
    static Bridge bridge;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        trucks = new int[N];
        bridge = new Bridge();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        while (idx < N) {
            bridge.clearBridge(trucks[idx]);
            bridge.addTruck(trucks[idx++]);
        }

        System.out.println(bridge.getTotalTime());
    }
}