import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++){
            q.offer(i);
        }

        StringBuilder answer = new StringBuilder("<");
        int count = 0;
        while (true){
            int number = q.poll();
            if (q.isEmpty()){
                answer.append(number);
                break;
            }
            count = count + 1;
            if (count == K){
                answer.append(number).append(", ");
                count = 0;
                continue;
            }
            q.offer(number);
        }
        answer.append(">");
        System.out.println(answer);
    }
}