import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 10; t++) {

            int cnt = Integer.parseInt(br.readLine());

            int[] boxes = new int[100];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {

                boxes[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < cnt; i++) {
                flattern(boxes);
            }

            sb.append("#").append(t + 1).append(" ").append(getResult(boxes)).append("\n");
        }
        System.out.println(sb);
    }

    static void flattern(int[] boxes){

        int max = Integer.MIN_VALUE;
        int maxIdx = -1;
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < 100; i++){
            if (boxes[i] > max)
                maxIdx = i;
            max = Math.max(max, boxes[i]);
            if (boxes[i] < min)
                minIdx = i;
            min = Math.min(min, boxes[i]);
        }
        boxes[maxIdx]--;
        boxes[minIdx]++;
    }

    static int getResult(int[] boxes){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 100; i++) {
            max = Math.max(max, boxes[i]);
            min = Math.min(min, boxes[i]);
        }
        return max - min;
    }
}
