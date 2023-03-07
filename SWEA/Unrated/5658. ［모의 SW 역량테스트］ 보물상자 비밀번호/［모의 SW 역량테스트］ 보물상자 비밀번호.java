import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int TC;
    static int N, K;
    static Queue<String> q;
    static TreeSet<Integer> numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());
        for (int t = 1; t <= TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            q = new ArrayDeque<>();
            numbers = new TreeSet<>();
            String[] inputs = br.readLine().split("");
            for (int i = 0; i < N; i++){
                q.offer(inputs[i]);
            }
            for (int n = 0; n < N / 4; n++){
                for (int i = 0; i < 4; i++) {
                    StringBuilder number = new StringBuilder();
                    for (int j = 0; j < N / 4; j++){
                        number.append(q.peek());
                        q.offer(q.poll());
                    }
                    numbers.add(Integer.parseInt(number.toString(), 16));
                }
                q.offer(q.poll());
            }

            int cnt = numbers.size() - K;
            for (Integer num : numbers){
                //System.out.println(num);
                if (cnt == 0){
                    sb.append("#").append(t).append(" ").append(num).append("\n");
                }
                cnt--;
            }
        }
        System.out.println(sb);
    }
}