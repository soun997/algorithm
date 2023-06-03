import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int N;
    static Queue<Integer> cards;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cards = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            cards.offer(i);
        }

        while(cards.size() > 1){
            cards.poll();
            int card = cards.poll();
            cards.offer(card);
        }

        System.out.println(cards.poll());
    }
}