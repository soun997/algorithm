import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        Deque<Integer> deque = new ArrayDeque<>();

        if (a < b) {
            for (int i = 1; i <= b; i++) {
                deque.addFirst(i);
            }
            for (int i = a - 1; i >= 1; i--) {
                deque.addFirst(i);
            }
        } else {
            for (int i = 1; i <= a; i++) {
                deque.addLast(i);
            }
            for (int i = b - 1; i >= 1; i--) {
                deque.addLast(i);
            }
        }

        int fill = N - deque.size();

        if (fill < 0){
            System.out.println(-1);
            return;
        }

        boolean isFilled = false;
        for (int i = 0; i < a; i++){
            int number = deque.poll();
            if (number == 1){
                for (int j = 0; j < fill; j++) {
                    result.append("1 ");
                }
                isFilled = true;
            }
            result.append(number).append(" ");
        }
        if (!isFilled){
            for (int j = 0; j < fill; j++) {
                result.append("1 ");
            }
        }
        while(!deque.isEmpty()){
            result.append(deque.poll()).append(" ");
        }

        System.out.println(result);
    }
}