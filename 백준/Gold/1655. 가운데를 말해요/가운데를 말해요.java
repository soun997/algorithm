import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    static int n;
    static PriorityQueue<Integer> increase;
    static PriorityQueue<Integer> decrease;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        increase = new PriorityQueue<>();
        decrease = new PriorityQueue<>((o1, o2) -> -1 * Integer.compare(o1, o2));

        for (int i = 0; i < n; i++) {
            if (i % 2 == 1){
                increase.offer(Integer.parseInt(br.readLine()));
            }
            if (i % 2 == 0) {
                decrease.offer(Integer.parseInt(br.readLine()));
            }

            if (increase.isEmpty() || decrease.isEmpty()){
                sb.append(decrease.peek()).append("\n");
                continue;
            }

            if (increase.peek() < decrease.peek()) {
                int temp = decrease.poll();
                decrease.offer(increase.poll());
                increase.offer(temp);
            }
            sb.append(decrease.peek()).append("\n");
        }
        System.out.println(sb.toString());

    }
}